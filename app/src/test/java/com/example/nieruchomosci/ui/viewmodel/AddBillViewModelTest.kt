package com.example.nieruchomosci.ui.viewmodel

import com.example.nieruchomosci.data.db.dao.UserBillDao
import com.example.nieruchomosci.data.db.entity.UserBillEntity
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

class AddBillViewModelTest {

    private lateinit var viewModel: AddBillViewModel
    private val userBillDao: UserBillDao = mockk(relaxed = true)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = AddBillViewModel(userBillDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `addBill should call insert on dao with correct bill entity`() = runTest {
        // Given
        val description = "Test Bill"
        val amount = 123.45
        val category = "Test Category"

        // When
        viewModel.addBill(description, amount, category)

        // Then
        coVerify {
            userBillDao.insert(
                any<UserBillEntity>()
            )
        }
    }
}
