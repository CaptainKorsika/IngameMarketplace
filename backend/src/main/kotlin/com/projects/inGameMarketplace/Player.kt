package com.projects.inGameMarketplace

class Player(
    val name: String,
    var money: Double = 5000.0,
    var inventorySpace: Int = 1,
    val inventoryItems: List<Pair<Item, Int>> = mutableListOf(),
    var day: Int = 1
)