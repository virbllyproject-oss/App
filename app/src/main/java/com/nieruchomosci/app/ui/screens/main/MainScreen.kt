package com.nieruchomosci.app.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nieruchomosci.app.ui.navigation.AppScreen
import com.nieruchomosci.app.ui.navigation.bottomNavItems
import com.nieruchomosci.app.ui.viewmodels.FormViewModel
import com.nieruchomosci.app.ui.viewmodels.HomeViewModel

const val PROFILE_EDIT_ROUTE = "profile_edit_route"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(homeViewModel: HomeViewModel, formViewModel: FormViewModel) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.label) },
                        label = { Text(screen.label) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(navController, startDestination = AppScreen.Home.route, Modifier.padding(innerPadding)) {
            composable(AppScreen.Home.route) { HomeScreen(viewModel = homeViewModel) }
            composable(AppScreen.Invoices.route) { InvoicesScreen() }
            composable(AppScreen.Assistant.route) { AssistantScreen() }

            // Pass NavController to SettingsScreen
            composable(AppScreen.Settings.route) {
                SettingsScreen(onProfileClick = {
                    navController.navigate(PROFILE_EDIT_ROUTE)
                })
            }

            // Add the new destination for the profile edit screen
            composable(PROFILE_EDIT_ROUTE) {
                ProfileScreen(
                    viewModel = formViewModel,
                    onSaveClicked = {
                        // Logic to save and navigate back
                        formViewModel.saveProfile()
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}