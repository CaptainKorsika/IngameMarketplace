package com.projects.inGameMarketplace.highScoreService

class ScoreConverter {
    fun toDomain(entity: ScoreEntity): Score {
        val score = Score(entity.name, entity.money)
        return score
    }


    fun toEntity(score: Score): ScoreEntity {
        val entity = ScoreEntity(score.name, score.money)
        return entity
    }

}