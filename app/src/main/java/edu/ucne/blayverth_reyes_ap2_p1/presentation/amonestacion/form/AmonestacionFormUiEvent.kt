package edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.form

sealed interface AmonestacionFormUiEvent {
    data class Load(val id: Int?): AmonestacionFormUiEvent
    data class NombresChanged(val nombres: String): AmonestacionFormUiEvent
    data class RazonChanged(val razon: String): AmonestacionFormUiEvent
    data class MontoChanged(val monto: String): AmonestacionFormUiEvent
    data object Save: AmonestacionFormUiEvent
    data object Delete: AmonestacionFormUiEvent
}