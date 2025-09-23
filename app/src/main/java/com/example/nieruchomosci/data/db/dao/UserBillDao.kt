package com.example.nieruchomosci.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nieruchomosci.data.db.entity.UserBillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserBillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(bill: UserBillEntity)

    @Query("SELECT * FROM user_bills ORDER BY date DESC")
    fun getAllBills(): Flow<List<UserBillEntity>>

    @Delete
    suspend fun delete(bill: UserBillEntity)
}
