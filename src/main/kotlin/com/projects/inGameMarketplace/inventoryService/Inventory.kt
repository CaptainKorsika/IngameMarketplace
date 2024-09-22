package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item

class Inventory {
    val currentItems = mutableListOf<Pair<Item, Int>>()
}