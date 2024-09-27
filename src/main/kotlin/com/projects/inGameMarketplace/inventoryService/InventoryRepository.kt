package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.DatabaseConnector
import com.projects.inGameMarketplace.itemService.Item
import java.sql.ResultSet
import java.sql.Statement

class InventoryRepository {
    private val databaseConnector = DatabaseConnector()

    fun saveInventory(inventory: InventoryEntity) {
        val connection = databaseConnector.connectToDatabase()

        val query = StringBuilder("INSERT INTO inventory (item_name, amount) VALUES ")

        inventory.currentItems.forEachIndexed { index, _ ->
            if (index > 0) query.append(", ")
            query.append("(?, ?)")
        }

        val preparedStatement = connection.prepareStatement(query.toString())

        var paramIndex = 1
        inventory.currentItems.forEach { (item, amount) ->
            preparedStatement.setString(paramIndex++, item.name)
            preparedStatement.setInt(paramIndex++, amount)
        }

        preparedStatement.executeUpdate()
        connection.close()

    }

    fun loadInventory(): InventoryEntity {
        val connection = databaseConnector.connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = """
           SELECT inventory.item_name as item_name, items.image_url, items.price, amount 
           FROM inventory  LEFT JOIN items 
           ON inventory.item_name = items.item_name; 
        """

        val resultSet: ResultSet = statement.executeQuery(query)


        val itemList = mutableListOf<Pair<Item, Int>>()
        while(resultSet.next()) {
            val name = resultSet.getString("item_name")
            val image = resultSet.getString("image_url")
            val price = resultSet.getDouble("price")
            val amount = resultSet.getInt("amount")

            val item = Item(name, image, price)

            itemList.add(item to amount)
        }

        return InventoryEntity(itemList)
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