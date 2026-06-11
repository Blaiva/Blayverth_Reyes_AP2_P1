package edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.list

sealed interface AmonestacionListUiEvent {
    object Load: AmonestacionListUiEvent
    object Refresh: AmonestacionListUiEvent
    object ClearMessage: AmonestacionListUiEvent
    object CreateNew: AmonestacionListUiEvent
    data class ShowMessage(val message: String): AmonestacionListUiEvent
    data class Edit(val id: Int): AmonestacionListUiEvent
}