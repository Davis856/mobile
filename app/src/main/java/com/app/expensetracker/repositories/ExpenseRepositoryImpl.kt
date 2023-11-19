package com.app.expensetracker.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.expensetracker.models.Expense

class ExpenseRepositoryImpl : ExpenseRepository {
    private val expenses = MutableLiveData<List<Expense>>(emptyList())

    override fun getExpenses(): List<Expense> {
        return expenses.value.orEmpty()
    }

    override fun getExpenseById(id: String): Expense? {
        return expenses.value?.find { it.id == id }
    }

    override fun addExpense(expense: Expense) {
        val updatedList = expenses.value.orEmpty().toMutableList()
        updatedList.add(expense)
        expenses.value = updatedList
    }

    override fun updateExpense(updatedExpense: Expense) {
        val updatedList = expenses.value.orEmpty().toMutableList()
        val index = updatedList.indexOfFirst { it.id == updatedExpense.id }
        if (index != -1) {
            updatedList[index] = updatedExpense
            expenses.value = updatedList
        }
    }

    override fun deleteExpense(id: String) {
        val updatedList = expenses.value.orEmpty().toMutableList()
        updatedList.removeIf { it.id == id }
        expenses.value = updatedList
    }

    override fun getExpensesLiveData(): LiveData<List<Expense>> = expenses
}