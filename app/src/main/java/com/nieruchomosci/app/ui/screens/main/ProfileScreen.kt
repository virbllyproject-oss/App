package com.nieruchomosci.app.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nieruchomosci.app.ui.viewmodels.FormViewModel

@Composable
fun ProfileScreen(
    viewModel: FormViewModel,
    onSaveClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Edytuj Profil", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        // Household Info Section
        Text("Gospodarstwo domowe", style = MaterialTheme.typography.titleLarge)
        HouseholdInfoForm(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        // Property Info Section
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nieruchomość", style = MaterialTheme.typography.titleLarge)
        PropertyInfoForm(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        // Waste Info Section
        Spacer(modifier = Modifier.height(16.dp))
        Text("Odpady", style = MaterialTheme.typography.titleLarge)
        WasteInfoForm(viewModel)
        Spacer(modifier = Modifier.height(16.dp))
        Divider()

        // Vehicle Info Section
        Spacer(modifier = Modifier.height(16.dp))
        Text("Pojazdy", style = MaterialTheme.typography.titleLarge)
        VehicleInfoForm(viewModel)

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onSaveClicked, modifier = Modifier.fillMaxWidth()) {
            Text("Zapisz zmiany")
        }
    }
}

@Composable
private fun HouseholdInfoForm(viewModel: FormViewModel) {
    val numberOfPeople by viewModel.numberOfPeople.collectAsState()
    val annualWaterConsumption by viewModel.annualWaterConsumption.collectAsState()
    val hasHotWaterMeter by viewModel.hasHotWaterMeter.collectAsState()

    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(value = numberOfPeople, onValueChange = viewModel::onNumberOfPeopleChange, label = { Text("Liczba osób") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(value = annualWaterConsumption, onValueChange = viewModel::onAnnualWaterConsumptionChange, label = { Text("Zużycie wody (m³)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth())
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Licznik ciepłej wody?", modifier = Modifier.weight(1f))
        Switch(checked = hasHotWaterMeter, onCheckedChange = viewModel::onHasHotWaterMeterChange)
    }
}

@Composable
private fun PropertyInfoForm(viewModel: FormViewModel) {
    val houseArea by viewModel.houseArea.collectAsState()
    val landArea by viewModel.landArea.collectAsState()

    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(value = houseArea, onValueChange = viewModel::onHouseAreaChange, label = { Text("Powierzchnia domu (m²)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(8.dp))
    OutlinedTextField(value = landArea, onValueChange = viewModel::onLandAreaChange, label = { Text("Powierzchnia działki (m²)") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal), modifier = Modifier.fillMaxWidth())
}

@Composable
private fun WasteInfoForm(viewModel: FormViewModel) {
    val segregatesWaste by viewModel.segregatesWaste.collectAsState()
    val hasComposter by viewModel.hasComposter.collectAsState()

    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Odpady segregowane?", modifier = Modifier.weight(1f))
        Switch(checked = segregatesWaste, onCheckedChange = viewModel::onSegregatesWasteChange)
    }
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Posiada kompostownik?", modifier = Modifier.weight(1f))
        Switch(checked = hasComposter, onCheckedChange = viewModel::onHasComposterChange)
    }
}

@Composable
private fun VehicleInfoForm(viewModel: FormViewModel) {
    val hasCar by viewModel.hasCar.collectAsState()
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Posiada samochód?", modifier = Modifier.weight(1f))
        Switch(checked = hasCar, onCheckedChange = viewModel::onHasCarChange)
    }
    if (hasCar) {
        // Details form can be added here
    }
}