package com.nieruchomosci.app.ui.screens.form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.nieruchomosci.app.ui.viewmodels.FormViewModel

@Composable
fun PropertyInfoScreen(
    viewModel: FormViewModel,
    onNextClicked: () -> Unit
) {
    val houseArea by viewModel.houseArea.collectAsState()
    val landArea by viewModel.landArea.collectAsState()
    val propertyType by viewModel.propertyType.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text("Informacje o nieruchomości", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = houseArea,
            onValueChange = viewModel::onHouseAreaChange,
            label = { Text("Powierzchnia domu (m²)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = landArea,
            onValueChange = viewModel::onLandAreaChange,
            label = { Text("Powierzchnia działki (m²)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = propertyType,
            onValueChange = viewModel::onPropertyTypeChange,
            label = { Text("Rodzaj nieruchomości (np. mieszkalna)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onNextClicked, modifier = Modifier.fillMaxWidth()) {
            Text("Dalej")
        }
    }
}