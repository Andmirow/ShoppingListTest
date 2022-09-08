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
    lateinit var viewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.add_item)
        nameView = findViewById(R.id.title)
        countView = findViewById(R.id.count)
        saveView = findViewById(R.id.save)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }



    fun clickSave(view: View) {
        val newItem = Item(nameView.editText.toString(),
            countView.editText.toString().toInt(),
            true)

    }


}