package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item

data class InventoryItem(val item: Item,
                         val amount: Int,
                         val averageBuyingPrice: Int? = null)