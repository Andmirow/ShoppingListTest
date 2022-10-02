package com.example.shoppinglisttest.data.database

import com.example.shoppinglisttest.domain.Item

class MapperItem {

    companion object{

        fun mapItemToDbModel (item : Item):ItemDbModel{
            return ItemDbModel(item.id,item.name,item.count,item.enable)
        }

        fun mapDbItemToEmtity (itemDbModel : ItemDbModel):Item{
            return Item(itemDbModel.name,itemDbModel.count,itemDbModel.enable,itemDbModel.id)
        }

        fun mapListItemToDbModel(listItem : List<Item>) = listItem.map {
            mapItemToDbModel(it)
        }

        fun mapListDbItemToEmtity(listItemDbModel : List<ItemDbModel>) = listItemDbModel.map {
            mapDbItemToEmtity(it)
        }

    }

}