package com.projects.inGameMarketplace

class PlayerRepository {


    fun convertPlayerToEntity(player: Player): PlayerEntity {



        return PlayerEntity()
    }

    fun convertPlayerEntityToDomain(entity: PlayerEntity): Player {




        return Player("Placeholder")
    }

}