package com.kilaserto.restaurantapp.ui.home

import android.annotation.SuppressLint
import android.app.UiModeManager
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.kilaserto.restaurantapp.R
import com.kilaserto.restaurantapp.db.CategoryModel
import com.kilaserto.restaurantapp.model.UICategoryModel
import kotlinx.android.synthetic.main.card_categories.view.*

class CategoriesAdapter(private var listener: CategoriesListener) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    var categoryArray: ArrayList<UICategoryModel> = ArrayList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.card_categories, parent, false)
        return CategoriesViewHolder(
            view, listener
        )
    }

    override fun getItemCount(): Int {
        return categoryArray.size
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoryArray[position])
    }

    class CategoriesViewHolder(itemView: View, listener: CategoriesListener) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(categoryModel: UICategoryModel) {

            if (categoryModel.isSelected) {
                itemView.title_category.setTextColor(Color.WHITE)
                itemView.card_view_categories.background.setTint(Color.parseColor("#FF00B050"))
            }else{
                itemView.title_category.setTextColor(Color.BLACK)
                itemView.card_view_categories.background.setTint(Color.parseColor("#D0CECE"))
            }
            itemView.title_category.text = categoryModel.title_category
        }

        init {
            itemView.card_view_categories.setOnClickListener {
                listener.onCategoriSelected(adapterPosition)
            }
        }
    }

    interface CategoriesListener {
        fun onCategoriSelected(position: Int)
    }

    fun setAllCategories(allCategories: ArrayList<UICategoryModel>) {
        this.categoryArray = allCategories
        notifyDataSetChanged()
    }
}