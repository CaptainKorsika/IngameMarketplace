package com.projects.inGameMarketplace

class Player(
    playerName: String,
    var money: Double = 500.0,
    var inventorySpace: Int = 1,
    val inventoryItems: List<Pair<Item, Int>> = mutableListOf(),
    var day: Int = 1
) {
    val name: String = playerName
}