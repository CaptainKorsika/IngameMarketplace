package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.itemService.ItemDTO

data class GameDataDTO(val inventorySpace: Int, val day: Int, val money: String, val inventoryItems: List<Pair<ItemDTO, Int>>)