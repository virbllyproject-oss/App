package com.nieruchomosci.app.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nieruchomosci.app.data.db.entity.MunicipalityRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MunicipalityRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rates: List<MunicipalityRateEntity>)

    @Query("SELECT * FROM municipality_rates WHERE municipalityName = :municipalityName")
    fun getRatesForMunicipality(municipalityName: String): Flow<List<MunicipalityRateEntity>>
}