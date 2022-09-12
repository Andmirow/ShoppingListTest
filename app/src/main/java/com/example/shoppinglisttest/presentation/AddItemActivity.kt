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

    lateinit var nameView : TextInputLayout
    lateinit var countView : TextInputLayout
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
//        nameView = findViewById(R.id.title)
//        countView = findViewById(R.id.count)
//        saveView = findViewById(R.id.save)
//        viewModel = ViewModelProvider(this).get(AddItemViewModel::class.java)
//        val oldItemId = intent.getIntExtra(SET_ITEM,-2)
//        if (oldItemId != -2){
//            oldItem = viewModel.findItem(oldItemId)
//            nameView.editText?.setText(oldItem!!.name)
//            countView.editText?.setText(oldItem!!.count.toString())
//        }
    }

//    fun clickSave(view: View) {
//        if (oldItem != null){
//            val newItem = Item(nameView.editText?.text.toString(),countView.editText?.text.toString().toInt(),true,oldItem!!.id)
//            viewModel.setItem(newItem!!)
//        }else{
//            viewModel.addItem(nameView.editText.toString(),countView.editText.toString().toInt())
//        }
//        startActivity(Intent(this, MainActivity::class.java)) //redirect
//    }

}