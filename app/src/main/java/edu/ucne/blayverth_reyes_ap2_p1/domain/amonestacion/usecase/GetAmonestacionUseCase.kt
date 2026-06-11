package edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase

import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import javax.inject.Inject

class GetAmonestacionUseCase @Inject constructor(private val repository: AmonestacionRepository) {
    suspend operator fun invoke(id: Int): Amonestacion? = repository.getAmonestacion(id)
}