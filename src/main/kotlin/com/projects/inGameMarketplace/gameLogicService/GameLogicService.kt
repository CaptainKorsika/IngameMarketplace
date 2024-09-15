package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.highScoreService.HighScoreService
import com.projects.inGameMarketplace.inventoryService.InventoryService
import com.projects.inGameMarketplace.itemService.ItemService
import com.projects.inGameMarketplace.playerService.PlayerService
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@CrossOrigin
@RestController
@RequestMapping("/interaction")
class GameLogicService {
    private final val restTemplate = RestTemplate()
    private final val playerService = PlayerService()
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
        inventoryService.createInventory()
    }

    @PostMapping("/endGame")
    fun endGame() {
//        if (!gameCancelled) {
//            val name = playerService.player!!.name
//            highScoreService.addToHighScoreList(name, money!!)
//            this.showHighScore()
//        }

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
        inventoryService.addItem()
        playerService.updatePlayerBalance(2.0)
    }


    fun sellItem() {

    }

    @GetMapping("/buyInventorySpace")
    fun unlockInventory() {
        val money = playerService.player!!.money
        val newBalance = inventoryService.buyInventoryAndReturnNewBalance(money)
        playerService.updatePlayerBalance(newBalance)
    }


    fun showHighScore() {

    }
}