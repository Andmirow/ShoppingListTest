package com.example.shoppinglisttest.domain

import java.io.Serializable

data class Item (
    val name : String,
    val count : Int,
    var enable : Boolean,
    var id : Int = UNDEFINED_ID
){

    companion object{
        const val UNDEFINED_ID = -1;
    }
}
