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

    private fun convertMoneyToString(money: Int): String {
        val convertedMoney = money.toString()
        val moneyStringLength = convertedMoney.length
        val dollar = convertedMoney.substring(0, moneyStringLength - 2)
        val cent = convertedMoney.substring(moneyStringLength - 2)

        return "$dollar,$cent"
    }
}