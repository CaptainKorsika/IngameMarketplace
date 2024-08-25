package com.projects.inGameMarketplace

import kotlinx.serialization.Serializable

@Serializable
class Item {
    val name: String = "banana"
    val image: String = ""
    val price: Int = 2
}