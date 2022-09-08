package com.example.shoppinglisttest.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.shoppinglisttest.domain.Item

class ShopListDiffUtilCallBack(
    val oldList : List<Item>,
    val newList : List<Item>
) : DiffUtil.Callback(){
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return ((oldList[oldItemPosition].name == newList[newItemPosition].name) &&
            (oldList[oldItemPosition].count == newList[newItemPosition].count) &&
            (oldList[oldItemPosition].enable == newList[newItemPosition].enable) &&
            (oldList[oldItemPosition].id == newList[newItemPosition].id))
    }

}