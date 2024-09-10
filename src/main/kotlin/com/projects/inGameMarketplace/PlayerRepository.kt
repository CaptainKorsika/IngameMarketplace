package com.projects.inGameMarketplace

import kotlinx.serialization.json.Json

class PlayerRepository {


    fun convertPlayerToEntity(player: Player): PlayerEntity {
        // TODO: Work in Progress
        val items = player.inventoryItems


        return PlayerEntity(player.name, player.money, player.inventorySpace, "items", player.day )
    }

    fun convertPlayerEntityToDomain(entity: PlayerEntity): Player {




        return Player("Placeholder")
    }

}


