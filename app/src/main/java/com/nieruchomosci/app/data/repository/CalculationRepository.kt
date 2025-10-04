package com.nieruchomosci.app.data.repository

import com.nieruchomosci.app.data.db.dao.HouseholdDao
import com.nieruchomosci.app.data.db.dao.MunicipalityRateDao
import com.nieruchomosci.app.data.db.dao.PropertyDao
import com.nieruchomosci.app.data.db.dao.UserProfileDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class CalculationRepository(
    private val profileDao: UserProfileDao,
    private val propertyDao: PropertyDao,
    private val householdDao: HouseholdDao,
    private val rateDao: MunicipalityRateDao
) {
    // This is a simplified calculation logic for now
    suspend fun calculateTotalAnnualCost(): Double {
        val profile = profileDao.getProfile().first() ?: return 0.0
        val property = propertyDao.getPropertiesForProfile(profile.profileId).first().firstOrNull() ?: return 0.0
        val household = householdDao.getHouseholdForProfile(profile.profileId).first() ?: return 0.0
        val rates = rateDao.getRatesForMunicipality(profile.selectedMunicipalityName).first()

        var totalCost = 0.0

        // 1. Calculate property tax
        val residentialTaxRate = rates.find { it.rateName == "property_tax_residential" }?.value ?: 0.0
        totalCost += property.houseArea * residentialTaxRate

        // 2. Calculate waste disposal fee
        val wasteRate = rates.find { it.rateName == "waste_segregated" }?.value ?: 0.0
        totalCost += household.numberOfPeople * wasteRate * 12 // Annual cost

        // ... more calculations can be added here in the future

        return totalCost
    }
}