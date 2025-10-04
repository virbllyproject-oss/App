package com.nieruchomosci.app.ui.screens.form

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
fun VehicleInfoScreen(
    viewModel: FormViewModel,
    onFinishClicked: () -> Unit
) {
    val hasCar by viewModel.hasCar.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Informacje o pojazdach", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Czy posiadasz samochód?", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Switch(checked = hasCar, onCheckedChange = viewModel::onHasCarChange)
        }

        if (hasCar) {
            VehicleDetailsForm(viewModel)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onFinishClicked, modifier = Modifier.fillMaxWidth()) {
            Text("Zakończ")
        }
    }
}

@Composable
fun VehicleDetailsForm(viewModel: FormViewModel) {
    val carMake by viewModel.carMake.collectAsState()
    val carModel by viewModel.carModel.collectAsState()
    val carProductionYear by viewModel.carProductionYear.collectAsState()
    val carFuelType by viewModel.carFuelType.collectAsState()
    val isCarUsedForBusiness by viewModel.isCarUsedForBusiness.collectAsState()

    Spacer(modifier = Modifier.height(16.dp))
    Divider()
    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(value = carMake, onValueChange = viewModel::onCarMakeChange, label = { Text("Marka") }, modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(value = carModel, onValueChange = viewModel::onCarModelChange, label = { Text("Model") }, modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(value = carProductionYear, onValueChange = viewModel::onCarProductionYearChange, label = { Text("Rok produkcji") }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number), modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedTextField(value = carFuelType, onValueChange = viewModel::onCarFuelTypeChange, label = { Text("Rodzaj paliwa") }, modifier = Modifier.fillMaxWidth())
    Spacer(modifier = Modifier.height(16.dp))
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Czy auto używane w działalności gosp.?", modifier = Modifier.weight(1f))
        Switch(checked = isCarUsedForBusiness, onCheckedChange = viewModel::onIsCarUsedForBusinessChange)
    }
}