package com.example.nieruchomosci.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.nieruchomosci.data.db.dao.FeeRateDao
import com.example.nieruchomosci.data.db.dao.MunicipalityDao
import com.example.nieruchomosci.data.db.dao.UserBillDao
import com.example.nieruchomosci.data.db.entity.FeeRateEntity
import com.example.nieruchomosci.data.db.entity.MunicipalityEntity
import com.example.nieruchomosci.data.db.entity.UserBillEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [MunicipalityEntity::class, FeeRateEntity::class, UserBillEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun municipalityDao(): MunicipalityDao
    abstract fun feeRateDao(): FeeRateDao
    abstract fun userBillDao(): UserBillDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nieruchomosci_database"
                )
                .addCallback(AppDatabaseCallback(CoroutineScope(Dispatchers.IO)))
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.municipalityDao())
                }
            }
        }

        suspend fun populateDatabase(municipalityDao: MunicipalityDao) {
            // Pre-populate the list of municipalities in Nowy Sącz County
            val municipalities = listOf(
                MunicipalityEntity(name = "Grybów (miasto)"),
                MunicipalityEntity(name = "Krynica-Zdrój"),
                MunicipalityEntity(name = "Muszyna"),
                MunicipalityEntity(name = "Piwniczna-Zdrój"),
                MunicipalityEntity(name = "Stary Sącz"),
                MunicipalityEntity(name = "Chełmiec"),
                MunicipalityEntity(name = "Gródek nad Dunajcem"),
                MunicipalityEntity(name = "Grybów (gmina wiejska)"),
                MunicipalityEntity(name = "Kamionka Wielka"),
                MunicipalityEntity(name = "Korzenna"),
                MunicipalityEntity(name = "Łabowa"),
                MunicipalityEntity(name = "Łącko"),
                MunicipalityEntity(name = "Łososina Dolna"),
                MunicipalityEntity(name = "Nawojowa"),
                MunicipalityEntity(name = "Podegrodzie"),
                MunicipalityEntity(name = "Rytro")
            )
            municipalityDao.insertAll(municipalities)
        }
    }
}
