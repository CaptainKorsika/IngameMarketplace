package com.projects.inGameMarketplace

import kotlinx.serialization.json.Json
import org.springframework.web.bind.annotation.*
import java.sql.ResultSet
import java.sql.Statement

@CrossOrigin
@RestController
@RequestMapping("/playerService")
class PlayerService {
    val playerRepository = PlayerRepository()
    val databaseConnector = DatabaseConnector()
    var player: Player? = getPlayers()

    private fun getPlayers(): Player? {
        return getPlayerIfAvailable()
    }

    @GetMapping("/getPlayer")
    fun fetchPlayerData(): Player? {
        return this.player
    }

    @GetMapping("/gameRunning")
    fun playerAlreadyCreated(): Boolean {
        return this.player != null
    }

    @PostMapping("/createPlayer")
    fun createPlayer(@RequestBody playerName: String) {
        this.player = Player(name = playerName)
        createPlayerEntry(this.player!!)
    }

    @PostMapping("/deletePlayer")
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
        val query = "SELECT * FROM PLAYER;"

        // Execute the query
        val resultSet: ResultSet = statement.executeQuery(query)
        val hasEntry = resultSet.next()

        if (!hasEntry) {
            resultSet.close()
            connection.close()
            return null
        }

        val inventoryItems = resultSet.getString("INVENTORY_ITEMS")
        val inventoryList: List<Pair<Item, Int>> = Json.decodeFromString(inventoryItems)

        val player = Player(
            resultSet.getString("PLAYER_NAME"),
            resultSet.getDouble("MONEY"),
            resultSet.getInt("INVENTORY_SPACE"),
            inventoryList,
            resultSet.getInt("DAY")
        )

        resultSet.close()
        connection.close()
        return player
    }

    private fun createPlayerEntry(player: Player) {
        val connection = databaseConnector.connectToDatabase()
        val query = """
            INSERT INTO PLAYER(PLAYER_NAME, MONEY, INVENTORY_SPACE, INVENTORY_ITEMS, DAY) VALUES(?, ?, ?, ?, ?)
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
            DELETE FROM PLAYER;
        """

        val preparedStatement = connection.prepareStatement(query)
        preparedStatement.executeUpdate()
        connection.close()
    }
}