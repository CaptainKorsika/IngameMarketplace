package com.projects.inGameMarketplace

import java.sql.Connection
import java.sql.DriverManager

class DatabaseConnector {
//    fun connectToDatabaseOld(): Connection {
//        val url = "jdbc:sqlite:E:src/main/resources/old_database.sql"
//        return DriverManager.getConnection(url)
//    }

    fun connectToDatabase(): Connection {
        val url = "jdbc:mysql://localhost:3306/marketplace_database"
        val user = System.getenv("DB_USER")
        val pw = System.getenv("DB_PW")
        return DriverManager.getConnection(url, user, pw)
    }
}