package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.highScoreService.HighScoreService
import com.projects.inGameMarketplace.inventoryService.Inventory
import com.projects.inGameMarketplace.inventoryService.PlayerInventoryService
import com.projects.inGameMarketplace.itemService.Item
import com.projects.inGameMarketplace.itemService.ItemService
import com.projects.inGameMarketplace.merchantService.MerchantInventoryDTO
import com.projects.inGameMarketplace.merchantService.MerchantService
import com.projects.inGameMarketplace.playerService.Player
import com.projects.inGameMarketplace.playerService.PlayerService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/interaction")
class GameLogicService {
    private final val playerService = PlayerService()
    val merchantService = MerchantService()
    val itemService = ItemService()
    val inventoryService = PlayerInventoryService()
    val highScoreService = HighScoreService()

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
    fun endGame(gameOver: Boolean = false) {
        val money = playerService.player!!.money
        if (gameOver) {
            val name = playerService.player!!.name
            highScoreService.addToHighScoreList(name, money)
            this.showHighScore()
        }
        playerService.deletePlayer()
    }

    @GetMapping("/getData")
    fun getGameData(): GameDataDTO? {
        val player: Player? = playerService.fetchPlayerData()
        val inventory = this.getCurrentInventory()

        if (player == null) {
            return null
        }
        return GameDataDTO(player.inventorySpace, player.day, player.money, inventory)
    }

    @GetMapping("/nextDay")
    fun nextDay(): List<MerchantInventoryDTO> {
        playerService.nextDay()
        val currentDay = playerService.player!!.day

        if (currentDay > 100) {
            this.endGame(true)
        }
        playerService.updatePlayerData()
        merchantService.createNewDailyInventory()

        val firstMerchantDTO = MerchantInventoryDTO(merchantService.firstMerchant.dailyInventory)
        val secondMerchantDTO = MerchantInventoryDTO(merchantService.secondMerchant.dailyInventory)
        val thirdMerchantDTO = MerchantInventoryDTO(merchantService.thirdMerchant.dailyInventory)


        val merchantInventoryList: List<MerchantInventoryDTO> = listOf(firstMerchantDTO, secondMerchantDTO, thirdMerchantDTO)
        return merchantInventoryList
    }

    @PostMapping("/buyItem")
    fun buyItem(newItem: Pair<Item, Int>) {
        val price = newItem.first.currentPrice * newItem.second
        if (playerService.player!!.money >= price) {
            val purchaseSuccessful = inventoryService.addItemAndConfirm(newItem)
            if (purchaseSuccessful) {
                playerService.updatePlayerBalance(price * -1)
            }
        }


    }

    @PostMapping("/sellItem")
    fun sellItem(soldItem: Pair<Item, Int>) {
        val inventory = inventoryService.inventory!!
        val itemAmount = inventory.currentItems.first { it.first.name == soldItem.first.name }.second
        val amountToSell = if (soldItem.second >= itemAmount) itemAmount else soldItem.second
        val revenue = soldItem.first.currentPrice * amountToSell

        val itemRemovedSuccessfully = inventoryService.removeItemAndConfirm(soldItem)
        if (itemRemovedSuccessfully) {
            playerService.updatePlayerBalance(revenue)
        }

    }

    @GetMapping("/getInventory")
    fun getCurrentInventory(): Inventory? {
        return inventoryService.inventory
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