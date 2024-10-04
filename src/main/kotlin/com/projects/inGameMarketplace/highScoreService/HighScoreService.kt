package com.projects.inGameMarketplace.highScoreService

class HighScoreService {
    private val highScoreRepository = HighScoreRepository()
    private val scoreConverter = ScoreConverter()
    private val maxHighScoreListSize = 20
    val highScores: MutableList<Score> = this.getHighScoreList()



    fun getHighScoreList(): MutableList<Score>  {
        val highScoreEntityList = highScoreRepository.getHighScoresFromDB()
        val calcEffectiveListSize = minOf(maxHighScoreListSize, highScoreEntityList.size)
        val finalizedEntityList = highScoreEntityList.subList(0, calcEffectiveListSize)

        val highScoreList = mutableListOf<Score>()

        finalizedEntityList.forEach {
            highScoreList.add(scoreConverter.toDomain(it))
        }

        return highScoreList
    }

    fun addToHighScoreList(playerName: String, finalScore: Int) {
        val roundedScore = (finalScore / 100).toInt()

        if (checkForNewHighScore(roundedScore)) {
            Score(playerName, roundedScore)

            val newScore = Score(playerName, roundedScore)
            this.highScores.add(newScore)

            if (this.highScores.size > maxHighScoreListSize) {
                val lowestScore = this.highScores.minBy { it.money }
                this.highScores.remove(lowestScore)
            }

            val scoreEntity = scoreConverter.toEntity(newScore)
            highScoreRepository.addScoreToDB(scoreEntity)
        }
    }

    private fun checkForNewHighScore(finalScore: Int): Boolean {
        if (this.highScores.size < maxHighScoreListSize) {
            return true
        }

        val lowestScore = this.highScores.minBy { it.money }
        return finalScore > lowestScore.money
    }

}