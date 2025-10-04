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
fun HouseholdInfoScreen(
    viewModel: FormViewModel,
    onNextClicked: () -> Unit
) {
    val numberOfPeople by viewModel.numberOfPeople.collectAsState()
    val annualWaterConsumption by viewModel.annualWaterConsumption.collectAsState()
    val hasHotWaterMeter by viewModel.hasHotWaterMeter.collectAsState()
    val annualElectricityConsumption by viewModel.annualElectricityConsumption.collectAsState()
    val heatingSource by viewModel.heatingSource.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Informacje o gospodarstwie domowym", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = numberOfPeople,
            onValueChange = viewModel::onNumberOfPeopleChange,
            label = { Text("Liczba osób w gospodarstwie domowym") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = annualWaterConsumption,
            onValueChange = viewModel::onAnnualWaterConsumptionChange,
            label = { Text("Roczna prognoza zużycia wody (m³)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Posiadasz licznik ciepłej wody?", modifier = Modifier.weight(1f))
            Switch(checked = hasHotWaterMeter, onCheckedChange = viewModel::onHasHotWaterMeterChange)
        }
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = annualElectricityConsumption,
            onValueChange = viewModel::onAnnualElectricityConsumptionChange,
            label = { Text("Roczne zużycie prądu (kWh, opcjonalnie)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = heatingSource,
            onValueChange = viewModel::onHeatingSourceChange,
            label = { Text("Źródło ogrzewania (gaz / prąd / inne)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f)) // Pushes button to the bottom

        Button(onClick = onNextClicked, modifier = Modifier.fillMaxWidth()) {
            Text("Dalej")
        }
    }
}