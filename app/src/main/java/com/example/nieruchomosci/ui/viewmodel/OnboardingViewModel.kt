package com.example.nieruchomosci.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nieruchomosci.data.db.dao.MunicipalityDao
import com.example.nieruchomosci.data.db.entity.MunicipalityEntity
import kotlinx.coroutines.flow.Flow

class OnboardingViewModel(private val municipalityDao: MunicipalityDao) : ViewModel() {

    val allMunicipalities: Flow<List<MunicipalityEntity>> = municipalityDao.getAll()

}

class OnboardingViewModelFactory(private val municipalityDao: MunicipalityDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnboardingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OnboardingViewModel(municipalityDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
