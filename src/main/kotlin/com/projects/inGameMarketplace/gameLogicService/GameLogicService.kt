package com.projects.inGameMarketplace.gameLogicService

import com.projects.inGameMarketplace.highScoreService.HighScoreDTO
import com.projects.inGameMarketplace.highScoreService.HighScoreService
import com.projects.inGameMarketplace.inventoryService.Inventory
import com.projects.inGameMarketplace.inventoryService.InventoryDTO
import com.projects.inGameMarketplace.inventoryService.InventoryService
import com.projects.inGameMarketplace.itemService.ItemDTO
import com.projects.inGameMarketplace.itemService.ItemService
import com.projects.inGameMarketplace.merchantService.MerchantService
import com.projects.inGameMarketplace.playerService.Player
import com.projects.inGameMarketplace.playerService.PlayerService
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/interaction")
class GameLogicService() {
    private final val playerService = PlayerService()
    val merchantService = MerchantService()
    val itemService = ItemService()
    val inventoryService = InventoryService()
    val highScoreService = HighScoreService()
    val itemMapper = ItemMapper()
    val inventoryMapper = InventoryMapper()

    // TODO: Change back to 100 after testing
    val gameLength = 5

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
        if (gameOver) {
            val money = playerService.player!!.money
            val name = playerService.player!!.name
            highScoreService.addToHighScoreList(name, money)
            this.showHighScore()
        }
        playerService.deletePlayer()
        inventoryService.deleteInventory()
    }

    @GetMapping("/getData")
    fun getGameData(): GameDataDTO? {
        val player: Player? = playerService.fetchPlayerData()
        val inventoryDTO = this.getCurrentInventory()

        if (player == null) {
            return null
        }

        val moneyString = convertMoneyToString(player.money)

        return GameDataDTO(player.inventorySpace, player.day, moneyString, inventoryDTO.currentItems)
    }

    @GetMapping("/merchantInventory")
    fun getMerchantInventory(): List<InventoryDTO> {
        val firstMerchantDTO: InventoryDTO = inventoryMapper.mapToInventoryDTO(merchantService.firstMerchant.dailyInventory)
        val secondMerchantDTO: InventoryDTO = inventoryMapper.mapToInventoryDTO(merchantService.secondMerchant.dailyInventory)
        val thirdMerchantDTO: InventoryDTO = inventoryMapper.mapToInventoryDTO(merchantService.thirdMerchant.dailyInventory)


        val merchantInventoryList: List<InventoryDTO> = listOf(firstMerchantDTO, secondMerchantDTO, thirdMerchantDTO)
        return merchantInventoryList
    }

    @GetMapping("/nextDay")
    fun nextDay(): List<InventoryDTO> {
        playerService.nextDay()
        val currentDay = playerService.player!!.day

        // TODO: Find better way for exiting | try/catch
        if (currentDay > gameLength) {
            this.endGame(true)
            return listOf()
        }
        merchantService.createNewDailyInventory()
        playerService.updatePlayerData()
        inventoryService.updateInventory()

        return getMerchantInventory()
    }

    @PostMapping("/buyItem")
    fun buyItem(@RequestBody newItem: Pair<ItemDTO, Int>) {
        val newDomainItem = itemMapper.mapToItemObject(newItem.first) to newItem.second

        val player = playerService.player!!
        val price = newDomainItem.first.currentPrice * newItem.second
        if (player.money >= price) {
            val purchaseSuccessful = inventoryService.addItemAndConfirm(newDomainItem, player.inventorySpace)
            if (purchaseSuccessful) {
                playerService.updatePlayerBalance(price * -1)
            }
        }
    }

    @PostMapping("/sellItem")
    fun sellItem(@RequestBody soldItem: Pair<ItemDTO, Int>) {
        println(soldItem)
        val newDomainItem = itemMapper.mapToItemObject(soldItem.first) to soldItem.second

        val inventory = inventoryService.inventory
        val itemAmount = inventory.currentItems.first { it.first.name == newDomainItem.first.name }.second
        val amountToSell = if (newDomainItem.second >= itemAmount) itemAmount else soldItem.second
        val revenue = newDomainItem.first.currentPrice * amountToSell

        val itemRemovedSuccessfully = inventoryService.removeItemAndConfirm(newDomainItem)
        if (itemRemovedSuccessfully) {
            playerService.updatePlayerBalance(revenue)
        }
    }

    @GetMapping("/getInventory")
    fun getCurrentInventory(): InventoryDTO {
        val inventoryDTO = inventoryMapper.mapToInventoryDTO(inventoryService.inventory)
        return inventoryDTO
    }


    @GetMapping("/buyInventorySpace")
    fun unlockInventory() {
        val player = playerService.player!!
        val money = player.money
        val price = inventoryService.buyInventoryAndReturnPrice(money, player.inventorySpace)

        if (money >= price) {
            playerService.updatePlayerBalance(price * -1)
            playerService.addInventorySpace()
        }
    }

    @GetMapping("showHighScores")
    fun showHighScore(): List<HighScoreDTO> {
        val dtoList = mutableListOf<HighScoreDTO>()

        highScoreService.highScores.forEach {
            dtoList.add(HighScoreDTO(it.name, it.money))
        }

        return dtoList
    }

    private fun convertMoneyToString(money: Int): String {
        val convertedMoney = money.toString()
        val moneyStringLength = convertedMoney.length
        val dollar = convertedMoney.substring(0, moneyStringLength - 2)
        val cent = convertedMoney.substring(moneyStringLength - 2)

        return "$dollar,$cent"
    }


}