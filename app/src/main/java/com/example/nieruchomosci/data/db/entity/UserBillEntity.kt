package com.example.nieruchomosci.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_bills")
data class UserBillEntity(
    @PrimaryKey(autoGenerate = true)
    val billId: Long = 0,
    val description: String,
    val amount: Double,
    val date: Long, // Storing date as Long (timestamp)
    val category: String // e.g., "Woda", "Prąd", "Gaz"
)
