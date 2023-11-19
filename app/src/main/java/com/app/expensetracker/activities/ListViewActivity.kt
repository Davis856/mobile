package com.app.expensetracker.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.expensetracker.R
import com.app.expensetracker.adapter.ItemAdapter
import com.app.expensetracker.models.Expense

class ListViewActivity : AppCompatActivity() {
    private val expenses = mutableListOf<Expense>()
    lateinit var addButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout_activity_list_expenses)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_back)
        }

        val window: Window = this@ListViewActivity.window
        window.statusBarColor = ContextCompat.getColor(this@ListViewActivity, R.color.black)

        initExpenses()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ItemAdapter(this, expenses)

        addButton = findViewById(R.id.buttonAddExpense)

        addButton.setOnClickListener {
            val intent = Intent(applicationContext, AddExpenseActivity::class.java)
            getResult.launch(intent)
        }
    }

    private val getResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == 3) {
                if (it.resultCode == Activity.RESULT_OK) {
                    if (it.data != null) {
                        val data = it.data
                        val bundle = data?.getBundleExtra("expenseBundle")
                        val expense = bundle?.getParcelable<Expense>("expense")
                        if (expense != null) {
                            addExpenseToList(expense)
                        }
                    }
                    Toast.makeText(this, "Expense Added!", Toast.LENGTH_SHORT).show()
                    recyclerView.adapter?.notifyItemInserted(stories.size - 1)
                }
            } else if(it.resultCode == 5){
                if(it.resultCode == 5){
                    if(it.resultCode == Activity.RESULT_OK){
                        if(it.data != null){

                        }
                    }
                }
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}