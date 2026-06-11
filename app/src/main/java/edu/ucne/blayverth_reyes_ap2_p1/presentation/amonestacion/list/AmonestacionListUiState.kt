package edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.list

import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion

data class AmonestacionListUiState(
    val isLoading: Boolean = false,
    val amonestaciones: List<Amonestacion> = emptyList(),
    val navigateToCreate: Boolean = true,
    val navigateToEditId: Int? = null,
    val message: String? = null,
    val error: String? = null
)
