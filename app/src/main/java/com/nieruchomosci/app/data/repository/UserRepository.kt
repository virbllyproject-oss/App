package com.nieruchomosci.app.data.repository

import com.nieruchomosci.app.data.db.dao.HouseholdDao
import com.nieruchomosci.app.data.db.dao.PropertyDao
import com.nieruchomosci.app.data.db.dao.UserProfileDao
import com.nieruchomosci.app.data.db.dao.VehicleDao
import com.nieruchomosci.app.data.db.entity.HouseholdEntity
import com.nieruchomosci.app.data.db.entity.PropertyEntity
import com.nieruchomosci.app.data.db.entity.UserProfileEntity
import com.nieruchomosci.app.data.db.entity.VehicleEntity
import com.nieruchomosci.app.ui.viewmodels.FormViewModel

class UserRepository(
    private val profileDao: UserProfileDao,
    private val propertyDao: PropertyDao,
    private val householdDao: HouseholdDao,
    private val vehicleDao: VehicleDao
) {
    suspend fun saveCompleteProfile(viewModel: FormViewModel) {
        // 1. Save the main profile and get its ID
        val profile = UserProfileEntity(selectedMunicipalityName = viewModel.selectedMunicipality.value)
        val profileId = profileDao.insertProfile(profile)

        // 2. Save Property Info
        val property = PropertyEntity(
            userProfileId = profileId,
            houseArea = viewModel.houseArea.value.toDoubleOrNull() ?: 0.0,
            landArea = viewModel.landArea.value.toDoubleOrNull() ?: 0.0,
            propertyType = viewModel.propertyType.value
        )
        propertyDao.insert(property)

        // 3. Save Household Info
        val household = HouseholdEntity(
            userProfileId = profileId,
            numberOfPeople = viewModel.numberOfPeople.value.toIntOrNull() ?: 0,
            annualWaterConsumption = viewModel.annualWaterConsumption.value.toDoubleOrNull(),
            hasHotWaterMeter = viewModel.hasHotWaterMeter.value,
            annualElectricityConsumption = viewModel.annualElectricityConsumption.value.toDoubleOrNull(),
            heatingSource = viewModel.heatingSource.value,
            segregatesWaste = viewModel.segregatesWaste.value,
            hasComposter = viewModel.hasComposter.value
        )
        householdDao.insert(household)

        // 4. Save Vehicle Info (if applicable)
        if (viewModel.hasCar.value) {
            val vehicle = VehicleEntity(
                userProfileId = profileId,
                make = viewModel.carMake.value,
                model = viewModel.carModel.value,
                productionYear = viewModel.carProductionYear.value.toIntOrNull() ?: 0,
                fuelType = viewModel.carFuelType.value,
                isUsedForBusiness = viewModel.isCarUsedForBusiness.value
            )
            vehicleDao.insert(vehicle)
        }
    }
}