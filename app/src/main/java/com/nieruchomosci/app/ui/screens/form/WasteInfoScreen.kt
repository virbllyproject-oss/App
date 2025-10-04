package com.nieruchomosci.app.ui.screens.form

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nieruchomosci.app.ui.viewmodels.FormViewModel

@Composable
fun WasteInfoScreen(
    viewModel: FormViewModel,
    onNextClicked: () -> Unit
) {
    val segregatesWaste by viewModel.segregatesWaste.collectAsState()
    val hasComposter by viewModel.hasComposter.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Odpady komunalne", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Czy odpady są segregowane?", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Switch(checked = segregatesWaste, onCheckedChange = viewModel::onSegregatesWasteChange)
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Czy posiadasz kompostownik?", modifier = Modifier.weight(1f), style = MaterialTheme.typography.bodyLarge)
            Switch(checked = hasComposter, onCheckedChange = viewModel::onHasComposterChange)
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = onNextClicked, modifier = Modifier.fillMaxWidth()) {
            Text("Dalej")
        }
    }
}