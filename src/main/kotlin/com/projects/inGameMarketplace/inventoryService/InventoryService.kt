package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item

class InventoryService {
    private val inventoryRepository = InventoryRepository()
    private val inventoryConverter = InventoryConverter()
    val inventory: Inventory = getPlayerInventoryFromDB()
    private val firstExtensionPrice: Int = 100000
    private val secondExtensionPrice: Int = 200000


    fun createInventory() {
        if (this.inventory.currentItems.isNotEmpty()) {
            val entity = this.inventoryConverter.toEntity(this.inventory)
            inventoryRepository.saveInventory(entity)
        }
    }

    fun updateInventory() {
        this.deleteInventory()
        this.createInventory()
    }

    private fun getPlayerInventoryFromDB(): Inventory {
        val inventoryEntity = this.inventoryRepository.loadInventory()
        val inventory = this.inventoryConverter.toDomain(inventoryEntity)

        return inventory
    }

    fun calculateInventoryUpgradePrice(money: Int, space: Int): Int {
        return if (space == 10) firstExtensionPrice else secondExtensionPrice
    }

    fun maxPossibleItemSpace(boughtItem: Pair<Item, Int>, space: Int): Int {
        if (space == this.inventory.currentItems.size) {
            return 0
        }

        val itemName = boughtItem.first.name
        val indexOfItem = this.inventory.currentItems.map { pair -> pair.first.name}.indexOf(itemName)

        if (indexOfItem == -1) {
            return 99
        }

        val availableSpace = 99 - this.inventory.currentItems[indexOfItem].second
        if (boughtItem.second > availableSpace) {
            return availableSpace
        }

        return boughtItem.second
    }

    fun addItem(boughtItem: Pair<Item, Int>) {

        val itemName = boughtItem.first.name
        val indexOfItem = this.inventory.currentItems.map { pair -> pair.first.name}.indexOf(itemName)

        if (indexOfItem == -1) {
            this.inventory.currentItems.add(boughtItem)
        } else {
            val newAmount = this.inventory.currentItems[indexOfItem].second + boughtItem.second
            val addedItem = boughtItem.first to newAmount
            this.inventory.currentItems[indexOfItem] = addedItem
        }
    }

    fun removeItemAndConfirm(soldItem: Pair<Item, Int>): Boolean {
        val itemName = soldItem.first.name
        val amountToSell = soldItem.second
        val indexOfItem = this.inventory.currentItems.map { pair -> pair.first.name}.indexOf(itemName)

        if (indexOfItem == -1) {
            println("Item not found")
            return false
        }

        val availableAmount = this.inventory.currentItems[indexOfItem].second
        val soldAmount = if (amountToSell > availableAmount) availableAmount else amountToSell

        if (soldAmount >= availableAmount) {
            inventory.currentItems.removeAt(indexOfItem)
        } else {
            val partiallySoldItem = inventory.currentItems[indexOfItem].first
            val remainingAmount = availableAmount - soldAmount

            inventory.currentItems[indexOfItem] = partiallySoldItem to remainingAmount

        }
        return true
    }

    fun deleteInventory() {
        this.inventory.currentItems.clear()
        this.inventoryRepository.deleteInventory()
    }
}