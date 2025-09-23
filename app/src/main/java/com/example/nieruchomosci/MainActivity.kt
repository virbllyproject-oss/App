package com.example.nieruchomosci

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.nieruchomosci.data.db.AppDatabase
import com.example.nieruchomosci.data.repository.UserPreferencesRepository
import com.example.nieruchomosci.ui.screen.AddBillScreen
import com.example.nieruchomosci.ui.screen.MainScreen
import com.example.nieruchomosci.ui.screen.OnboardingScreen
import com.example.nieruchomosci.ui.theme.NieruchomosciTheme
import com.example.nieruchomosci.ui.viewmodel.*

// Sealed class to represent the different screens in the app
sealed class Screen {
    object Onboarding : Screen()
    object Main : Screen()
    object AddBill : Screen()
}

class MainActivity : ComponentActivity() {

    private val database by lazy { AppDatabase.getDatabase(this) }
    private val userPreferencesRepository by lazy { UserPreferencesRepository(this) }

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(userPreferencesRepository, database.userBillDao())
    }

    private val onboardingViewModel: OnboardingViewModel by viewModels {
        OnboardingViewModelFactory(database.municipalityDao())
    }

    private val addBillViewModel: AddBillViewModel by viewModels {
        AddBillViewModelFactory(database.userBillDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NieruchomosciTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator(mainViewModel, onboardingViewModel, addBillViewModel)
                }
            }
        }
    }
}

@Composable
fun AppNavigator(
    mainViewModel: MainViewModel,
    onboardingViewModel: OnboardingViewModel,
    addBillViewModel: AddBillViewModel
) {
    val selectedMunicipalityId by mainViewModel.selectedMunicipalityId.collectAsState()
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Onboarding) }

    // This effect runs when selectedMunicipalityId changes.
    // It determines the initial screen.
    LaunchedEffect(selectedMunicipalityId) {
        currentScreen = if (selectedMunicipalityId == null) {
            Screen.Onboarding
        } else {
            Screen.Main
        }
    }

    when (currentScreen) {
        is Screen.Onboarding -> {
            OnboardingScreen(viewModel = onboardingViewModel) { municipality ->
                mainViewModel.saveMunicipalitySelection(municipality.municipalityId)
                currentScreen = Screen.Main // Navigate to Main after selection
            }
        }
        is Screen.Main -> {
            MainScreen(
                viewModel = mainViewModel,
                onAddBillClick = {
                    currentScreen = Screen.AddBill // Navigate to AddBill
                }
            )
        }
        is Screen.AddBill -> {
            AddBillScreen(
                viewModel = addBillViewModel,
                onBillAdded = {
                    currentScreen = Screen.Main // Navigate back to Main
                }
            )
        }
    }
}
