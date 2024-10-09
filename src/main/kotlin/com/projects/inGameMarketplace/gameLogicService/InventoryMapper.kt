package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.inventoryService.Inventory
import com.projects.inGameMarketplace.inventoryService.InventoryDTO
import com.projects.inGameMarketplace.itemService.ItemDTO

class InventoryMapper {
    private val itemMapper = ItemMapper()

    fun mapToInventoryDTO(inventory: Inventory): InventoryDTO {
        val itemDTOList = mutableListOf<Pair<ItemDTO, Int>>()
        inventory.currentItems.forEach {
            val itemDTOPair = itemMapper.mapToItemDTO(it.first) to it.second
            itemDTOList.add(itemDTOPair)

        }
        val inventoryDTO = InventoryDTO(itemDTOList.toList())
        return inventoryDTO
    }
}