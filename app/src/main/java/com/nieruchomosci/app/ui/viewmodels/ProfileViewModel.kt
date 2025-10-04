package com.nieruchomosci.app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nieruchomosci.app.data.repository.UserRepository

// Note: For a real app, loading the profile data would be more complex,
// likely involving Flows and observing the database.
// For this step, we'll focus on the concept and the UI structure.
// We'll assume this ViewModel can load and save data via the repository.

class ProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    // This ViewModel would be populated with functions to load existing user data
    // and save the updated data, similar to FormViewModel but for editing.
    // For example:
    // fun loadProfile() { ... }
    // fun updateProfile() { ... }
}

class ProfileViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}