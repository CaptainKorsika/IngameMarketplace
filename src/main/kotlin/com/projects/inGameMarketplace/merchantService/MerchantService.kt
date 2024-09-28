package com.projects.inGameMarketplace.merchantService

class MerchantService {
    val firstMerchant: Merchant = Merchant()
    val secondMerchant: Merchant = Merchant()
    val thirdMerchant: Merchant = Merchant()

    private val merchantList = listOf(firstMerchant, secondMerchant, thirdMerchant)


    fun createNewDailyInventory() {
        merchantList.forEach { it.getNewItems() }
    }



}