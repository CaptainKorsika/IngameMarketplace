package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.DatabaseConnector
import java.sql.ResultSet
import java.sql.Statement

class PlayerRepository {
    private val databaseConnector = DatabaseConnector()



    fun getPLayerFromDB() {

    }

    fun getPlayer(): PlayerEntity? {
        val connection = databaseConnector.connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM player;"

        // Execute the query
        val resultSet: ResultSet = statement.executeQuery(query)
        val hasEntry = resultSet.next()

        if (!hasEntry) {
            resultSet.close()
            connection.close()
            return null
        }

        val playerEntity = PlayerEntity(
            resultSet.getString("player_name"),
            resultSet.getInt("inventory_space"),
            resultSet.getInt("day"),
            resultSet.getDouble("money"),
        )

        resultSet.close()
        connection.close()
        return playerEntity
    }

    fun createPlayerEntry(entity: PlayerEntity): Boolean {
        try {
            val connection = databaseConnector.connectToDatabase()
            val query = """
            INSERT INTO player(player_name, inventory_space, day, money) VALUES(?, ?, ?, ?)
        """

            val preparedStatement = connection.prepareStatement(query)
            preparedStatement.setString(1, entity.playerName)
            preparedStatement.setInt(2, entity.inventorySpace)
            preparedStatement.setInt(3, entity.day)
            preparedStatement.setDouble(4, entity.money)

            preparedStatement.executeUpdate()
            connection.close()

            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun updatePlayerInDB(entity: PlayerEntity): Boolean {
        // TODO: Implement logic
        return false
    }

    fun deletePlayer() {
        val connection = databaseConnector.connectToDatabase()
        val query = """
            DELETE FROM player;
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.executeUpdate()
        connection.close()
    }
}


