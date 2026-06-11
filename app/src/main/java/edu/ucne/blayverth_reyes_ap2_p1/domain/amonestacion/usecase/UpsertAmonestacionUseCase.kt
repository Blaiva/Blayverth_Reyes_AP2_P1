package edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase

import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import javax.inject.Inject

class UpsertAmonestacionUseCase @Inject constructor(private val repository: AmonestacionRepository) {
    suspend operator fun invoke(amonestacion: Amonestacion): Result<Int>{
        val nombresResult = validarNombres(amonestacion.nombres)
        if(!nombresResult.isValid){
            return Result.failure(IllegalArgumentException(nombresResult.message))
        }

        val razonResult = validarRazon(amonestacion.razon)
        if(!razonResult.isValid){
            return Result.failure(IllegalArgumentException(nombresResult.message))
        }

        val montoResult = validarMonto(amonestacion.monto.toString())
        if(!montoResult.isValid){
            return Result.failure(IllegalArgumentException(nombresResult.message))
        }

        return runCatching { repository.upsert(amonestacion) }
    }
}