package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.ItemService

class InventoryConverter {

    fun toDomain(entity: InventoryEntity): Inventory {
        val inventory =  Inventory()
        val itemList = ItemService().getAvailableItems()
        entity.currentItems.forEach {

            val item = itemList.first { item -> item.name == it.first }
            inventory.currentItems.add(item to it.second) }

        return inventory
    }

    fun toEntity(inventory: Inventory): InventoryEntity {
        return InventoryEntity(inventory.currentItems.map { it.first.name to it.second }.toMutableList())
    }


}