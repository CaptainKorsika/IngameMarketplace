package com.projects.inGameMarketplace

import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@CrossOrigin
@RestController
@RequestMapping("/interaction")
class GameLogicService {
    val restTemplate = RestTemplate()
    val playerService = PlayerService()
    val itemService = ItemService()
    val inventoryService = InventoryService(restTemplate)
    val highScoreService = HighScoreService()
    var isCurrentlyRunning = this.checkForRunningGame()

    @GetMapping("/gameRunning")
    fun checkForRunningGame(): Boolean {
        return playerService.player != null
    }

    @PostMapping("/createPlayer")
    fun startGame(@RequestBody playerName: String) {
        playerService.createPlayer(playerName)

    }

    @PostMapping("/endGame")
    fun endGame(@RequestBody gameCancelled: Boolean, @RequestBody money: Int?) {
        if (!gameCancelled) {
            val name = playerService.player!!.name
            highScoreService.addToHighScoreList(name, money!!)
            this.showHighScore()
            // Show Highscore Screen
        }

        playerService.deletePlayer()



        // Delete Player Object
    }

    fun nextDay() {
        // update player Day
        // if Day > 100 -> endGame
        // write player data into DB
        // update itemList for merchants
        // update item prices
    }


    fun selectMerchant() {

    }


    fun selectItem() {

    }

    fun buyItem() {

    }


    fun sellItem() {

    }



    fun unlockInventory() {

    }


    fun showHighScore() {

    }
}