package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.inventoryService.Inventory
import com.projects.inGameMarketplace.inventoryService.InventoryDTO
import com.projects.inGameMarketplace.itemService.ItemDTO

class InventoryMapper {

    fun mapToInventoryDTO(inventory: Inventory): InventoryDTO {

        val itemDTOList = mutableListOf<Pair<ItemDTO, Int>>()
        inventory.currentItems.forEach {

            val averagePrice = it.first.averagePrice
            val currentPrice = it.first.currentPrice

            val averagePriceString = convertMoneyToString(averagePrice)
            val currentPriceString = convertMoneyToString(currentPrice)

            itemDTOList.add(ItemDTO(it.first.name, it.first.image, averagePriceString, currentPriceString) to it.second)

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