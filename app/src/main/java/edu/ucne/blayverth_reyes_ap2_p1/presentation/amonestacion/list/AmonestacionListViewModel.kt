package edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase.ObserveAmonestacionUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AmonestacionListViewModel @Inject constructor(val observeAmonestacionUseCase: ObserveAmonestacionUseCase): ViewModel() {
    private val _state = MutableStateFlow(AmonestacionListUiState(isLoading = true))
    val state: StateFlow<AmonestacionListUiState> = _state.asStateFlow()

    init {
        loadAmonestaciones()
    }

    fun onEvent(event: AmonestacionListUiEvent){
        when(event){
            AmonestacionListUiEvent.Load -> loadAmonestaciones()
            AmonestacionListUiEvent.Refresh -> loadAmonestaciones()
            is AmonestacionListUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            is AmonestacionListUiEvent.Edit -> _state.update { it.copy(navigateToEditId = event.id) }
            AmonestacionListUiEvent.CreateNew -> _state.update { it.copy(navigateToCreate = true) }
            AmonestacionListUiEvent.ClearMessage -> _state.update { it.copy(message = null) }
        }
    }

    fun loadAmonestaciones(){
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observeAmonestacionUseCase().collectLatest { list -> _state.update { it.copy(isLoading = false, amonestaciones = list, message = null) } }
        }
    }
}