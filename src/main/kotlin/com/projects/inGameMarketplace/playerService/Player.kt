package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.itemService.Item

class Player(
    val name: String,
    var money: Double = 5000.0,
    var inventorySpace: Int = 1,
    val inventoryItems: List<Pair<Item, Int>> = mutableListOf(
        Item("Bread", "", 2.0) to 3,
        Item("Steak", "", 4.0) to 2),
    var day: Int = 2
)