package com.nieruchomosci.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nieruchomosci.app.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FormViewModel(private val userRepository: UserRepository) : ViewModel() {

    // ... (all the StateFlows remain the same)
    private val _selectedMunicipality = MutableStateFlow("")
    val selectedMunicipality = _selectedMunicipality.asStateFlow()
    private val _numberOfPeople = MutableStateFlow("")
    val numberOfPeople = _numberOfPeople.asStateFlow()
    private val _annualWaterConsumption = MutableStateFlow("")
    val annualWaterConsumption = _annualWaterConsumption.asStateFlow()
    private val _hasHotWaterMeter = MutableStateFlow(false)
    val hasHotWaterMeter = _hasHotWaterMeter.asStateFlow()
    private val _annualElectricityConsumption = MutableStateFlow("")
    val annualElectricityConsumption = _annualElectricityConsumption.asStateFlow()
    private val _heatingSource = MutableStateFlow("")
    val heatingSource = _heatingSource.asStateFlow()
    private val _houseArea = MutableStateFlow("")
    val houseArea = _houseArea.asStateFlow()
    private val _landArea = MutableStateFlow("")
    val landArea = _landArea.asStateFlow()
    private val _propertyType = MutableStateFlow("")
    val propertyType = _propertyType.asStateFlow()
    private val _segregatesWaste = MutableStateFlow(true)
    val segregatesWaste = _segregatesWaste.asStateFlow()
    private val _hasComposter = MutableStateFlow(false)
    val hasComposter = _hasComposter.asStateFlow()
    private val _hasCar = MutableStateFlow(false)
    val hasCar = _hasCar.asStateFlow()
    private val _carMake = MutableStateFlow("")
    val carMake = _carMake.asStateFlow()
    private val _carModel = MutableStateFlow("")
    val carModel = _carModel.asStateFlow()
    private val _carProductionYear = MutableStateFlow("")
    val carProductionYear = _carProductionYear.asStateFlow()
    private val _carFuelType = MutableStateFlow("")
    val carFuelType = _carFuelType.asStateFlow()
    private val _isCarUsedForBusiness = MutableStateFlow(false)
    val isCarUsedForBusiness = _isCarUsedForBusiness.asStateFlow()


    // --- Update Functions ---
    fun onMunicipalityChange(newValue: String) { _selectedMunicipality.value = newValue }
    fun onNumberOfPeopleChange(newValue: String) { _numberOfPeople.value = newValue }
    fun onAnnualWaterConsumptionChange(newValue: String) { _annualWaterConsumption.value = newValue }
    fun onHasHotWaterMeterChange(newValue: Boolean) { _hasHotWaterMeter.value = newValue }
    fun onAnnualElectricityConsumptionChange(newValue: String) { _annualElectricityConsumption.value = newValue }
    fun onHeatingSourceChange(newValue: String) { _heatingSource.value = newValue }
    fun onHouseAreaChange(newValue: String) { _houseArea.value = newValue }
    fun onLandAreaChange(newValue: String) { _landArea.value = newValue }
    fun onPropertyTypeChange(newValue: String) { _propertyType.value = newValue }
    fun onSegregatesWasteChange(newValue: Boolean) { _segregatesWaste.value = newValue }
    fun onHasComposterChange(newValue: Boolean) { _hasComposter.value = newValue }
    fun onHasCarChange(newValue: Boolean) { _hasCar.value = newValue }
    fun onCarMakeChange(newValue: String) { _carMake.value = newValue }
    fun onCarModelChange(newValue: String) { _carModel.value = newValue }
    fun onCarProductionYearChange(newValue: String) { _carProductionYear.value = newValue }
    fun onCarFuelTypeChange(newValue: String) { _carFuelType.value = newValue }
    fun onIsCarUsedForBusinessChange(newValue: Boolean) { _isCarUsedForBusiness.value = newValue }

    // --- Save Function ---
    fun saveProfile() {
        viewModelScope.launch {
            userRepository.saveCompleteProfile(this@FormViewModel)
        }
    }
}

// Factory to provide the UserRepository to the FormViewModel
class FormViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FormViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}