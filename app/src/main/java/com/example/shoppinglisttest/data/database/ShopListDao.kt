package com.example.shoppinglisttest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ShopListDao {

    @Query("SELECT * FROM shop_items")
    fun getListItem() : LiveData<List<ItemDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(itemDbModel: ItemDbModel)

    @Query("DELETE FROM shop_items WHERE id=:itemId")
    fun deleteItem(itemId : Int)

    @Query("SELECT * FROM shop_items WHERE id=:itemId LIMIT 1")
    fun getItem(itemId : Int) : ItemDbModel

}