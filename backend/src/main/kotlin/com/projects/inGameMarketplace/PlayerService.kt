package com.projects.inGameMarketplace

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/playerService")
class PlayerService {
    var player: Player? = getPlayers()

    private fun getPlayers(): Player? {
        return databaseService.getPlayerIfAvailable()
    }

    @GetMapping("/getPlayer")
    fun fetchPlayerData(): Player? {
        return this.player
    }

    @GetMapping("/gameRunning")
    fun playerAlreadyCreated(): Boolean {
        return this.player != null
    }

    @PostMapping("/createPlayer")
    fun createPlayer(@RequestBody playerName: String) {
        this.player = Player(name = playerName)
        databaseService.createPlayerEntry(this.player!!)
    }

    @PostMapping("/deletePlayer")
    fun deletePlayer() {
        databaseService.deletePlayerEntry()
        this.player = null
    }

    @PostMapping("/updatePlayer")
    fun updatePlayerData(@RequestBody updatedPlayer: Player) {
        this.player = updatedPlayer
    }
}