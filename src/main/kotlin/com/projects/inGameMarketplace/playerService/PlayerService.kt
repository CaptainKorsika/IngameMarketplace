package com.projects.inGameMarketplace.playerService

import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/interaction")
class PlayerService {
    val playerRepository = PlayerRepository()
    val playerConverter = PlayerConverter()
    var player: Player? = this.getPlayerIfAvailable()


    @GetMapping("/getPlayer")
    fun fetchPlayerData(): Player? {
        return this.player
    }

    fun createPlayer(playerName: String) {
        this.player = Player(name = playerName)
        val entity = playerConverter.toEntity(this.player!!)
        val playerCreatedSuccessfully = playerRepository.createPlayerEntry(entity)
        if (playerCreatedSuccessfully) {
            this.player = player
        }
    }

    fun deletePlayer() {
        deletePlayerEntry()
        this.player = null
    }

    @PostMapping("/updatePlayer")
    fun updatePlayerData(@RequestBody updatedPlayer: Player) {
        this.player = updatedPlayer
    }

    private fun getPlayerIfAvailable(): Player? {
        val entity = playerRepository.getPlayer()
        return if (entity != null) {
            playerConverter.toDomain(entity)
        } else {
            null
        }
    }

    private fun deletePlayerEntry() {
        playerRepository.deletePlayer()
    }
}