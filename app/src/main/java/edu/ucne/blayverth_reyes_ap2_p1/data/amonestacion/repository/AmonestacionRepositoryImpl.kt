package edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.repository

import edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.local.AmonestacionDao
import edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.mapper.toDomain
import edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.mapper.toEntity
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.model.Amonestacion
import edu.ucne.blayverth_reyes_ap2_p1.domain.amonestacion.repository.AmonestacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AmonestacionRepositoryImpl @Inject constructor(private val localDataSource: AmonestacionDao): AmonestacionRepository {
    override fun observeAmonestaciones(): Flow<List<Amonestacion>> {
        return localDataSource.observe().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getAmonestacion(id: Int): Amonestacion? {
        return localDataSource.get(id)?.toDomain()
    }

    override suspend fun upsert(amonestacion: Amonestacion): Int {
        localDataSource.upsert(amonestacion.toEntity())
        return amonestacion.amonestacionId ?: 0
    }

    override suspend fun delete(id: Int) {
        localDataSource.delete(id)
    }

    override suspend fun exists(id: Int): Boolean {
        return localDataSource.exists(id)
    }

}