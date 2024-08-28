package com.projects.inGameMarketplace

import kotlin.random.Random

class ItemService {
    private var existingItems: List<Item> = listOf(
        Item("Bread", "", 2.0),
        Item("Steak", "", 4.0),
        Item("Cheese", "", 5.0),
        Item("Mead", "", 12.0),
        Item("Apple", "", 3.0),
        Item("Pickaxe", "", 10.0),
        Item("Journal", "", 8.0),
        Item("Fire Starter", "", 3.0),
        Item("Bucket", "", 4.0),
        Item("Rope", "", 6.0),
        Item("Sword", "", 50.0),
        Item("Bow", "", 30.0),
        Item("Arrows", "", 15.0),
        Item("Chest Plate", "", 25.0),
        Item("Iron Helmet", "", 20.0),
        Item("Healing Potion", "", 18.0),
        Item("Elixir", "", 25.0),
        Item("Herbs", "", 7.0),
        Item("Antidote Potion", "", 16.0),
        Item("Tea", "", 5.0),
        Item("Cloak", "", 22.0),
        Item("Boots", "", 12.0),
        Item("Silver Ring", "", 20.0),
        Item("Tunic", "", 8.0),
        Item("Hat", "", 10.0),
        Item("Crystal Orb", "", 60.0),
        Item("Spell Scroll", "", 45.0),
        Item("Amulets", "", 80.0),
        Item("Mysterious Dust", "", 40.0),
        Item("Dragon Scale", "", 100.0)
    )
    val availableItems: MutableList<Item>? = null


    fun createAvailableItems(existingItems: List<Item> = this.existingItems): List<Item> {
        val listBuilder: MutableList<Item> = mutableListOf()
        for (i in 1..10) {
            while (true) {
                val randomNumber = Random.nextInt(1, existingItems.size)
                val itemToAdd = existingItems[randomNumber]
                if (!listBuilder.map { item -> item.name }.contains(itemToAdd.name)) {
                    listBuilder.add(itemToAdd)
                    break
                }
            }
        }
        return listBuilder
    }

    fun changeDailyPrice() {
        val lowerBoundary: Double = 0.5
        val upperBoundary: Double = 1.5

        this.existingItems.forEach { it.currentPrice = Random.nextDouble(it.averagePrice * lowerBoundary, it.averagePrice * upperBoundary)}
    }
}