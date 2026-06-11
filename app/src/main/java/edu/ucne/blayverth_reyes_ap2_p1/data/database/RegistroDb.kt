package edu.ucne.blayverth_reyes_ap2_p1.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.local.AmonestacionDao
import edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.local.AmonestacionEntity

@Database(
    entities = [AmonestacionEntity::class],
    version = 2
)
abstract class RegistroDb : RoomDatabase() {
    abstract fun amonestacionDao(): AmonestacionDao
}