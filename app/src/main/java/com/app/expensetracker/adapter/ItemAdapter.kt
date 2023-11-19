package com.app.expensetracker.adapter

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.expensetracker.R
import com.app.expensetracker.models.Expense

class ItemAdapter(private val context: ListViewActivity, private val expenses: MutableList<Expense>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = expenses[position]
        holder.itemView.apply {
            name.text = item.name
            amount.text = item.amount
        }

        holder.itemView.deleteButton.setOnClickListener{
            val dialog = Dialog(context)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout_delete_popup)

            val nameLabel = dialog.findViewById(R.id.nameLabel) as TextView

            var expenseName = expenses.get(position).name
            expenseName += "?"

            nameLabel.text = expenseName

            val yesView = dialog.findViewById(R.id.yesButton) as View
            val noView = dialog.findViewById(R.id.noButton) as View

            yesView.setOnClickListener{
                expenses.removeAt(position)
                notifyDataSetChanged()
                dialog.dismiss()
            }

            noView.setOnClickListener{
                dialog.dismiss()
            }

            dialog.show()
        }

        holder.itemView.updateButton.setOnClickListener() {
            val bundle = Bundle()
            val intent = Intent(context, UpdateExpenseActivity::class.java)

            bundle.putParcelable("expense", expenses[position])
            intent.putExtra("expenseBundle", bundle)

            context.startActivityForResult(intent, 5)
        }
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}