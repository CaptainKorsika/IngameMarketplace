package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.itemService.Item
import com.projects.inGameMarketplace.itemService.ItemDTO

class ItemMapper {
    fun mapToItemDTO(item: Item): ItemDTO {
        val averagePrice = this.convertMoneyToString(item.averagePrice)
        val currentPrice = this.convertMoneyToString(item.currentPrice)
        return ItemDTO(item.name, item.image, averagePrice,  currentPrice)

    }

    fun mapToItemObject(itemDTO: ItemDTO, availableItems: List<Item>): Item {
        val matchingItem = availableItems.first { it.name == itemDTO.name }
        val currentPrice = this.convertMoneyToInt(itemDTO.currentPrice)

        if (itemDTO.currentPrice == "") {
            return Item(itemDTO.name, itemDTO.image, matchingItem.averagePrice)
        }

        return Item(itemDTO.name, itemDTO.image, matchingItem.averagePrice, currentPrice)
    }

    private fun convertMoneyToString(money: Int): String {
        val convertedMoney = money.toString()
        val moneyStringLength = convertedMoney.length
        val dollar = convertedMoney.substring(0, moneyStringLength - 2)
        val cent = convertedMoney.substring(moneyStringLength - 2)

        return "$dollar,$cent"
    }

    private fun convertMoneyToInt(money: String): Int {
        val index = money.indexOf(",")
        val firstPart = money.substring(0, index)
        val secondPart = money.substring(index + 1, money.length)

        val moneyWithoutColon = firstPart + secondPart
        return moneyWithoutColon.toInt()
    }
}