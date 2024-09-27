package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item

data class InventoryEntity(val currentItems: MutableList<Pair<Item, Int>>)


