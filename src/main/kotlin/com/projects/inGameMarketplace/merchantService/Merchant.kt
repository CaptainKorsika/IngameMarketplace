package com.projects.inGameMarketplace.merchantService

import com.projects.inGameMarketplace.inventoryService.Inventory
import com.projects.inGameMarketplace.inventoryService.InventoryItem
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
                if (!newInventory.currentItems.map { item -> item.item.name }.contains(itemToAdd.name)) {
                    itemToAdd.currentPrice = this.changePrices(itemToAdd.averagePrice)
                    val itemAmount = Random.nextInt(20, 100)
                    newInventory.currentItems.add(InventoryItem(itemToAdd, itemAmount))
                    break
                }
            }
        }
        this.dailyInventory = newInventory
        return newInventory
    }

    fun maxPossibleItemPurchase(item: Pair<Item, Int>): Int {
        val merchantItem = this.dailyInventory.currentItems.firstOrNull { it.item.name == item.first.name }
        if (merchantItem == null) {
            return 0
        }

        val merchantAmount = merchantItem.amount
        var desiredPlayerPurchase = item.second

        if (desiredPlayerPurchase > merchantAmount) {
            desiredPlayerPurchase = merchantAmount
        }

        return desiredPlayerPurchase
    }


    fun sellItem (item: Pair<Item, Int>) {
        val merchantItem = this.dailyInventory.currentItems.first { it.item.name == item.first.name }
        val currentItemIndex = this.dailyInventory.currentItems.indexOf(merchantItem)

        val newAmount = merchantItem.amount - item.second

        val newItem = InventoryItem(merchantItem.item, newAmount)
        this.dailyInventory.currentItems[currentItemIndex] = newItem
    }

    private fun changePrices(price: Int): Int {
        val lowerBoundary = 0.5
        val upperBoundary = 1.5
        val calculatedPrice = Random.nextInt((price * lowerBoundary).toInt(), (price * upperBoundary).toInt())
        return calculatedPrice
    }
}
