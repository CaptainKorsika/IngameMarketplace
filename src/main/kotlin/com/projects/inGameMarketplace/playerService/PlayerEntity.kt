package com.projects.inGameMarketplace.playerService

import kotlinx.serialization.Serializable

@Serializable
data class PlayerEntity(
    val playerName: String,
    val money: Double,
    val inventorySpace: Int,
    val day: Int
)