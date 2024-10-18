package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.ItemDTO

data class InventoryDTO(
    val currentItems: List<InventoryItemDTO>
)