package com.example.shoppinglisttest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.domain.Item

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_shop_list)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setRecyclerView()
    }

    fun setRecyclerView(){
        val rv = findViewById<RecyclerView>(R.id.rv_shop_list)
        val adapter = ShopAdapter()
        rv.adapter = adapter
        setListener(adapter, rv)

    }

    private fun setListener(
        adapter: ShopAdapter,
        rv: RecyclerView?
    ) {
        adapter.onItemLongClikListener = {
            viewModel.changeEnable(it)
        }
        adapter.onItemShotClikListener = {
            Log.d("test", "${it.name} ${it.count} ${it.enable}")
        }

        viewModel.shopList.observe(this) {
            adapter.submitList(it)
        }

        val simCollBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteItem(adapter.currentList[viewHolder.adapterPosition])
            }

        }

        val itemTouchHelper = ItemTouchHelper(simCollBack)
        itemTouchHelper.attachToRecyclerView(rv)
    }


}