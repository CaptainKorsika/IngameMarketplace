package com.projects.inGameMarketplace

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement

class DatabaseService {
    fun getPlayerIfAvailable(): Player? {
        val connection = connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM PLAYER;"

        // Execute the query
        val resultSet: ResultSet = statement.executeQuery(query)
        val hasEntry = resultSet.next()

        if (!hasEntry) {
            resultSet.close()
            connection.close()
            return null
        }

        val inventoryItems = resultSet.getString("INVENTORY_ITEMS")
        val inventoryList: List<Pair<Item, Int>> = Json.decodeFromString(inventoryItems)

        val player = Player(
            resultSet.getString("PLAYER_NAME"),
            resultSet.getDouble("MONEY"),
            resultSet.getInt("INVENTORY_SPACE"),
            inventoryList,
            resultSet.getInt("DAY")
            )

        resultSet.close()
        connection.close()
        return player
    }

    fun createPlayerEntry(player: Player) {
        val connection = connectToDatabase()
        val query = """
            INSERT INTO PLAYER(PLAYER_NAME, MONEY, INVENTORY_SPACE, INVENTORY_ITEMS, DAY) VALUES(?, ?, ?, ?, ?)
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, player.name)
        preparedStatement.setDouble(2, player.money)
        preparedStatement.setInt(3, player.inventorySpace)
        preparedStatement.setObject(4, player.inventoryItems)
        preparedStatement.setInt(5, player.day)

        preparedStatement.executeUpdate()
        connection.close()
    }

    fun deletePlayerEntry() {
        val connection = connectToDatabase()
        val query = """
            DELETE FROM PLAYER;
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.executeUpdate()
        connection.close()
    }

//    fun buyInventory(newSpace: Int) {
//        val connection = connectToDatabase()
//        val query = """
//            UPDATE PLAYER SET INVENTORY_SPACE = ?
//        """
//        val preparedStatement = connection.prepareStatement(query)
//        preparedStatement.setInt(1, newSpace)
//        preparedStatement.executeUpdate()
//        connection.close()
//    }



    private fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:E:src/main/resources/database.sql"
        return DriverManager.getConnection(url)
    }


}

val databaseService: DatabaseService = DatabaseService()