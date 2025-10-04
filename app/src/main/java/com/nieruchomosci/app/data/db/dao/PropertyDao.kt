package com.nieruchomosci.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nieruchomosci.app.data.db.entity.PropertyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(property: PropertyEntity)

    @Update
    suspend fun update(property: PropertyEntity)

    @Query("SELECT * FROM properties WHERE userProfileId = :profileId")
    fun getPropertiesForProfile(profileId: Long): Flow<List<PropertyEntity>>
}