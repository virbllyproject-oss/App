package com.nieruchomosci.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.nieruchomosci.app.data.db.AppDatabase
import com.nieruchomosci.app.data.repository.CalculationRepository
import com.nieruchomosci.app.data.repository.UserRepository
import com.nieruchomosci.app.ui.screens.form.FormNavigator
import com.nieruchomosci.app.ui.screens.main.MainScreen
import com.nieruchomosci.app.ui.theme.NieruchomosciAsystentTheme
import com.nieruchomosci.app.ui.viewmodels.FormViewModel
import com.nieruchomosci.app.ui.viewmodels.FormViewModelFactory
import com.nieruchomosci.app.ui.viewmodels.HomeViewModel
import com.nieruchomosci.app.ui.viewmodels.HomeViewModelFactory

class MainActivity : ComponentActivity() {

    // --- Database and Repositories ---
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val userRepository by lazy {
        UserRepository(
            profileDao = database.userProfileDao(),
            propertyDao = database.propertyDao(),
            householdDao = database.householdDao(),
            vehicleDao = database.vehicleDao()
        )
    }
    private val calculationRepository by lazy {
        CalculationRepository(
            profileDao = database.userProfileDao(),
            propertyDao = database.propertyDao(),
            householdDao = database.householdDao(),
            rateDao = database.municipalityRateDao()
        )
    }

    // --- ViewModel Factories and Instances ---
    private val formViewModelFactory by lazy { FormViewModelFactory(userRepository) }
    private val homeViewModelFactory by lazy { HomeViewModelFactory(calculationRepository) }

    private val formViewModel: FormViewModel by viewModels { formViewModelFactory }
    private val homeViewModel: HomeViewModel by viewModels { homeViewModelFactory }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NieruchomosciAsystentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isFormCompleted by remember { mutableStateOf(false) }

                    if (isFormCompleted) {
                        MainScreen(homeViewModel = homeViewModel, formViewModel = formViewModel)
                    } else {
                        FormNavigator(
                            factory = formViewModelFactory,
                            onFormFinished = {
                                isFormCompleted = true
                            }
                        )
                    }
                }
            }
        }
    }
}