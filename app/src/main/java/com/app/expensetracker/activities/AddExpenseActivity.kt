package com.app.expensetracker.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.expensetracker.R
import com.app.expensetracker.models.Expense
import com.app.expensetracker.repositories.ExpenseRepository
import com.app.expensetracker.utils.RepositoryProvider
import java.time.LocalDate
import java.util.UUID

class AddExpenseActivity: AppCompatActivity() {
    private lateinit var expenseNameEditText: EditText
    private lateinit var expenseDescriptionEditText: EditText
    private lateinit var expenseAmountEditText: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var addExpenseButton: Button

    private lateinit var expenseRepository: ExpenseRepository

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }

        expenseNameEditText = findViewById(R.id.editTextExpenseName)
        expenseDescriptionEditText = findViewById(R.id.editTextExpenseDescription)
        expenseAmountEditText = findViewById(R.id.editTextExpenseAmount)
        addExpenseButton = findViewById(R.id.buttonAddExpense)

        expenseRepository = RepositoryProvider.expenseRepository

        addExpenseButton.setOnClickListener{
            addExpense()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun addExpense() {
        val name = expenseNameEditText.text.toString()
        val description = expenseDescriptionEditText.text.toString()
        val amount = expenseAmountEditText.text.toString().toDoubleOrNull()

        if (name.isNotEmpty() && amount != null) {
            val newExpense = Expense(
                UUID.randomUUID().toString(),
                name,
                description,
                amount,
                LocalDate.now()
            )
            expenseRepository.addExpense(newExpense)

            Toast.makeText(this, "Expense added successfully!", Toast.LENGTH_SHORT).show()

            finish()
        }
    }
}