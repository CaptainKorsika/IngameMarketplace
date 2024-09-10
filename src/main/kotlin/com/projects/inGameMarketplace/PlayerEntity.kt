package com.projects.inGameMarketplace

import kotlinx.serialization.json.Json

data class PlayerEntity(
    val playerName: String,
    val money: Double,
    val inventorySpace: Int,
    val inventoryItems: String,
    val day: Int
) {
    init {
        require(playerName.length <= 30) {"Name can only be 30 Characters"}
        require(playerName.isNotEmpty()) {"Name can't be empty"}
    }
}