package com.nieruchomosci.app.ui.screens.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nieruchomosci.app.ui.viewmodels.FormViewModel

@Composable
fun MunicipalitySelectionScreen(
    viewModel: FormViewModel,
    onMunicipalitySelected: (String) -> Unit
) {
    val municipalities = listOf(
        "Miasto Grybów",
        "Gmina Krynica-Zdrój",
        "Gmina Muszyna",
        "Gmina Piwniczna-Zdrój",
        "Gmina Stary Sącz"
    )
    val selectedMunicipality by viewModel.selectedMunicipality.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Wybierz swoją gminę",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(municipalities) { municipality ->
                Text(
                    text = municipality,
                    style = MaterialTheme.typography.titleLarge,
                    color = if (municipality == selectedMunicipality) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onMunicipalitySelected(municipality) }
                        .padding(vertical = 12.dp)
                )
            }
        }
    }
}