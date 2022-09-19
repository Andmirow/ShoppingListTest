package com.example.shoppinglisttest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import com.example.shoppinglisttest.R


class AddItemActivity : AppCompatActivity(), AddItemFragment.HowToCloseFragment{

    private var itemfragmentContainer : FragmentContainerView? = null

    companion object{
        private const val SET_ITEM = "set_Item"

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
        itemfragmentContainer = findViewById(R.id.fragment_container_view)
        val oldItemId = intent.getIntExtra(SET_ITEM,-2)
        if (oldItemId != -2){
            launchFragment(AddItemFragment.setItem(oldItemId))
        }else
            launchFragment(AddItemFragment.addItem())
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun closeFragment() {
        onBackPressed()
        onBackPressed()
    }

}