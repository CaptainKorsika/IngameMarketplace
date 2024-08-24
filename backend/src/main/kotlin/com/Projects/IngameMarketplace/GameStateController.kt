package com.projects.inGameMarketplace

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/api")
class GameStateController {
    @GetMapping("/gameStatus")
    final fun gameCurrentlyRunning(): Boolean {
        return databaseController.checkForPlayer()
    }

    @PostMapping("/createPlayer")
    fun createPlayer(@RequestBody playerName: String): ResponseEntity<String> {
        val newPlayer = Player(playerName)
        databaseController.createPlayerEntry(newPlayer)
        return ResponseEntity.ok("Player built successfully")
    }

    @PostMapping("/deletePlayer")
    fun deletePlayer(): ResponseEntity<String> {
        databaseController.deletePlayerEntry()
        return ResponseEntity.ok("Player deleted successfully")
    }
}