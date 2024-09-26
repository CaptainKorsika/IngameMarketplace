package com.projects.inGameMarketplace.itemService

import com.projects.inGameMarketplace.DatabaseConnector
import java.sql.ResultSet
import java.sql.Statement

class ItemRepository {
    private val databaseConnector = DatabaseConnector()

    fun getAvailableItems(): List<ItemEntity> {
        val connection = databaseConnector.connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM items;"

        val resultSet: ResultSet = statement.executeQuery(query)


        val listBuilder = mutableListOf<ItemEntity>()

        while (resultSet.next()) {
            val name = resultSet.getString("item_name")
            val image = resultSet.getString("image_url")
            val price = resultSet.getDouble("price")

            val entity = ItemEntity(name, image, price)
            listBuilder.add(entity)
        }

        resultSet.close()
        connection.close()
        return listBuilder.toList()
    }
}