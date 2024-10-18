package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.inventoryService.InventoryItemDTO

data class GameDataDTO(val inventorySpace: Int, val day: Int, val money: String, val inventoryItems: List<InventoryItemDTO>)