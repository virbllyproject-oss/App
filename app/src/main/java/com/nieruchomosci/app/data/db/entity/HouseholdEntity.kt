package com.nieruchomosci.app.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "households",
    foreignKeys = [
        ForeignKey(
            entity = UserProfileEntity::class,
            parentColumns = ["profileId"],
            childColumns = ["userProfileId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class HouseholdEntity(
    @PrimaryKey(autoGenerate = true)
    val householdId: Long = 0,
    val userProfileId: Long,
    val numberOfPeople: Int,
    val annualWaterConsumption: Double?, // m³, nullable
    val hasHotWaterMeter: Boolean,
    val annualElectricityConsumption: Double?, // kWh, nullable
    val heatingSource: String, // "gaz", "prąd", "inne"
    val segregatesWaste: Boolean,
    val hasComposter: Boolean
)