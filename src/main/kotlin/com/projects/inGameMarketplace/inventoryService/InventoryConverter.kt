package com.projects.inGameMarketplace.inventoryService

class InventoryConverter {

    fun toDomain(entity: InventoryEntity): Inventory {
        val inventory =  Inventory()
        entity.currentItems.forEach { inventory.currentItems.add(it) }
        // TODO: get item name information and create inventory

        return inventory
    }

    fun toEntity(inventory: Inventory): InventoryEntity {
        return InventoryEntity(inventory.currentItems.map { it.first.name to it.second }.toMutableList())
    }


}