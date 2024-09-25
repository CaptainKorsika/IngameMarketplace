package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.DatabaseConnector
import com.projects.inGameMarketplace.itemService.ItemEntity
import java.sql.ResultSet
import java.sql.Statement

class InventoryRepository {
    private val databaseConnector = DatabaseConnector()

    fun saveInventory(inventory: InventoryEntity) {
        val connection = databaseConnector.connectToDatabase()

        val query = StringBuilder("INSERT INTO players (player_name, score) VALUES ")

        inventory.currentItems.forEachIndexed { index, _ ->
            if (index > 0) query.append(", ")
            query.append("(?, ?)")
        }

        val preparedStatement = connection.prepareStatement(query.toString())

        var paramIndex = 1
        inventory.currentItems.forEach { (name, score) ->
            preparedStatement.setString(paramIndex++, name)
            preparedStatement.setInt(paramIndex++, score)
        }

        preparedStatement.executeUpdate()
        connection.close()

    }

    fun loadInventory(): InventoryEntity? {
        val connection = databaseConnector.connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM inventory;"

        val resultSet: ResultSet = statement.executeQuery(query)


        val itemList = mutableListOf<Pair<String, Int>>()
        var counter = 0
        while(resultSet.next()) {
            val name = resultSet.getString("item_name")
            val amount = resultSet.getInt("amount")


            itemList.add(name to amount)
            counter++
        }

        if (counter == 0) {
            return null
        }

        return InventoryEntity(itemList)
        // TODO: Find a way to create Item object

    }

    fun deleteInventory() {
        val connection = databaseConnector.connectToDatabase()
        val query = """
            DELETE FROM inventory;
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.executeUpdate()
        connection.close()


    }
}