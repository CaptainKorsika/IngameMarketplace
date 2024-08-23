package com.Projects.IngameMarketplace

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
@RequestMapping("/start-game")
class StartGameController {
    lateinit var playerName: String


    @PostMapping("/create-player")
    fun createPlayer(@RequestBody playerName: String): ResponseEntity<String> {
        val newPlayer = Player()
        newPlayer.submitName(playerName)
        println(playerName)
        databaseController.createPlayerEntry(newPlayer)
        return ResponseEntity.ok("Player built successfully")
    }
}


