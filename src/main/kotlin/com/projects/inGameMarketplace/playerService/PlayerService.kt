package com.projects.inGameMarketplace.playerService

import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/interaction")
class PlayerService {
    private val playerRepository = PlayerRepository()
    private val playerConverter = PlayerConverter()
    var player: Player? = this.getPlayerIfAvailable()


    fun fetchPlayerData(): Player? {
        return this.player
    }

    fun createPlayer(playerName: String) {
        val newPlayer = Player(name = playerName)
        val entity = playerConverter.toEntity(newPlayer)
        val playerCreatedSuccessfully = playerRepository.createPlayerEntry(entity)
        if (playerCreatedSuccessfully) {
            this.player = newPlayer
        }
    }

    fun deletePlayer() {
        deletePlayerEntry()
        this.player = null
    }

    fun updatePlayerBalance(balanceChange: Double) {
        this.player!!.money += balanceChange
    }

    fun updatePlayerData() {
        val entity = playerConverter.toEntity(this.player!!)
        playerRepository.updatePlayerInDB(entity)
    }

    fun nextDay() {
        this.player!!.day += 1
    }

    fun addInventorySpace() {
        this.player!!.inventorySpace += 10
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