package com.projects.inGameMarketplace

import org.springframework.web.client.RestTemplate

class GameLogicService {
    val restTemplate = RestTemplate()

    val databaseService = DatabaseService()
    val playerService = PlayerService()
    val itemService = ItemService()
    val inventoryService = InventoryService(restTemplate)


    fun nextDay() {
        // update player Day
        // if Day > 100 -> endGame
        // write player data into DB
        // update itemList for merchants
        // update item prices
    }

    fun endGame() {
        // Write Name and Money into DB
        // Show Highscore Screen
        // Delete Player Object
    }


}