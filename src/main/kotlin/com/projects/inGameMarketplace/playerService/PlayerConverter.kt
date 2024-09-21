package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.itemService.Item
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PlayerConverter {

    fun toEntity(player: Player): PlayerEntity {
        return PlayerEntity(
            player.name,
            player.inventorySpace,
            player.day,
            player.money
        )
    }

    fun toDomain(entity: PlayerEntity): Player {

        return Player(
            entity.playerName,
            entity.inventorySpace,
            entity.day,
            entity.money
        )
    }

}