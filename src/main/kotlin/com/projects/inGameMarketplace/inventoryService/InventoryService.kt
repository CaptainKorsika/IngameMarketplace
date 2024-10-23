package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item

class InventoryService {
    private val inventoryRepository = InventoryRepository()
    private val inventoryConverter = InventoryConverter()
    val inventory: Inventory = getPlayerInventoryFromDB()
    private val firstExtensionPrice: Int = 100000
    private val secondExtensionPrice: Int = 200000


    fun createInventory() {
        println("Creating inventory")
        if (this.inventory.currentItems.isNotEmpty()) {
            val entity = this.inventoryConverter.toEntity(this.inventory)
            inventoryRepository.saveInventory(entity)
        }
    }

    fun updateInventory() {
        this.deleteInventory(true)
        this.createInventory()
    }

    private fun getPlayerInventoryFromDB(): Inventory {
        val inventoryEntity = this.inventoryRepository.loadInventory()
        val inventory = this.inventoryConverter.toDomain(inventoryEntity)

        return inventory
    }

    fun calculateInventoryUpgradePrice(space: Int): Int {
        return if (space == 10) firstExtensionPrice else secondExtensionPrice
    }

    fun maxPossibleItemSpace(boughtItem: Pair<Item, Int>, space: Int): Int {
        val itemName = boughtItem.first.name
        val indexOfItem = this.inventory.currentItems.map { inventoryItemObject -> inventoryItemObject.item.name}.indexOf(itemName)

        if (space == this.inventory.currentItems.size && indexOfItem == -1) {
            return 0
        }

        if (indexOfItem == -1) {
            return 99
        }

        val availableSpace = 99 - this.inventory.currentItems[indexOfItem].amount
        if (boughtItem.second > availableSpace) {
            return availableSpace
        }

        return boughtItem.second
    }

    fun addItem(boughtItem: Pair<Item, Int>) {

        val itemName = boughtItem.first.name
        val indexOfItem = this.inventory.currentItems.map { inventoryItemObject -> inventoryItemObject.item.name}.indexOf(itemName)

        if (indexOfItem == -1) {
            this.inventory.currentItems.add(InventoryItem(boughtItem.first, boughtItem.second, boughtItem.first.currentPrice))
        } else {

            val newAverage: Int = calculateNewAverage(boughtItem, indexOfItem)

            val newAmount = this.inventory.currentItems[indexOfItem].amount + boughtItem.second
            val addedInventoryItem = InventoryItem(boughtItem.first, newAmount, newAverage)
            this.inventory.currentItems[indexOfItem] = addedInventoryItem
        }
    }

    fun removeItemAndConfirm(soldItem: Pair<Item, Int>): Boolean {
        val itemName = soldItem.first.name
        val amountToSell = soldItem.second
        val indexOfItem = this.inventory.currentItems.map { inventoryItemObject -> inventoryItemObject.item.name}.indexOf(itemName)

        if (indexOfItem == -1) {
            println("Item not found")
            return false
        }

        val availableAmount = this.inventory.currentItems[indexOfItem].amount
        val soldAmount = if (amountToSell > availableAmount) availableAmount else amountToSell

        if (soldAmount >= availableAmount) {
            inventory.currentItems.removeAt(indexOfItem)
        } else {
            val partiallySoldItem = inventory.currentItems[indexOfItem].item
            val remainingAmount = availableAmount - soldAmount
            val averageBuyingPrice = inventory.currentItems[indexOfItem].averageBuyingPrice

            inventory.currentItems[indexOfItem] = InventoryItem(partiallySoldItem, remainingAmount, averageBuyingPrice)
        }
        return true
    }

    fun deleteInventory(isNextDay: Boolean = false) {
        if (!isNextDay) {
            this.inventory.currentItems.clear()
        }
        this.inventoryRepository.deleteInventory()
    }

    private fun calculateNewAverage(boughtItem: Pair<Item, Int>, index: Int): Int {
        val currentBuyingPriceAverage = this.inventory.currentItems[index].averageBuyingPrice
        val currentAmount = this.inventory.currentItems[index].amount
        val averagePriceTotal = currentAmount * currentBuyingPriceAverage!!
        val newPriceTotal = averagePriceTotal + boughtItem.first.currentPrice * boughtItem.second
        val newTotalAmount = currentAmount + boughtItem.second
        return newPriceTotal / newTotalAmount
    }
}