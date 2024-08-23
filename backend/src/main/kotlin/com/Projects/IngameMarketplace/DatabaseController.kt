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
        connection.close()

        // Store the first entry in a variable
        return if (resultSet.next()) {
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
    }


    fun createPlayerEntry(player: Player) {
        val connection = connectToDatabase()
        val statement: Statement = connection.createStatement()

        val itemJSON = Json.parseToJsonElement(player.inventoryItems.toString())
        // val itemJSON = Json.encodeToString(player.inventoryItems)

        val query = """
            INSERT INTO PLAYER(PLAYER_NAME, MONEY, INVENTORY_SPACE, INVENTORY_ITEMS, DAY, IS_PLAYING) VALUES(
            ${player.name}, 
            ${player.money}, 
            ${player.inventorySpace}, 
            ${itemJSON}, 
            ${player.day}, 
            ${player.isPlaying})
        """

        statement.executeQuery(query)
        connection.close()
    }

}

val databaseController: DatabaseController = DatabaseController()