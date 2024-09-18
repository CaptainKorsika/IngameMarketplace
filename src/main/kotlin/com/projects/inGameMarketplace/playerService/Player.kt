package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.itemService.Item

class Player(
    val name: String,
    var money: Double = 5000.0,
    var inventorySpace: Int = 10,
    var day: Int = 1
)