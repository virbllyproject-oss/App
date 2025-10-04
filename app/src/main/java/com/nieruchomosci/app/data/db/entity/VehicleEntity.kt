package com.nieruchomosci.app.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "vehicles",
    foreignKeys = [
        ForeignKey(
            entity = UserProfileEntity::class,
            parentColumns = ["profileId"],
            childColumns = ["userProfileId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true)
    val vehicleId: Long = 0,
    val userProfileId: Long,
    val make: String,
    val model: String,
    val productionYear: Int,
    val fuelType: String, // "benzyna", "diesel", etc.
    val isUsedForBusiness: Boolean
)