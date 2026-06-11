package edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.form

data class AmonestacionFormUiState(
    val amonestacionId: Int? = null,
    val nombres: String = "",
    val razon: String = "",
    val monto: String = "",
    val nombresError: String? = null,
    val razonError: String? = null,
    val montoError: String? = null,
    val isNew: Boolean = true,
    val isSaving: Boolean = false,
    val saved: Boolean = false,
    val isDeleting: Boolean = false,
    val deleted: Boolean = false
)
