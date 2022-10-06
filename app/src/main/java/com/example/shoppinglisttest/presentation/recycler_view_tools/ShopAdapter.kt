package com.example.shoppinglisttest.presentation.recycler_view_tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.databinding.ItemDisabledBinding
import com.example.shoppinglisttest.databinding.ItemEnabledBinding
import com.example.shoppinglisttest.domain.Item
import com.example.shoppinglisttest.presentation.recycler_view_tools.ShopListDiffItemCallBack
import com.example.shoppinglisttest.presentation.recycler_view_tools.ShopViewHolder

private const val ENABLE_TYPE = 1
private const val DISABLED_TYPE = 2
class ShopAdapter() : ListAdapter<Item, ShopViewHolder>(ShopListDiffItemCallBack()) {


    var onItemLongClikListener : ((Item)-> Unit)? = null
    var onItemShotClikListener : ((Item)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val layout = when (viewType) {
            DISABLED_TYPE -> R.layout.item_disabled
            ENABLE_TYPE -> R.layout.item_enabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }


        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context),layout,parent,false)
        return ShopViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val item = getItem(position)
        val binding = holder.binding
        when (binding){
            is ItemDisabledBinding -> {
                binding.tvName.text = item.name
                binding.tvCount.text = item.count.toString()
            }
            is ItemEnabledBinding -> {
                binding.tvName.text = item.name
                binding.tvCount.text = item.count.toString()
            }
        }
        binding.root.setOnLongClickListener {
            onItemLongClikListener?.invoke(item)
            return@setOnLongClickListener true
        }
        binding.root.setOnClickListener {
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






