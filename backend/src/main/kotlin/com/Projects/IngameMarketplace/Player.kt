package com.Projects.IngameMarketplace

class Player {
    lateinit var name: String
    var money: Int = 500
    var inventorySpace: Int = 1
    val inventoryItems: MutableSet<Item> = mutableSetOf()
    var day: Int = 1
    var isPlaying: Int = 1

    fun submitName(name: String) {
        this.name = name
    }

}