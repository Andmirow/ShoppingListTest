package com.example.shoppinglisttest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.databinding.ActivityMainBinding
import com.example.shoppinglisttest.presentation.recycler_view_tools.ShopAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), AddItemFragment.HowToCloseFragment {

    private lateinit var recyclerView : RecyclerView
    private lateinit var viewModel: MainViewModel
    private lateinit var addButtonView : FloatingActionButton
    //private var fragmentContainer : FragmentContainerView? = null
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.rvShopList     //findViewById(R.id.rv_shop_list)
        addButtonView = binding.buttonAddShopItem     //findViewById(R.id.button_add_shop_item)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setRecyclerView()
        addButtonView.setOnClickListener {
            if (binding.fragmentContainer==null){
                val intent = AddItemActivity.addItem(this)
                startActivity(intent)
            }else{
                launchFragment(AddItemFragment.addItem())
            }
        }
    }

    private fun setRecyclerView(){
        val rv = findViewById<RecyclerView>(R.id.rv_shop_list)
        val adapter = ShopAdapter()
        rv.adapter = adapter
        setListener(adapter, rv)

    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setListener(adapter: ShopAdapter, rv: RecyclerView?) {
        adapter.onItemLongClikListener = {
            viewModel.changeEnable(it)
        }
        adapter.onItemShotClikListener = {
            if (binding.fragmentContainer==null){
                val intent = AddItemActivity.setItem(this, it.id)
                startActivity(intent)
            }else{
                launchFragment(AddItemFragment.setItem(it.id))
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

    override fun closeFragment() {
        supportFragmentManager.popBackStack()
    }
}

