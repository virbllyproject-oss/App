package com.example.nieruchomosci.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserPreferencesRepository(context: Context) {

    private val dataStore = context.dataStore

    companion object {
        val SELECTED_MUNICIPALITY_ID = longPreferencesKey("selected_municipality_id")
    }

    val selectedMunicipalityId: Flow<Long?> = dataStore.data.map { preferences ->
        preferences[SELECTED_MUNICIPALITY_ID]
    }

    suspend fun saveSelectedMunicipalityId(id: Long) {
        dataStore.edit { settings ->
            settings[SELECTED_MUNICIPALITY_ID] = id
        }
    }
}
