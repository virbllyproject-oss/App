package com.example.nieruchomosci.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "fee_rates",
    foreignKeys = [
        ForeignKey(
            entity = MunicipalityEntity::class,
            parentColumns = ["municipalityId"],
            childColumns = ["municipalityId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class FeeRateEntity(
    @PrimaryKey(autoGenerate = true)
    val feeRateId: Long = 0,
    val municipalityId: Long,
    val name: String, // e.g., "Podatek od budynków mieszkalnych"
    val description: String, // e.g., "Stawka roczna"
    val rate: Double, // e.g., 1.15
    val unit: String, // e.g., "zł/m²"
    val validFromYear: Int,
    val sourceDocumentUrl: String? = null
)
