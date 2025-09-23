package com.example.nieruchomosci.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nieruchomosci.data.db.dao.UserBillDao
import com.example.nieruchomosci.data.db.entity.UserBillEntity
import com.example.nieruchomosci.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(
    private val userPreferencesRepository: UserPreferencesRepository,
    private val userBillDao: UserBillDao
) : ViewModel() {

    val selectedMunicipalityId: StateFlow<Long?> = userPreferencesRepository.selectedMunicipalityId
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    val allBills: Flow<List<UserBillEntity>> = userBillDao.getAllBills()

    fun saveMunicipalitySelection(id: Long) {
        viewModelScope.launch {
            userPreferencesRepository.saveSelectedMunicipalityId(id)
        }
    }
}

class MainViewModelFactory(
    private val repository: UserPreferencesRepository,
    private val userBillDao: UserBillDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository, userBillDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
