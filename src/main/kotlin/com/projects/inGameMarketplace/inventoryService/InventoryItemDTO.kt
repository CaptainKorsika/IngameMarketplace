package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.ItemDTO

data class InventoryItemDTO(val item: ItemDTO,
                            val amount: Int,
                            val averageBuyingPrice: String?)