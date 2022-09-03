package com.example.shoppinglisttest.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglisttest.domain.Item
import com.example.shoppinglisttest.domain.ItemRepository
import java.lang.RuntimeException

object ItemRepositoryImpl : ItemRepository {

    private val repositoryLD = MutableLiveData<List<Item>>()
    private val repository = sortedSetOf<Item>({o1, o2 -> o1.id.compareTo(o2.id)})

    private var autoincroment = 0

    init {
        for (i in 0 until 10) {
            val item = Item("Name $i", i, true)
            addItem(item)
        }
    }

    override fun addItem(item: Item) {
        if (item.id == Item.UNDEFINED_ID){
            item.id = autoincroment++
        }
        repository.add(item)
        upList()
    }


    fun upList(){
        repositoryLD.value = repository.toList()
    }




    override fun getList(): LiveData<List<Item>> {
        return repositoryLD
    }


    override fun remiveItem(item: Item) {
        repository.remove(item)
        upList()
    }

    override fun setItem(item: Item) {
        var findItem = findItemById(item.id)
        remiveItem(findItem)
        addItem(item)
    }

    override fun findItemById(id: Int) : Item {
        return repository.find { id == it.id } ?: throw RuntimeException()
    }




}