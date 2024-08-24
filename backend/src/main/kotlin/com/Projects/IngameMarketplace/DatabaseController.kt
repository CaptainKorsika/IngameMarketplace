package com.projects.inGameMarketplace

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class DatabaseController {
    fun checkForPlayer(): Boolean {
        val connection = connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM PLAYER;"

        // Execute the query
        val resultSet: ResultSet = statement.executeQuery(query)
        val hasEntry = resultSet.next()

        resultSet.close()
        connection.close()
        return hasEntry
    }

    fun createPlayerEntry(player: Player) {
        val connection = connectToDatabase()
        val itemJSON = Json.encodeToString(player.inventoryItems.toString())
        val query = """
            INSERT INTO PLAYER(PLAYER_NAME, MONEY, INVENTORY_SPACE, INVENTORY_ITEMS, DAY) VALUES(?, ?, ?, ?, ?)
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, player.name)
        preparedStatement.setInt(2, player.money)
        preparedStatement.setInt(3, player.inventorySpace)
        preparedStatement.setObject(4, itemJSON)
        preparedStatement.setInt(5, player.day)

        preparedStatement.executeUpdate()
        connection.close()
    }

    private fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:E:src/main/resources/database.sql"
        return DriverManager.getConnection(url)
    }
}

val databaseController: DatabaseController = DatabaseController()