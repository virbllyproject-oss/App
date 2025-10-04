package com.nieruchomosci.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppScreen(val route: String, val icon: ImageVector, val label: String) {
    object Home : AppScreen("home", Icons.Default.Home, "Home")
    object Invoices : AppScreen("invoices", Icons.Default.List, "Faktury")
    object Assistant : AppScreen("assistant", Icons.Default.Person, "Asystent")
    object Settings : AppScreen("settings", Icons.Default.Settings, "Ustawienia")
}

val bottomNavItems = listOf(
    AppScreen.Home,
    AppScreen.Invoices,
    AppScreen.Assistant,
    AppScreen.Settings
)