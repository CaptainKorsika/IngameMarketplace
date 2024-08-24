package com.projects.inGameMarketplace

class Player(playerName: String) {
    val name: String = playerName
    var money: Int = 500
    var inventorySpace: Int = 1
    val inventoryItems: MutableMap<Item, Int> = mutableMapOf()
    var day: Int = 1
}