package com.example.shoppinglisttest.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.domain.Item

class ShopAdapter() : RecyclerView.Adapter<ShopViewHolder>() {

    var listItem = listOf<Item>()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_enabled,parent,false)
        return ShopViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val item = listItem[position]
        holder.name.text = item.name
        holder.count.text = item.count.toString()

    }

    override fun getItemCount(): Int {
        return listItem.size
    }

}

class ShopViewHolder(view : View) : RecyclerView.ViewHolder(view){

    var name = view.findViewById<TextView>(R.id.tv_name)
    var count = view.findViewById<TextView>(R.id.tv_count)
}

