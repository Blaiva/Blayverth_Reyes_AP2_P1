package edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.usecase

import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAmonestacionUseCase @Inject constructor(private val repository: AmonestacionRepository) {
    operator fun invoke(): Flow<List<Amonestacion>> = repository.observeAmonestaciones()
}