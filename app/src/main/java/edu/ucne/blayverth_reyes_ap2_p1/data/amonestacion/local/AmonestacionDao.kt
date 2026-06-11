package edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AmonestacionDao {
    @Upsert
    suspend fun upsert(entity: AmonestacionEntity)

    @Query("DELETE FROM amonestacion WHERE amonestacionId = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM amonestacion WHERE amonestacionId = :id")
    suspend fun get(id: Int): AmonestacionEntity?

    @Query("SELECT * FROM amonestacion ORDER BY amonestacionId DESC")
    fun observe(): Flow<List<AmonestacionEntity>>

    @Query("SELECT 1 FROM amonestacion WHERE amonestacionId = :id")
    suspend fun exists(id: Int): Boolean
}