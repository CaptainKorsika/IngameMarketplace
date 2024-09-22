package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.inventoryService.Inventory

data class GameDataDTO(val inventorySpace: Int, val day: Int, val money: Double, val inventoryItems: Inventory?) {
}