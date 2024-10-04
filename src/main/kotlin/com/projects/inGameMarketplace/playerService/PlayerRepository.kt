package com.projects.inGameMarketplace.playerService

import com.projects.inGameMarketplace.DatabaseConnector
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class PlayerRepository {
    private val databaseConnector = DatabaseConnector()

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
            resultSet.getInt("money")
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

            val statement = connection.prepareStatement(query)

            statement.setString(1, entity.playerName)
            statement.setInt(2, entity.inventorySpace)
            statement.setInt(3, entity.day)
            statement.setInt(4, entity.money)

            statement.executeUpdate()
            connection.close()

            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun updatePlayerInDB(entity: PlayerEntity) {
        val connection = databaseConnector.connectToDatabase()
        val query = """
            UPDATE player
            SET inventory_space = ?, day = ?, money = ?
            WHERE player_name = ?;
        """
        val statement = connection.prepareStatement(query)

        statement.setInt(1, entity.inventorySpace)
        statement.setInt(2, entity.day)
        statement.setInt(3, entity.money)
        statement.setString(4, entity.playerName)

        statement.executeUpdate()
        connection.close()
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


