package com.example.shoppinglisttest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
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
        viewModel.shopList.observe(this){


        }
    }

    fun setRecyclerView(){
        val rv = findViewById<RecyclerView>(R.id.rv_shop_list)
        rv.adapter = ShopAdapter()

    }



}