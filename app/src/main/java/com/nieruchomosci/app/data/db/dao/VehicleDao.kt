package com.nieruchomosci.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nieruchomosci.app.data.db.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: VehicleEntity)

    @Update
    suspend fun update(vehicle: VehicleEntity)

    @Query("SELECT * FROM vehicles WHERE userProfileId = :profileId")
    fun getVehiclesForProfile(profileId: Long): Flow<List<VehicleEntity>>
}