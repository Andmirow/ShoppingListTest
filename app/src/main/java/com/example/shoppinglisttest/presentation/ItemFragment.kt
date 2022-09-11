package com.example.shoppinglisttest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.shoppinglisttest.R
import java.util.zip.Inflater

class ItemFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.add_item,container,false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}