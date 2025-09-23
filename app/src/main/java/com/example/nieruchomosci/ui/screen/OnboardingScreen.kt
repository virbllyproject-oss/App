package com.example.nieruchomosci.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nieruchomosci.data.db.entity.MunicipalityEntity
import com.example.nieruchomosci.ui.viewmodel.OnboardingViewModel

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel,
    onMunicipalitySelected: (MunicipalityEntity) -> Unit
) {
    val municipalities by viewModel.allMunicipalities.collectAsState(initial = emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Wybierz swoją gminę",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(municipalities) { municipality ->
                MunicipalityRow(municipality = municipality, onMunicipalitySelected = onMunicipalitySelected)
            }
        }
    }
}

@Composable
fun MunicipalityRow(
    municipality: MunicipalityEntity,
    onMunicipalitySelected: (MunicipalityEntity) -> Unit
) {
    Text(
        text = municipality.name,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onMunicipalitySelected(municipality) }
            .padding(vertical = 16.dp),
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}
