//package com.projects.inGameMarketplace.playerService
//
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//
//class PlayerServiceUnitTest {
//    private lateinit var playerService: PlayerService
//
//    @BeforeEach
//    fun setup() {
//        // TODO: find a way to mock repo function
//        playerService = PlayerService()
//    }
//
//    @Test
//    fun `should return player object`() {
//        // given
//        playerService.player = Player("Peter")
//        val expectedPlayer = Player("Peter", 10, 1, 50000)
//        // when
//        val actualPlayer = playerService.fetchPlayerData()
//
//        // then
//        assertNotNull(actualPlayer)
//        assertEquals(expectedPlayer, actualPlayer)
//    }
//
//    @Test
//    fun `should return null`() {
//        // given
//        playerService.player = null
//        val expectedPlayer = null
//        // when
//        val actualPlayer = playerService.fetchPlayerData()
//
//        // then
//        assertNull(actualPlayer)
//        assertEquals(expectedPlayer, actualPlayer)
//    }
//
//    @Test
//    fun `should create player object`() {
//        // given
//        // when
//        playerService.createPlayer("Basti")
//        val actualPlayer = playerService.player
//        val expectedPlayer = Player("Basti", 10, 1, 50000)
//        // then
//        assertNotNull(actualPlayer)
//        assertEquals(expectedPlayer, actualPlayer)
//    }
//
//    @Test
//    fun `should delete player object`() {
//        // given
//        playerService.player = Player("Basti", 10, 1, 50000)
//        // when
//        playerService.deletePlayer()
//        val actualPlayer = playerService.player
//        val expectedPlayer = null
//        // then
//        assertNull(actualPlayer)
//        assertEquals(expectedPlayer, actualPlayer)
//    }
//
//    @Test
//    fun `should update player balance`() {
//        // given
//        playerService.player = Player("Basti", 10, 1, 100)
//        // when
//        playerService.updatePlayerBalance(100)
//        val actualPlayer = playerService.player
//        val expectedPlayer = Player("Basti", 10, 1, 200)
//        // then
//        assertEquals(expectedPlayer, actualPlayer)
//    }
//
//    @Test
//    fun `should update player data`() {
//        // TODO: Find a way to mock repo function
//
//        // given
//        playerService.player = Player("Basti", 10, 1, 100)
//        // when
//        playerService.updatePlayerData()
//        // then
//        assert // function to have been called with entity
//    }
//
//    @Test
//    fun `should update day`() {
//        // given
//        playerService.player = Player("Basti", 10, 1, 100)
//        // when
//        playerService.nextDay()
//        val expectedDay = 2
//        // then
//        assertEquals(expectedDay, playerService.player!!.day)
//    }
//
//    @Test
//    fun `should update inventory space`() {
//        // given
//        playerService.player = Player("Basti", 10, 1, 100)
//        // when
//        playerService.addInventorySpace()
//        val expectedDay = 20
//        // then
//        assertEquals(expectedDay, playerService.player!!.inventorySpace)
//    }
//
//
//
//
//}