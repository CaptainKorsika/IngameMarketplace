package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item


class PlayerInventoryService() {
    val inventoryRepository = InventoryRepository()
    val inventory: Inventory? = getPlayerInventoryFromDB()
    var inventorySpace = 10
    private val firstExtensionPrice: Double = 1000.0
    private val secondExtensionPrice: Double = 2000.0


    fun createInventory() {
        // create DB entry
        this.getPlayerInventoryFromDB()
    }


    private fun getPlayerInventoryFromDB(): Inventory? {
        // TODO: return null if no player
        val inventory = Inventory()
        return inventory
    }

    fun buyInventoryAndReturnNewBalance(money: Double): Double {
        when(this.inventorySpace) {
            10 -> {
                if (money >= firstExtensionPrice) {
                    this.inventorySpace = 20
                    return firstExtensionPrice
                }
            }
            20 -> {
                if (money >= secondExtensionPrice) {
                    this.inventorySpace = 30
                    return secondExtensionPrice
                }
            }
        }

        // TODO: Calc new Balance
        return 0.0
    }

    fun addItemAndConfirm(boughtItem: Pair<Item, Int>): Boolean {

        if (this.inventorySpace == this.inventory!!.currentItems.size) {
            return false
        }

        val itemName = boughtItem.first.name
        val indexOfItem = this.inventory.currentItems.map { pair -> pair.first.name}.indexOf(itemName)

        if (indexOfItem == -1) {
            this.inventory.currentItems.add(boughtItem)
            return true
        } else {
            val availableSpace = 99 - this.inventory.currentItems[indexOfItem].second
            if (boughtItem.second > availableSpace) {
                return false
            }
            this.inventory.currentItems[indexOfItem] = boughtItem
        }
        return true
    }

    fun removeItemAndConfirm(soldItem: Pair<Item, Int>): Boolean {
        val itemName = soldItem.first.name
        val amountToSell = soldItem.second
        val indexOfItem = this.inventory!!.currentItems.map { pair -> pair.first.name}.indexOf(itemName)

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
        this.inventoryRepository.deleteInventory()
    }
}