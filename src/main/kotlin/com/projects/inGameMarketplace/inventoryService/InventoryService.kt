package com.projects.inGameMarketplace.inventoryService

import com.fasterxml.jackson.databind.ObjectMapper
import com.projects.inGameMarketplace.itemService.Item
import com.projects.inGameMarketplace.playerService.Player
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@CrossOrigin
@RestController
@RequestMapping("/inventoryService")
class InventoryService(
    @Autowired val restTemplate: RestTemplate) {
    val inventory: Inventory? = getPlayerInventoryFromDB()
    var inventorySpace = 10
    val firstExtensionPrice: Int = 1000
    val secondExtensionPrice: Int = 2000


    fun createInventory() {
        // create DB entry
        this.getPlayerInventoryFromDB()
    }


    private final fun getPlayerInventoryFromDB(): Inventory? {
        val inventory = Inventory()
        return inventory
    }

    fun buyInventoryAndReturnNewBalance(money: Double): Double {
        var currentMoney = money
        when(this.inventorySpace) {
            10 -> {
                if (currentMoney >= firstExtensionPrice) {
                    this.inventorySpace = 20
                    currentMoney -= firstExtensionPrice
                }
            }
            20 -> {
                if (currentMoney >= secondExtensionPrice) {
                    this.inventorySpace = 30
                    currentMoney -= secondExtensionPrice
                }
            }
        }
        return currentMoney
    }

    fun addItem() {

    }

    fun removeItem() {

    }


//    private fun callGetPlayerDataApi(): Player? {
//        val url = "http://localhost:8080/playerService/getPlayer"
//        return try {
//            restTemplate.getForObject(url, Player::class.java)
//        } catch (e: Exception) {
//            println("Error occurred while fetching player data: ${e.message}")
//            null
//        }
//    }
//
//
//    private fun updatePlayer(player: Player) {
//        val url = "http://localhost:8080/playerService/updatePlayer"
//        val playerJson = ObjectMapper().writeValueAsString(player)
//        val headers = org.springframework.http.HttpHeaders().apply {
//            contentType = MediaType.APPLICATION_JSON
//        }
//        val requestEntity = HttpEntity(playerJson, headers)
//        restTemplate.postForEntity(url, requestEntity, String::class.java)
//    }


    //    fun buyInventory(newSpace: Int) {
//        val connection = connectToDatabase()
//        val query = """
//            UPDATE PLAYER SET INVENTORY_SPACE = ?
//        """
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setInt(1, newSpace)
//        preparedStatement.executeUpdate()
//        connection.close()
//    }


}