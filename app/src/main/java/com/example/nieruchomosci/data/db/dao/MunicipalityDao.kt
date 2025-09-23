package com.example.nieruchomosci.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nieruchomosci.data.db.entity.MunicipalityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MunicipalityDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(municipalities: List<MunicipalityEntity>)

    @Query("SELECT * FROM municipalities ORDER BY name ASC")
    fun getAll(): Flow<List<MunicipalityEntity>>

    @Query("SELECT * FROM municipalities WHERE municipalityId = :id")
    suspend fun getById(id: Long): MunicipalityEntity?
}
