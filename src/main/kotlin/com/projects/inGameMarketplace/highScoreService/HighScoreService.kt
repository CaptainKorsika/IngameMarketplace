package com.projects.inGameMarketplace.highScoreService

class HighScoreService {
    private val highScoreRepository = HighScoreRepository()
    val highScores: List<Pair<String, Int>> = getHighScoreList()


    fun getHighScoreList(): List<Pair<String, Int>>  {

        return mutableListOf()
    }


    fun addToHighScoreList(playerName: String, finalScore: Int) {
        if (checkForNewHighScore(finalScore)) {
            Score(playerName, finalScore)
            // add to HighScore List
            // delete lowest from high score list
            // update DB
            highScoreRepository.updateHighScore(this.highScores)
        }
    }

    fun eraseList() {

    }

    private fun checkForNewHighScore(finalScore: Int): Boolean {
        val lowestScore = this.highScores.minBy { it.second }.second
        return finalScore > lowestScore
    }

}