package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.itemService.Item

data class GameDataDTO(val inventorySpace: Int, val day: Int, val money: Int, val inventoryItems: MutableList<Pair<Item, Int>>)