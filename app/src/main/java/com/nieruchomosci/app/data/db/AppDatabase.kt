package com.nieruchomosci.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nieruchomosci.app.data.db.dao.*
import com.nieruchomosci.app.data.db.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        UserProfileEntity::class,
        PropertyEntity::class,
        HouseholdEntity::class,
        VehicleEntity::class,
        MunicipalityRateEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userProfileDao(): UserProfileDao
    abstract fun propertyDao(): PropertyDao
    abstract fun householdDao(): HouseholdDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun municipalityRateDao(): MunicipalityRateDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nieruchomosci_asystent_database"
                )
                .addCallback(AppDatabaseCallback(CoroutineScope(Dispatchers.IO)))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(private val scope: CoroutineScope) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.municipalityRateDao())
                }
            }
        }

        suspend fun populateDatabase(rateDao: MunicipalityRateDao) {
            val rates = mutableListOf<MunicipalityRateEntity>()

            // Real data for Miasto Grybów (based on 2025 rates found)
            rates.add(MunicipalityRateEntity(municipalityName = "Miasto Grybów", rateName = "property_tax_residential", description = "Podatek od budynków mieszkalnych", value = 1.15, unit = "PLN_per_sq_meter"))
            rates.add(MunicipalityRateEntity(municipalityName = "Miasto Grybów", rateName = "property_tax_business", description = "Podatek od budynków związanych z działalnością gospodarczą", value = 33.10, unit = "PLN_per_sq_meter"))
            rates.add(MunicipalityRateEntity(municipalityName = "Miasto Grybów", rateName = "waste_segregated", description = "Opłata za odpady (segregowane)", value = 25.00, unit = "PLN_per_person"))

            // Placeholder data for Krynica-Zdrój
            rates.add(MunicipalityRateEntity(municipalityName = "Gmina Krynica-Zdrój", rateName = "property_tax_residential", description = "Podatek od budynków mieszkalnych", value = 1.10, unit = "PLN_per_sq_meter"))
            rates.add(MunicipalityRateEntity(municipalityName = "Gmina Krynica-Zdrój", rateName = "property_tax_business", description = "Podatek od budynków związanych z działalnością gospodarczą", value = 32.50, unit = "PLN_per_sq_meter"))
            rates.add(MunicipalityRateEntity(municipalityName = "Gmina Krynica-Zdrój", rateName = "waste_segregated", description = "Opłata za odpady (segregowane)", value = 28.00, unit = "PLN_per_person"))

            rateDao.insertAll(rates)
        }
    }
}