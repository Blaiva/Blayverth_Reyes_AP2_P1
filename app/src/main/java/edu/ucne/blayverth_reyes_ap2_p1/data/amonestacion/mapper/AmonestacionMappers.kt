package edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.mapper

import edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.local.AmonestacionEntity
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion

fun Amonestacion.toEntity() = AmonestacionEntity(
    amonestacionId = amonestacionId,
    nombres = nombres,
    razon = razon,
    monto = monto
)

fun AmonestacionEntity.toDomain() = Amonestacion(
    amonestacionId = amonestacionId,
    nombres = nombres,
    razon = razon,
    monto = monto
)