class ItemService ̛{
    val existingItems: MutableList<Item>
    val availableList



    fun createAvailableItems(this.existingItems): List<Item> {
        val listBuilder: MutableList<Item> = mutableListof()
        for (i in 1..10) {
            while (true) {
                val randomNumber = Random.nextInt(1, existingItems.size())
                val itemToAdd = existingItems[randomNumber]
                if (!listBuilder.map(item -> item.name).contains(itemToAdd.name)) {
                    listBuilder.add(itemToAdd)
                    break
                }
            }
        }
        return listBuilder
    }

    fun changeDailyPrices(): List<Item> {
        val lowerBoundry: Double = 0.5
        val upperBoundry: Double = 1.5
    }
}