package com.example.shoppinglisttest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglisttest.R
import com.example.shoppinglisttest.domain.Item
import com.google.android.material.textfield.TextInputLayout

class AddItemFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.add_item_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameView = view.findViewById(R.id.title)
        countView = view.findViewById(R.id.count)
        saveView = view.findViewById(R.id.save)

        viewModel =   ViewModelProvider(this).get(AddItemViewModel::class.java)
        val oldItemId = requireActivity().intent.getIntExtra(SET_ITEM,-2)
        if (oldItemId != -2){
            oldItem = viewModel.findItem(oldItemId)
            nameView.editText?.setText(oldItem!!.name)
            countView.editText?.setText(oldItem!!.count.toString())
        }

        saveView.setOnClickListener {
            if (oldItem != null){
                val newItem = Item(nameView.editText?.text.toString(),countView.editText?.text.toString().toInt(),true,oldItem!!.id)
                viewModel.setItem(newItem!!)
            }else{
                viewModel.addItem(nameView.editText.toString(),countView.editText.toString().toInt())
            }
            activity?.onBackPressed()
        }
    }

}