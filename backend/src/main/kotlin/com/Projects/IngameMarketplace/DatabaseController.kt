package com.Projects.IngameMarketplace

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import javax.xml.crypto.Data

class DatabaseController {

    fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:E:src/main/resources/database.sql"
        return DriverManager.getConnection(url)
    }


    fun getPlayer(): Map<String, Any?>? {
        val connection = connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM PLAYER;"

        // Execute the query
        val resultSet: ResultSet = statement.executeQuery(query)

        // Store the first entry in a variable
        val result = if (resultSet.next()) {
            val metadata = resultSet.metaData
            val columnCount = metadata.columnCount
            val row = mutableMapOf<String, Any?>()

            for (i in 1..columnCount) {
                row[metadata.getColumnName(i)] = resultSet.getObject(i)
            }
            row
        } else {
            null
        }
        resultSet.close()
        connection.close()
        return result
    }


    fun createPlayerEntry(player: Player) {
        val connection = connectToDatabase()

        val itemJSON = Json.encodeToString(player.inventoryItems.toString())

        val query = """
            INSERT INTO PLAYER(PLAYER_NAME, MONEY, INVENTORY_SPACE, INVENTORY_ITEMS, DAY, IS_PLAYING) VALUES(?, ?, ?, ?, ?, ?)
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, player.name)
        preparedStatement.setInt(2, player.money)
        preparedStatement.setInt(3, player.inventorySpace)
        preparedStatement.setObject(4, itemJSON)
        preparedStatement.setInt(5, player.day)
        preparedStatement.setInt(6, player.isPlaying)

        preparedStatement.executeUpdate()
        connection.close()
    }

}

val databaseController: DatabaseController = DatabaseController()