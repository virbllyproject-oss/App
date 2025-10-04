package com.nieruchomosci.app.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssistantScreen() {
    var text by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Napisz do asystenta...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Asystent AI",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Wkrótce w tym miejscu będziesz mógł porozmawiać z inteligentnym asystentem na temat swoich finansów i podatków.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}