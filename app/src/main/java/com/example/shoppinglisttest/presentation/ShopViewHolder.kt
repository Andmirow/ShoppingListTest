package com.example.shoppinglisttest.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglisttest.R

class ShopViewHolder(view : View) : RecyclerView.ViewHolder(view){

    var name = view.findViewById<TextView>(R.id.tv_name)
    var count = view.findViewById<TextView>(R.id.tv_count)
}