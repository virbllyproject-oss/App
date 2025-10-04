package com.nieruchomosci.app.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "municipality_rates")
data class MunicipalityRateEntity(
    @PrimaryKey(autoGenerate = true)
    val rateId: Long = 0,
    val municipalityName: String, // The name of the municipality this rate applies to
    val rateName: String, // A unique name for the rate, e.g., "property_tax_residential"
    val description: String, // A user-friendly description, e.g., "Podatek od budynków mieszkalnych"
    val value: Double,
    val unit: String // e.g., "PLN_per_sq_meter", "PLN_per_person"
)