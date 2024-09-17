package com.projects.inGameMarketplace.inventoryService

import com.projects.inGameMarketplace.itemService.Item


class InventoryService() {
    val inventory: Inventory? = getPlayerInventoryFromDB()
    var inventorySpace = 10
    private val firstExtensionPrice: Double = 1000.0
    private val secondExtensionPrice: Double = 2000.0


    fun createInventory() {
        // create DB entry
        this.getPlayerInventoryFromDB()
    }


    private fun getPlayerInventoryFromDB(): Inventory? {
        // TODO: return null if no player
        val inventory = Inventory()
        return inventory
    }

    fun buyInventoryAndReturnNewBalance(money: Double): Double {
        when(this.inventorySpace) {
            10 -> {
                if (money >= firstExtensionPrice) {
                    this.inventorySpace = 20
                    return firstExtensionPrice
                }
            }
            20 -> {
                if (money >= secondExtensionPrice) {
                    this.inventorySpace = 30
                    return secondExtensionPrice
                }
            }
        }
        return 0.0
    }

    fun addItemAndValidate(boughtItem: Pair<Item, Int>): Boolean {

        return true
    }

    fun removeItem(soldItem: Pair<Item, Int>) {

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


}