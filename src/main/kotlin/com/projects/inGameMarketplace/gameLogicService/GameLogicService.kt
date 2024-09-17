package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.highScoreService.HighScoreService
import com.projects.inGameMarketplace.inventoryService.InventoryService
import com.projects.inGameMarketplace.itemService.Item
import com.projects.inGameMarketplace.itemService.ItemService
import com.projects.inGameMarketplace.playerService.Player
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
    val inventoryService = InventoryService()
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

    @GetMapping("/getData")
    fun getGameData() {
        val player: Player = playerService.player!!
        val inventory = inventoryService.inventory
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

    fun buyItem(newItem: Pair<Item, Int>) {
        val price = newItem.first.currentPrice * newItem.second
        if (playerService.player!!.money >= price) {
            val purchaseSuccessful = inventoryService.addItemAndValidate(newItem)
            if (purchaseSuccessful) {
                playerService.updatePlayerBalance(price * -1)
            }
        }
    }


    fun sellItem(soldItem: Pair<Item, Int>) {
        val inventory = inventoryService.inventory!!
        val itemAmount = inventory.currentItems.filter { it!!.first.name == soldItem.first.name }.sumOf { it?.second ?: 0 }
        val amountToSell = if (soldItem.second >= itemAmount) itemAmount else soldItem.second
        val revenue = soldItem.first.currentPrice * amountToSell
        inventoryService.removeItem(soldItem)
        playerService.updatePlayerBalance(revenue)
    }

    fun getCurrentInventory() {

    }


    @GetMapping("/buyInventorySpace")
    fun unlockInventory() {
        val money = playerService.player!!.money
        val price = inventoryService.buyInventoryAndReturnNewBalance(money)
        playerService.updatePlayerBalance(price * -1)
    }


    fun showHighScore() {

    }
}