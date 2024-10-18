package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.inventoryService.Inventory
import com.projects.inGameMarketplace.inventoryService.InventoryDTO
import com.projects.inGameMarketplace.inventoryService.InventoryItemDTO

class InventoryMapper {
    private val itemMapper = ItemMapper()

    fun mapToInventoryDTO(inventory: Inventory): InventoryDTO {
        val itemDTOList = mutableListOf<InventoryItemDTO>()
        inventory.currentItems.forEach {

            val itemDTO = itemMapper.mapToItemDTO(it.item)
            val avgBuyingPrice: String?

            if (it.averageBuyingPrice != null) {
                avgBuyingPrice = itemMapper.convertMoneyToString(it.averageBuyingPrice)
            } else {
                avgBuyingPrice = null
            }

            val inventoryItemDTO = InventoryItemDTO(itemDTO, it.amount, avgBuyingPrice)
            itemDTOList.add(inventoryItemDTO)

        }
        val inventoryDTO = InventoryDTO(itemDTOList.toList())
        return inventoryDTO
    }
}