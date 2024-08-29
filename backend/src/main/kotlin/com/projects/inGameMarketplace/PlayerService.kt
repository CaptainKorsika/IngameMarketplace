package com.projects.inGameMarketplace

import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/playerService")
class PlayerService {
    var player: Player? = getPlayers()

    private fun getPlayers(): Player? {
        println("i am here")
        return databaseService.getPlayerIfAvailable()
    }

    @GetMapping("/gameRunning")
    fun playerAlreadyCreated(): Boolean {
        return this.player != null
    }

    @PostMapping("/createPlayer")
    fun createPlayer(@RequestBody playerName: String) {
        this.player = Player(playerName = playerName)
        databaseService.createPlayerEntry(this.player!!)
    }

    @PostMapping("/deletePlayer")
    fun deletePlayer() {
        databaseService.deletePlayerEntry()
        this.player = null
    }
}