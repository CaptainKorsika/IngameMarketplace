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


        val inventoryEntity = InventoryEntity(mutableListOf<InventoryItem>())

        inventory.currentItems.forEach {
            var avgPrice: Int

            if (it.averageBuyingPrice == null) {
                avgPrice = 0
            } else {
                avgPrice = it.averageBuyingPrice
            }

            inventoryEntity.currentItems.add(InventoryItem(it.item, it.amount, avgPrice))
        }


        return InventoryEntity(inventory.currentItems.map { InventoryItem(it.item, it.amount, it.averageBuyingPrice) }.toMutableList())
    }

}