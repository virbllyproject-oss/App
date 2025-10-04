package com.nieruchomosci.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nieruchomosci.app.data.repository.CalculationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val calculationRepository: CalculationRepository) : ViewModel() {

    private val _totalAnnualCost = MutableStateFlow(0.0)
    val totalAnnualCost = _totalAnnualCost.asStateFlow()

    init {
        loadTotalCost()
    }

    private fun loadTotalCost() {
        viewModelScope.launch {
            _totalAnnualCost.value = calculationRepository.calculateTotalAnnualCost()
        }
    }
}

class HomeViewModelFactory(private val repository: CalculationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}