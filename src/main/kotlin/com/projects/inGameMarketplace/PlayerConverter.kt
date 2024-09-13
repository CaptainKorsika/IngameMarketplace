package com.projects.inGameMarketplace

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PlayerConverter {

    fun toEntity(player: Player): PlayerEntity {
        // TODO: Work in Progress
        val items = player.inventoryItems
        val itemJsonList = Json.encodeToString(items)


        return PlayerEntity(player.name, player.money, player.inventorySpace, itemJsonList, player.day )
    }

    fun toDomain(entity: PlayerEntity): Player {




        return Player("Placeholder")
    }

}