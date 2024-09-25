package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item


class PlayerInventoryService {
    val inventoryRepository = InventoryRepository()
    val inventory: Inventory? = getPlayerInventoryFromDB()
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

    fun buyInventoryAndReturnPrice(money: Double, space: Int): Double {
        var price = 0.0
        when(space) {
            10 -> {
                if (money >= firstExtensionPrice) {
                    price = firstExtensionPrice
                }
            }
            20 -> {
                if (money >= secondExtensionPrice) {
                    price =  secondExtensionPrice
                }
            }
        }
        return price
    }

    fun addItemAndConfirm(boughtItem: Pair<Item, Int>, space: Int): Boolean {

        if (space == this.inventory!!.currentItems.size) {
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