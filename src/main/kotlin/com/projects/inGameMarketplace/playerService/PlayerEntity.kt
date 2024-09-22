package com.projects.inGameMarketplace.playerService

import kotlinx.serialization.Serializable

@Serializable
data class PlayerEntity(
    val playerName: String,
    val inventorySpace: Int,
    val day: Int,
    val money: Double,
)