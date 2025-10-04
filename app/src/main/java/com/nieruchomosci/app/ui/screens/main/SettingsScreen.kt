package com.nieruchomosci.app.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(onProfileClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Ustawienia",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SettingsItem(title = "Profil", onClick = onProfileClick)
        SettingsItem(title = "Kontakt z twórcą") { /* TODO: Handle contact */ }
        SettingsItem(title = "Oceń nas w Google Play") { /* TODO: Handle rating */ }
    }
}

@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Column(modifier = Modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = title, style = MaterialTheme.typography.titleLarge)
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Przejdź do $title",
                modifier = Modifier.size(16.dp)
            )
        }
        Divider()
    }
}