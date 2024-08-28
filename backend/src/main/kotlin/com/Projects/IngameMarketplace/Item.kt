package com.projects.inGameMarketplace

import kotlinx.serialization.Serializable

@Serializable
class Item(val name: String, val image: String, val averagePrice: Double) {
    var currentPrice: Double = averagePrice
}