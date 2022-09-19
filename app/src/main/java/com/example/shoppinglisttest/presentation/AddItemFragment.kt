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

private const val SET_ITEM = "set_Item"
private const val NOT_ID = -2



class AddItemFragment : Fragment() {

    private lateinit var nameView : TextInputLayout
    private lateinit var countView : TextInputLayout
    private lateinit var saveView : Button
    private lateinit var viewModel: AddItemViewModel
    private var oldItem : Item? = null
    private lateinit var howToCloseFragment : HowToCloseFragment


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is HowToCloseFragment) {
            howToCloseFragment = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishedListener")
        }
    }



    companion object{
        private const val SET_ITEM = "set_Item"
        private const val ADD_ITEM = "add_Item"


        @JvmStatic
        fun setItem(itemId: Int) =
            AddItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(SET_ITEM, itemId)
                }
            }

        @JvmStatic
        fun addItem() = AddItemFragment().apply {
            arguments = Bundle()
        }

    }


    interface HowToCloseFragment{
        fun closeFragment()
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =   ViewModelProvider(this).get(AddItemViewModel::class.java)
        val args = requireArguments()
        if (args.containsKey(SET_ITEM)) {
            val ItemId = args.getInt(SET_ITEM,NOT_ID)
            oldItem = viewModel.findItem(ItemId)
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


        if (oldItem!=null){
            nameView.editText?.setText(oldItem!!.name)
            countView.editText?.setText(oldItem!!.count.toString())
        }



//        val oldItemId = requireActivity().intent.getIntExtra(SET_ITEM,-2)
//        if (oldItemId != -2){
//            oldItem = viewModel.findItem(oldItemId)
//            nameView.editText?.setText(oldItem!!.name)
//            countView.editText?.setText(oldItem!!.count.toString())
//        }

        saveView.setOnClickListener {
            if (oldItem != null){
                val newItem = Item(nameView.editText?.text.toString(),countView.editText?.text.toString().toInt(),true,oldItem!!.id)
                viewModel.setItem(newItem!!)
            }else{
                viewModel.addItem(nameView.editText?.text.toString(),countView.editText?.text.toString().toInt())
            }
            //activity?.onBackPressed()
            howToCloseFragment.closeFragment()
        }
    }

}