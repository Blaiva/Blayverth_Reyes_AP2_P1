package edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.repository

import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion
import kotlinx.coroutines.flow.Flow

interface AmonestacionRepository {
    fun observeAmonestaciones(): Flow<List<Amonestacion>>
    suspend fun getAmonestacion(id: Int): Amonestacion?
    suspend fun upsert(amonestacion: Amonestacion): Int
    suspend fun delete(id: Int)
    suspend fun exists(id: Int): Boolean
}