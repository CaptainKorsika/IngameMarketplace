package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.itemService.Item
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class PlayerConverter {

    fun toEntity(player: Player): PlayerEntity {
        val items = player.inventoryItems
        val itemJsonList = Json.encodeToString(items)

        return PlayerEntity(
            player.name,
            player.money,
            player.inventorySpace,
            itemJsonList,
            player.day )
    }

    fun toDomain(entity: PlayerEntity): Player {

        val itemJson = entity.inventoryItems
        val itemList = Json.decodeFromString<List<Pair<Item, Int>>>(itemJson)

        return Player(
            entity.playerName,
            entity.money,
            entity.inventorySpace,
            itemList,
            entity.day
        )
    }

}