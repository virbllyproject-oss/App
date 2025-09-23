package com.example.nieruchomosci.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nieruchomosci.data.db.entity.FeeRateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FeeRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(feeRates: List<FeeRateEntity>)

    @Query("SELECT * FROM fee_rates WHERE municipalityId = :municipalityId ORDER BY name ASC")
    fun getRatesForMunicipality(municipalityId: Long): Flow<List<FeeRateEntity>>
}
