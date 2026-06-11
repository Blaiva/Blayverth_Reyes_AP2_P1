package edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase.DeleteAmonestacionUseCase
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase.GetAmonestacionUseCase
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase.UpsertAmonestacionUseCase
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase.validarMonto
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase.validarNombres
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase.validarRazon
import edu.ucne.blayverth_reyes_ap2_p1.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionFormViewModel @Inject constructor(
    private val getAmonestacionUseCase: GetAmonestacionUseCase,
    private val upsertAmonestacionUseCase: UpsertAmonestacionUseCase,
    private val deleteAmonestacionUseCase: DeleteAmonestacionUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val routeArgs = savedStateHandle.toRoute<Screen.AmonestacionForm>()
    private val chequeId = routeArgs.amonestacionId

    private val _state = MutableStateFlow(AmonestacionFormUiState())
    val state: StateFlow<AmonestacionFormUiState> = _state.asStateFlow()

    fun onEvent(event: AmonestacionFormUiEvent) {
        when (event) {
            is AmonestacionFormUiEvent.Load -> loadAmonestacion(event.id)
            is AmonestacionFormUiEvent.NombresChanged -> _state.update { it.copy(nombres = event.nombres, nombresError = null) }
            is AmonestacionFormUiEvent.RazonChanged -> _state.update { it.copy(razon = event.razon, razonError = null) }
            is AmonestacionFormUiEvent.MontoChanged -> _state.update { it.copy(monto = event.monto, montoError = null) }
            AmonestacionFormUiEvent.Save -> onSave()
            AmonestacionFormUiEvent.Delete -> onDelete()
        }
    }

    fun loadAmonestacion(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, amonestacionId = null) }
            return
        }

        viewModelScope.launch {
            val amonestacion = getAmonestacionUseCase(id)
            if (amonestacion != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        amonestacionId = amonestacion.amonestacionId,
                        nombres = amonestacion.nombres,
                        razon = amonestacion.razon,
                        monto = amonestacion.monto.toString()
                    )
                }
            } else {
                _state.update { it.copy(isNew = true, amonestacionId = null) }
            }
        }
    }

    fun onSave() {
        val nombres = state.value.nombres
        val razon = state.value.razon
        val monto = state.value.monto

        val nombresValidation = validarNombres(nombres)
        val razonValidation = validarRazon(razon)
        val montoValidation = validarMonto(monto)

        if (!nombresValidation.isValid || !razonValidation.isValid || !montoValidation.isValid) {
            _state.update {
                it.copy(
                    nombresError = nombresValidation.message,
                    razonError = razonValidation.message,
                    montoError = montoValidation.message
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val amonestacion = Amonestacion(
                amonestacionId = state.value.amonestacionId ?: 0,
                nombres = nombres,
                razon = razon,
                monto = monto.toDoubleOrNull() ?: 0.0
            )

            val result = upsertAmonestacionUseCase(amonestacion)

            result.onSuccess { newId ->
                _state.update {
                    it.copy(
                        isSaving = false,
                        saved = true,
                        amonestacionId = newId,
                        isNew = false
                    )
                }
            }.onFailure {
                _state.update { it.copy(isSaving = false) }
            }
        }
    }

    private fun onDelete() {
        val id = state.value.amonestacionId ?: 0
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteAmonestacionUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}