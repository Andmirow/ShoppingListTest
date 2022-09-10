package com.example.shoppinglisttest.presentation.recycler_view_tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.domain.Item
import com.example.shoppinglisttest.presentation.recycler_view_tools.ShopListDiffItemCallBack
import com.example.shoppinglisttest.presentation.recycler_view_tools.ShopViewHolder

const val ENABLE_TYPE = 1
const val DISABLED_TYPE = 2
class ShopAdapter() : ListAdapter<Item, ShopViewHolder>(ShopListDiffItemCallBack()) {


    var onItemLongClikListener : ((Item)-> Unit)? = null
    var onItemShotClikListener : ((Item)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        return ShopViewHolder(
            when(viewType){
                ENABLE_TYPE-> LayoutInflater.from(parent.context).inflate(R.layout.item_enabled,parent,false)
                DISABLED_TYPE -> LayoutInflater.from(parent.context).inflate(R.layout.item_disabled,parent,false)
                else -> throw Exception()
            }
        )
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val item = getItem(position)
        holder.name.text = item.name
        holder.count.text = item.count.toString()

        holder.itemView.setOnLongClickListener {
            onItemLongClikListener?.invoke(item)
            return@setOnLongClickListener true
        }
        holder.itemView.setOnClickListener {
            onItemShotClikListener?.invoke(item)
        }

    }



    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).enable){
            true -> ENABLE_TYPE
            false -> DISABLED_TYPE
        }
    }

}






