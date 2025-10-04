package com.nieruchomosci.app.ui.screens.form

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nieruchomosci.app.ui.viewmodels.FormViewModel
import com.nieruchomosci.app.ui.viewmodels.FormViewModelFactory

// This function will be the main entry point for the form,
// and it will require the factory to be passed from MainActivity.
@Composable
fun FormNavigator(
    factory: FormViewModelFactory,
    onFormFinished: () -> Unit // A new lambda to signal that the form is complete
) {
    val viewModel: FormViewModel = viewModel(factory = factory)
    var currentStep by remember { mutableStateOf<FormStep>(FormStep.MunicipalitySelection) }

    when (currentStep) {
        is FormStep.MunicipalitySelection -> {
            MunicipalitySelectionScreen(
                viewModel = viewModel,
                onMunicipalitySelected = { selected ->
                    viewModel.onMunicipalityChange(selected)
                    currentStep = FormStep.HouseholdInfo
                }
            )
        }
        is FormStep.HouseholdInfo -> {
            HouseholdInfoScreen(
                viewModel = viewModel,
                onNextClicked = {
                    currentStep = FormStep.PropertyInfo
                }
            )
        }
        is FormStep.PropertyInfo -> {
            PropertyInfoScreen(
                viewModel = viewModel,
                onNextClicked = {
                    currentStep = FormStep.WasteInfo
                }
            )
        }
        is FormStep.WasteInfo -> {
            WasteInfoScreen(
                viewModel = viewModel,
                onNextClicked = {
                    currentStep = FormStep.VehicleInfo
                }
            )
        }
        is FormStep.VehicleInfo -> {
            VehicleInfoScreen(
                viewModel = viewModel,
                onFinishClicked = {
                    viewModel.saveProfile()
                    onFormFinished() // Signal that the form is done
                }
            )
        }
    }
}
// Note: I removed the sealed class from here as it's better to have it in a separate file
// or at the top level if it's used across different files. For now, keeping it here is fine.
sealed class FormStep {
    object MunicipalitySelection : FormStep()
    object HouseholdInfo : FormStep()
    object PropertyInfo : FormStep()
    object WasteInfo : FormStep()
    object VehicleInfo : FormStep()
}