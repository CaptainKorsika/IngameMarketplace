package com.projects.inGameMarketplace

import java.sql.Connection
import java.sql.DriverManager

class DatabaseConnector {
    fun connectToDatabase(): Connection {
        val url = "jdbc:mysql://localhost:3306/marketplace_database"
        val user = System.getenv("DB_USER")
        val pw = System.getenv("DB_PW")
        return DriverManager.getConnection(url, user, pw)
    }
}