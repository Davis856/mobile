package com.app.expensetracker.utils

import com.app.expensetracker.repositories.ExpenseRepository
import com.app.expensetracker.repositories.ExpenseRepositoryImpl

object RepositoryProvider {
    val categoryRepository: CategoryRepository = CategoryRepositoryImpl()
    val expenseRepository: ExpenseRepository = ExpenseRepositoryImpl()
}
