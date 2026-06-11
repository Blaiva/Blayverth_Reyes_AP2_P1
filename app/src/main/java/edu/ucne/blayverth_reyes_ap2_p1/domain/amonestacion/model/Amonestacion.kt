package edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model

data class Amonestacion(
    val amonestacionId: Int = 0,
    val nombres: String = "",
    val razon: String = "",
    val monto: Double = 0.0
)
