package com.projects.inGameMarketplace

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class PlayerEntity(
    val playerName: String,
    val money: Double,
    val inventorySpace: Int,
    val inventoryItems: String,
    val day: Int
)