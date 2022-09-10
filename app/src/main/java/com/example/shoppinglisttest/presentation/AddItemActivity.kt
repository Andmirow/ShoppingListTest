package com.example.shoppinglisttest.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.domain.AddItemClass
import com.example.shoppinglisttest.domain.FindItemByIdClass
import com.example.shoppinglisttest.domain.Item
import com.example.shoppinglisttest.domain.SetItemClass
import com.google.android.material.textfield.TextInputLayout

class AddItemActivity : AppCompatActivity(){

    lateinit var nameView : TextInputLayout
    lateinit var countView : TextInputLayout
    lateinit var saveView : Button
    lateinit var viewModel: AddItemViewModel
    var oldItem : Item? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.add_item)
        nameView = findViewById(R.id.title)
        countView = findViewById(R.id.count)
        saveView = findViewById(R.id.save)
        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
        val oldItemId = intent.getIntExtra(SET_ITEM,-2)
        if (oldItemId != -2){
            oldItem = viewModel.findItem(oldItemId)
            nameView.editText?.setText(oldItem!!.name)
            countView.editText?.setText(oldItem!!.count)
        }
    }


    fun clickSave(view: View) {
        if (oldItem != null){
            viewModel.setItem(oldItem!!)
        }else{
            viewModel.addItem(nameView.editText.toString(),countView.editText.toString().toInt())
        }
        //redirect
    }

}