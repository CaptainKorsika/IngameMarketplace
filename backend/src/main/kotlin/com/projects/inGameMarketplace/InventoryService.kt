package com.projects.inGameMarketplace

import com.fasterxml.jackson.databind.ObjectMapper
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
class InventoryService(@Autowired val restTemplate: RestTemplate) {
    val firstExtensionPrice: Int = 1000
    val secondExtensionPrice: Int = 2000

    @GetMapping("/buyInventorySpace")
    fun buyInventory(): Int {
        val player: Player? = callGetPlayerDataApi()
        if (player != null && player.inventorySpace < 3) {
            var wasUpdated = false
            when(player.inventorySpace) {
                1 -> {
                    if (player.money >= firstExtensionPrice) {
                        player.inventorySpace = 2
                        player.money -= firstExtensionPrice
                        wasUpdated = true
                    }
                }
                2 -> {
                    if (player.money >= secondExtensionPrice) {
                        player.inventorySpace = 3
                        player.money -= secondExtensionPrice
                        wasUpdated = true
                    }
                }
            }
            if (wasUpdated) {
                this.updatePlayer(player)
            }
        }
        return callGetPlayerDataApi()!!.inventorySpace
    }



    private fun callGetPlayerDataApi(): Player? {
        val url = "http://localhost:8080/playerService/getPlayer"
        return try {
            restTemplate.getForObject(url, Player::class.java)
        } catch (e: Exception) {
            println("Error occurred while fetching player data: ${e.message}")
            null
        }
    }


    private fun updatePlayer(player: Player) {
        val url = "http://localhost:8080/playerService/updatePlayer"
        val playerJson = ObjectMapper().writeValueAsString(player)
        val headers = org.springframework.http.HttpHeaders().apply {
            contentType = MediaType.APPLICATION_JSON
        }
        val requestEntity = HttpEntity(playerJson, headers)
        restTemplate.postForEntity(url, requestEntity, String::class.java)
    }
}