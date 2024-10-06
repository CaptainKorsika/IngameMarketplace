package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.itemService.Item
import com.projects.inGameMarketplace.itemService.ItemDTO

class ItemMapper {
//    fun mapToItemDTO(item: Item): ItemDTO {
//
//
//
//
//    }

    fun mapToItemObject(itemDTO: ItemDTO): Item {

        val averagePrice = this.convertMoneyToInt(itemDTO.averagePrice)
        val currentPrice = this.convertMoneyToInt(itemDTO.currentPrice)


        return Item(itemDTO.name, itemDTO.image, averagePrice, currentPrice)
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