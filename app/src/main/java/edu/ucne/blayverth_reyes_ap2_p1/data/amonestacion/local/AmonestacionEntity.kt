package edu.ucne.blayverth_reyes_ap2_p1.data.amonestacion.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "amonestacion")
data class AmonestacionEntity(
    @PrimaryKey(autoGenerate = true)
    val amonestacionId: Int,
    val nombres: String,
    val razon: String,
    val monto: Double
)