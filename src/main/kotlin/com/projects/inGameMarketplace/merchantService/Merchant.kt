package com.projects.inGameMarketplace.merchantService

import com.projects.inGameMarketplace.itemService.Item
import com.projects.inGameMarketplace.itemService.ItemService
import kotlin.random.Random

class Merchant {
    private val itemService = ItemService()
    var dailyItemList: List<Pair<Item, Int>> = listOf()

    fun getNewItems() {
        val allItems = itemService.getAllItems()
        val listBuilder: MutableList<Pair<Item, Int>> = mutableListOf()
        for (i in 1..10) {
            while (true) {
                val randomNumber = Random.nextInt(1, allItems.size)
                val itemToAdd = allItems[randomNumber]
                if (!listBuilder.map { item -> item.first.name }.contains(itemToAdd.name)) {
                    itemToAdd.currentPrice = this.changePrices(itemToAdd.averagePrice)
                    val itemAmount = Random.nextInt(20, 100)
                    val itemPair: Pair<Item, Int> = itemToAdd to itemAmount
                    listBuilder.add(itemPair)
                    break
                }
            }
        }
        this.dailyItemList = listBuilder
    }

    private fun changePrices(price: Double): Double {
        val lowerBoundary: Double = 0.5
        val upperBoundary: Double = 1.5
        return Random.nextDouble(price * lowerBoundary, price * upperBoundary)
    }
}
