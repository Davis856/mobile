package com.app.expensetracker.models
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

import java.time.LocalDate

@Parcelize
data class Expense(
    val id: String,
    val name: String,
    val description: String?,
    val amount: Double,
    val timestamp: LocalDate
) : Parcelable
