package com.nieruchomosci.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profiles")
data class UserProfileEntity(
    @PrimaryKey(autoGenerate = true)
    val profileId: Long = 0,
    val selectedMunicipalityName: String // e.g., "Gmina Krynica-Zdrój"
)