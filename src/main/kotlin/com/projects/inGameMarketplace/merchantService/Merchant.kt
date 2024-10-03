package com.projects.inGameMarketplace.merchantService

import com.projects.inGameMarketplace.inventoryService.Inventory
import com.projects.inGameMarketplace.itemService.Item
import com.projects.inGameMarketplace.itemService.ItemService
import kotlin.random.Random

class Merchant {
    private val itemService = ItemService()
    var dailyInventory: Inventory = this.getNewItems()

    fun getNewItems(): Inventory {
        val newInventory = Inventory()
        val allItems = itemService.getAvailableItems()
        for (i in 1..10) {
            while (true) {
                val randomNumber = Random.nextInt(1, allItems.size)
                val itemToAdd = allItems[randomNumber]
                if (!newInventory.currentItems.map { item -> item.first.name }.contains(itemToAdd.name)) {
                    itemToAdd.currentPrice = this.changePrices(itemToAdd.averagePrice)
                    val itemAmount = Random.nextInt(20, 100)
                    val itemPair: Pair<Item, Int> = itemToAdd to itemAmount
                    newInventory.currentItems.add(itemPair)
                    break
                }
            }
        }
        this.dailyInventory = newInventory
        return newInventory
    }

    private fun changePrices(price: Double): Double {
        val lowerBoundary = 0.5
        val upperBoundary = 1.5
        return Random.nextDouble(price * lowerBoundary, price * upperBoundary)
    }
}
