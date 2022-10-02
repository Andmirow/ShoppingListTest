package com.example.shoppinglisttest.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglisttest.data.database.AppDatabase
import com.example.shoppinglisttest.data.database.MapperItem
import com.example.shoppinglisttest.domain.Item
import com.example.shoppinglisttest.domain.ItemRepository
import java.lang.RuntimeException

class ItemRepositoryImpl(application: Application) : ItemRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()

    override fun addItem(item: Item) {
        shopListDao.addItem(MapperItem.mapItemToDbModel(item))
    }


    override fun getList(): LiveData<List<Item>> {
        return MediatorLiveData<List<Item>>().apply {
            this.addSource(shopListDao.getListItem()){
                value = MapperItem.mapListDbItemToEmtity(it)
            }
        }
    }


    override fun remiveItem(item: Item) {
        shopListDao.deleteItem(item.id)
    }

    fun remiveItem(itemId: Int) {
        shopListDao.deleteItem(itemId)
    }


    override fun setItem(item: Item) {
        addItem(item)
    }

    override fun findItemById(id: Int) : Item {
        val itemDb = shopListDao.getItem(id)
        return MapperItem.mapDbItemToEmtity(itemDb)
    }




}