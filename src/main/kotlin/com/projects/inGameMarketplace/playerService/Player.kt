package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.itemService.Item

class Player(
    val name: String,
    var inventorySpace: Int = 10,
    var day: Int = 1,
    var money: Int = 50000
)