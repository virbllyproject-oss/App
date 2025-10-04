package com.nieruchomosci.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nieruchomosci.app.data.db.entity.HouseholdEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HouseholdDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(household: HouseholdEntity)

    @Update
    suspend fun update(household: HouseholdEntity)

    @Query("SELECT * FROM households WHERE userProfileId = :profileId LIMIT 1")
    fun getHouseholdForProfile(profileId: Long): Flow<HouseholdEntity?>
}