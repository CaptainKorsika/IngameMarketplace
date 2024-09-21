package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.itemService.Item
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PlayerConverter {

    fun toEntity(player: Player): PlayerEntity {
        return PlayerEntity(
            player.name,
            player.money,
            player.inventorySpace,
            player.day )
    }

    fun toDomain(entity: PlayerEntity): Player {

        return Player(
            entity.playerName,
            entity.money,
            entity.inventorySpace,
            entity.day
        )
    }

}