package com.projects.inGameMarketplace.itemService

class ItemService {
    private val itemRepository = ItemRepository()
    private val itemConverter = ItemConverter()
    private val existingItems = getAllItemsFromDB()

    private fun getAllItemsFromDB(): List<Item> {
        val itemEntityList = itemRepository.getAvailableItems()
        val itemList = itemEntityList.map { itemConverter.toDomain(it) }
        return itemList
    }

    fun getAvailableItems(): List<Item> {
        return this.existingItems
    }
}