package com.nieruchomosci.app.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "properties",
    foreignKeys = [
        ForeignKey(
            entity = UserProfileEntity::class,
            parentColumns = ["profileId"],
            childColumns = ["userProfileId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class PropertyEntity(
    @PrimaryKey(autoGenerate = true)
    val propertyId: Long = 0,
    val userProfileId: Long,
    val houseArea: Double, // in square meters
    val landArea: Double, // in square meters
    val propertyType: String // "mieszkalna jednorodzinna", "letniskowa", etc.
)