package com.example.nieruchomosci.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.nieruchomosci.data.db.entity.UserBillEntity
import com.example.nieruchomosci.ui.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    onAddBillClick: () -> Unit
) {
    val bills by viewModel.allBills.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddBillClick) {
                Icon(Icons.Filled.Add, contentDescription = "Dodaj rachunek")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Podsumowanie", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            BillsChart(bills = bills)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Twoje rachunki", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))

            if (bills.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Brak rachunków. Dodaj pierwszy, klikając przycisk '+'")
                }
            } else {
                LazyColumn {
                    items(bills) { bill ->
                        BillRow(bill = bill)
                    }
                }
            }
        }
    }
}

@Composable
fun BillsChart(bills: List<UserBillEntity>) {
    if (bills.isEmpty()) return

    val spendingByCategory = bills.groupBy { it.category }
        .mapValues { (_, billsInCategory) -> billsInCategory.sumOf { it.amount } }

    val maxSpending = spendingByCategory.values.maxOrNull() ?: 1.0

    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            spendingByCategory.forEach { (category, total) ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(category, modifier = Modifier.weight(0.3f))
                    Row(modifier = Modifier.weight(0.7f).height(20.dp).background(MaterialTheme.colorScheme.surfaceVariant)) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth((total / maxSpending).toFloat())
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun BillRow(bill: UserBillEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(bill.description, style = MaterialTheme.typography.bodyLarge)
                Text(bill.category, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                "${String.format("%.2f", bill.amount)} zł",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
