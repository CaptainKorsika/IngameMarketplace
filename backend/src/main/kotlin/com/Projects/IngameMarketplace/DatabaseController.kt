package com.Projects.IngameMarketplace

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.Statement
import javax.xml.crypto.Data

class DatabaseController {

    fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:E:\\Work\\Programmieren\\Projects\\IngameMarketplace\\backend\\src\\main\\resources\\database.sql"
        return DriverManager.getConnection(url)
    }


    fun getPlayer(): Map<String, Any?>? {
        val connection = connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM PLAYER;"


        // Execute the query
        val resultSet: ResultSet = statement.executeQuery(query)

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
}

val databaseController: DatabaseController = DatabaseController()