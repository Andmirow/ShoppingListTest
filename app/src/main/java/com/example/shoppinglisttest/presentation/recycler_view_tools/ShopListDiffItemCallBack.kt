package com.example.shoppinglisttest.presentation.recycler_view_tools

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglisttest.domain.Item

class ShopListDiffItemCallBack : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }



}