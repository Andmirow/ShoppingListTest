package com.example.shoppinglisttest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "shop_items")
data class ItemDbModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val count : Int,
    val enable : Boolean
)