package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.ItemService

class InventoryConverter {

    fun toDomain(entity: InventoryEntity): Inventory {
        val inventory =  Inventory()
        if (entity.currentItems.size > 0) {
            entity.currentItems.forEach {
                inventory.currentItems.add(it)
            }
        }
        return inventory
    }

    fun toEntity(inventory: Inventory): InventoryEntity {
        return InventoryEntity(inventory.currentItems.map { it.first to it.second }.toMutableList())
    }

}