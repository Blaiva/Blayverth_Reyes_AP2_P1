package edu.ucne.blayverth_reyes_ap2_p1.data.borrame.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "borrame")
data class BorrameEntity(
    @PrimaryKey(autoGenerate = true)
    val borrameId: Int
)