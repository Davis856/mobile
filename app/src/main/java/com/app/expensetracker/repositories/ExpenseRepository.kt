package com.app.expensetracker.repositories

import androidx.lifecycle.LiveData
import com.app.expensetracker.models.Expense

interface ExpenseRepository {
    fun getExpenses(): List<Expense>
    fun getExpenseById(id: String): Expense?
    fun addExpense(expense: Expense)
    fun updateExpense(updatedExpense: Expense)
    fun deleteExpense(id: String)
    fun getExpensesLiveData(): LiveData<List<Expense>>
}