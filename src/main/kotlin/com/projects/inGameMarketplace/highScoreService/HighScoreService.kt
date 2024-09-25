package com.projects.inGameMarketplace.highScoreService

import kotlin.math.roundToInt

class HighScoreService {
    private val highScoreRepository = HighScoreRepository()
    val highScores: List<Pair<String, Int>> = getHighScoreList()


    fun getHighScoreList(): List<Pair<String, Int>>  {

        return mutableListOf()
    }


    fun addToHighScoreList(playerName: String, finalScore: Double) {
        val roundedScore = finalScore.toInt()
        if (checkForNewHighScore(roundedScore)) {
            Score(playerName, roundedScore)
            // add to HighScore List
            // delete lowest from high score list
            // update DB
            highScoreRepository.updateHighScore(this.highScores)
        }
    }

    private fun checkForNewHighScore(finalScore: Int): Boolean {
        val lowestScore = this.highScores.minBy { it.second }.second
        return finalScore > lowestScore
    }

}