package com.Projects.IngameMarketplace

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@CrossOrigin
@RestController
@RequestMapping("/startGame")
class StartGameController {

    @PostMapping("/createPlayer")
    fun createPlayer(@RequestBody playerName: String): ResponseEntity<String> {
        val newPlayer = Player()
        newPlayer.submitName(playerName)
        databaseController.createPlayerEntry(newPlayer)
        return ResponseEntity.ok("Player built successfully")
    }
}


