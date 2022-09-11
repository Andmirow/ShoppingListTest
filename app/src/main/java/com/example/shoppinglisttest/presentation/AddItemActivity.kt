package com.example.shoppinglisttest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.domain.AddItemClass
import com.example.shoppinglisttest.domain.FindItemByIdClass
import com.example.shoppinglisttest.domain.Item
import com.example.shoppinglisttest.domain.SetItemClass
import com.google.android.material.textfield.TextInputLayout

class AddItemActivity : AppCompatActivity(){

    lateinit var nameView : EditText//TextInputLayout
    lateinit var countView : EditText//TextInputLayout
    lateinit var saveView : Button
    lateinit var viewModel: AddItemViewModel
    var oldItem : Item? = null


    companion object{
        private const val SET_ITEM = "set_Item"
        private const val ADD_ITEM = "add_Item"

        fun setItem(context : Context, itemId : Int): Intent {
            val intent = Intent(context, AddItemActivity::class.java)
            intent.putExtra(SET_ITEM, itemId)
            return intent
        }

        fun addItem(context : Context): Intent {
            return Intent(context, AddItemActivity::class.java)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_item)
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        Log.d("ShopItemActivity", mode.toString())
    }




//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.add_item)
//        nameView = findViewById(R.id.title)
//        countView = findViewById(R.id.count)
//        saveView = findViewById(R.id.save)
//        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
//        val oldItemId = intent.getIntExtra(SET_ITEM,-2)
//        if (oldItemId != -2){
//            oldItem = viewModel.findItem(oldItemId)
//            nameView.setText(oldItem!!.name)      //editText?.setText(oldItem!!.name)
//            countView.setText(oldItem!!.count)      //editText?.setText(oldItem!!.count)
//        }
//    }


    fun clickSave(view: View) {
        if (oldItem != null){
            viewModel.setItem(oldItem!!)
        }else{
            viewModel.addItem(nameView.text.toString(),countView.text.toString().toInt())//    editText.toString(),countView.editText.toString().toInt())
        }
        //redirect
    }

}