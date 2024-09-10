package com.projects.inGameMarketplace

import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.*
import java.sql.ResultSet
import java.sql.Statement

@CrossOrigin
@RestController
@RequestMapping("/interaction")
class PlayerService {
    val playerRepository = PlayerRepository()
    val databaseConnector = DatabaseConnector()
    var player: Player? = getPlayer()

    private fun getPlayer(): Player? {
        return getPlayerIfAvailable()
    }

    @GetMapping("/getPlayer")
    fun fetchPlayerData(): Player? {
        return this.player
    }

    fun gameIsRunning(): Boolean {
        return this.player != null
    }

    fun createPlayer(playerName: String) {
        this.player = Player(name = playerName)
        createPlayerEntry(this.player!!)
    }

    fun deletePlayer() {
        deletePlayerEntry()
        this.player = null
    }

    @PostMapping("/updatePlayer")
    fun updatePlayerData(@RequestBody updatedPlayer: Player) {
        this.player = updatedPlayer
    }


    private fun getPlayerIfAvailable(): Player? {
        val connection = databaseConnector.connectToDatabase()
        val statement: Statement = connection.createStatement()
        val query = "SELECT * FROM player;"

        // Execute the query
        val resultSet: ResultSet = statement.executeQuery(query)
        val hasEntry = resultSet.next()

        if (!hasEntry) {
            resultSet.close()
            connection.close()
            return null
        }

        val inventoryItems = resultSet.getString("inventory_items")
        val inventoryList: List<Pair<Item, Int>> = Json.decodeFromString(inventoryItems)

        val player = Player(
            resultSet.getString("player_name"),
            resultSet.getDouble("money"),
            resultSet.getInt("inventory_space"),
            inventoryList,
            resultSet.getInt("day")
        )

        resultSet.close()
        connection.close()
        return player
    }

    private fun createPlayerEntry(player: Player) {
        val connection = databaseConnector.connectToDatabase()
        val query = """
            INSERT INTO player(player_name, money, inventory_space, inventory_items, day) VALUES(?, ?, ?, ?, ?)
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.setString(1, player.name)
        preparedStatement.setDouble(2, player.money)
        preparedStatement.setInt(3, player.inventorySpace)
        preparedStatement.setObject(4, player.inventoryItems)
        preparedStatement.setInt(5, player.day)

        preparedStatement.executeUpdate()
        connection.close()
    }

    private fun deletePlayerEntry() {
        val connection = databaseConnector.connectToDatabase()
        val query = """
            DELETE FROM player;
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.executeUpdate()
        connection.close()
    }
}