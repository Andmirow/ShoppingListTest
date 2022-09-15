package com.example.shoppinglisttest.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.domain.Item
import com.example.shoppinglisttest.presentation.recycler_view_tools.ShopAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView : RecyclerView
    lateinit var viewModel: MainViewModel
    lateinit var addButtonView : FloatingActionButton
    private var fragmentContainer : FragmentContainerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_shop_list)
        addButtonView = findViewById(R.id.button_add_shop_item)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setRecyclerView()
        addButtonView.setOnClickListener {
            if (fragmentContainer==null){
                val intent = AddItemActivity.addItem(this)
                startActivity(intent)
            }else{
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, AddItemFragment::class.java, null)
                    .commit()
            }

        }
        fragmentContainer = findViewById(R.id.fragment_container)

    }

    fun setRecyclerView(){
        val rv = findViewById<RecyclerView>(R.id.rv_shop_list)
        val adapter = ShopAdapter()
        rv.adapter = adapter
        setListener(adapter, rv)

    }

    private fun setListener(adapter: ShopAdapter, rv: RecyclerView?) {
        adapter.onItemLongClikListener = {
            viewModel.changeEnable(it)
        }
        adapter.onItemShotClikListener = {
            if (fragmentContainer==null){
                val intent = AddItemActivity.setItem(this, it.id)
                startActivity(intent)
            }else{
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, AddItemFragment::class.java, null)
                    .commit()
            }
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

