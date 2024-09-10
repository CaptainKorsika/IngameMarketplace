package com.projects.inGameMarketplace

class HighScoreService {
    val highScores: List<Pair<String, Int>> = getHighScoreList()


    fun getHighScoreList(): List<Pair<String, Int>>  {

        return mutableListOf()
    }


    fun addToHighScoreList(playerName: String, finalScore: Int) {
        if (checkForNewHighScore(finalScore)) {
            // add to HighWcore List
        }
    }

    fun deleteFromList() {

    }

    private fun checkForNewHighScore(finalScore: Int): Boolean {
        val lowestScore = this.highScores.minBy { it.second }.second
        return finalScore > lowestScore
    }

}