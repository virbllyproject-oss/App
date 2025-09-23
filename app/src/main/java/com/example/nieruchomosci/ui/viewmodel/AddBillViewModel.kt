package com.example.nieruchomosci.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nieruchomosci.data.db.dao.UserBillDao
import com.example.nieruchomosci.data.db.entity.UserBillEntity
import kotlinx.coroutines.launch

class AddBillViewModel(private val userBillDao: UserBillDao) : ViewModel() {

    fun addBill(description: String, amount: Double, category: String) {
        viewModelScope.launch {
            val bill = UserBillEntity(
                description = description,
                amount = amount,
                date = System.currentTimeMillis(), // Use current time for simplicity
                category = category
            )
            userBillDao.insert(bill)
        }
    }
}

class AddBillViewModelFactory(private val userBillDao: UserBillDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBillViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddBillViewModel(userBillDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
