package com.projects.inGameMarketplace.highScoreService

import com.projects.inGameMarketplace.DatabaseConnector
import java.sql.ResultSet
import java.sql.Statement

class HighScoreRepository {
    private val databaseConnector = DatabaseConnector()

    fun getHighScoresFromDB(): List<ScoreEntity> {
        val connection = databaseConnector.connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM high_score;"

        val resultSet: ResultSet = statement.executeQuery(query)


        val listBuilder = mutableListOf<ScoreEntity>()

        while (resultSet.next()) {
            val name = resultSet.getString("player_name")
            val score = resultSet.getInt("score")

            val entity = ScoreEntity(name, score)
            listBuilder.add(entity)
        }

        resultSet.close()
        connection.close()
        return listBuilder.toList()
    }

    fun addScoreToDB(score: ScoreEntity) {
        val count = this.countHighScoreEntries()
        val query = if (count >= 100) {
            """
            UPDATE high_score
            SET player_name = ?, score = ?
            WHERE score = (SELECT MIN(score) FROM high_score)
            LIMIT 1;
        """

        } else {
            "INSERT INTO high_score(player_name, score) VALUES(?, ?);"
        }

        val connection = databaseConnector.connectToDatabase()
        val statement = connection.prepareStatement(query)

        statement.setString(1, score.name)
        statement.setInt(2, score.money)

        statement.executeUpdate()
        connection.close()
    }



    private fun countHighScoreEntries(): Int {
        val connection = databaseConnector.connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT COUNT(*) FROM high_score;"

        val resultSet: ResultSet = statement.executeQuery(query)
        resultSet.next()
        val count = resultSet.getInt(1)

        resultSet.close()
        connection.close()

        return count
    }

}