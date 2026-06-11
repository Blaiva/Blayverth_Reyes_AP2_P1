package edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase

import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import javax.inject.Inject

class DeleteAmonestacionUseCase @Inject constructor(private val repository: AmonestacionRepository) {
    suspend operator fun invoke(id: Int) = repository.delete(id)
}