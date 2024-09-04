package com.projects.inGameMarketplace

import java.sql.Connection
import java.sql.DriverManager

class DatabaseConnector {
    fun connectToDatabase(): Connection {
        val url = "jdbc:sqlite:E:src/main/resources/database.sql"
        return DriverManager.getConnection(url)
    }

}