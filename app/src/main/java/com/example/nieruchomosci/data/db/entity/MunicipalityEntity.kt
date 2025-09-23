package com.example.nieruchomosci.data.db.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "municipalities",
    indices = [Index(value = ["name"], unique = true)]
)
data class MunicipalityEntity(
    @PrimaryKey(autoGenerate = true)
    val municipalityId: Long = 0,
    val name: String
)
