package com.Projects.IngameMarketplace

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/api")
class GameMenuController {
    var gameIsRunning: Boolean = gameCurrentlyRunning()

    @GetMapping("/game-status")
    final fun gameCurrentlyRunning(): Boolean {
        val isRunning = !databaseController.getPlayer().isNullOrEmpty()
        return isRunning
    }
}

val gameMenuController = GameMenuController()