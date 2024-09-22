package com.projects.inGameMarketplace.itemService

class ItemConverter {

    fun toEntity(item: Item): ItemEntity {
        return ItemEntity(item.name, item.image, item.averagePrice)
    }

    fun toDomain(entity: ItemEntity): Item {
        return Item(entity.name, entity.image, entity.price)
    }

}