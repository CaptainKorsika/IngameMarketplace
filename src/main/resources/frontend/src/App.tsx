import { useEffect, useState} from "react";
import Inventory from "./components/Inventory/Inventory";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css';
import Menu from "./components/Menu/Menu";
import axios from "axios";
import {ItemObject} from "./Interfaces/ItemObject";
import {FocusItemObject} from "./Interfaces/FocusItemObject";

function App() {
    const [isCurrentlyPlaying, setIsCurrentlyPlaying] = useState(false)
    const [money, setMoney] = useState(1)
    const [inventorySpace, setInventorySpace] = useState(10)
    const [inventoryItems, setInventoryItems] = useState<ItemObject[]>([])
    const [day, setDay] = useState(1)
    const [merchantsItems, setMerchantsItems] = useState<ItemObject[][]>([])
    const [activeMerchant, setActiveMerchant] = useState(0)
    const [focusItem, setFocusItem] = useState<FocusItemObject>(null)
    const [amount, setAmount] = useState(0)
    const [totalPrice, setTotalPrice] = useState("0.00")
    const [enoughMoney, setEnoughMoney] = useState(true)
    const [showHighScoreModal, setShowHighScoreModal] = useState(false)

    const unlockInventory = () => {
        axios.get('http://localhost:8080/interaction/buyInventorySpace')
            .then(response => {
                setInventorySpace(response.data);
            })
    }

    const handleHighScoreModal = (open: boolean) => {
        setShowHighScoreModal(open)
    }

    const handleActiveMerchant = (merchantId: number) => {
        setActiveMerchant(merchantId)
    }

    const handleEnoughMoneyCheck = (price: number) => {
        const availableMoney = Number(money.toString().replace(",", "."))
        setEnoughMoney(availableMoney >= price)
    }

    const handleAmountChange = (itemAmount: number) => {

        if (itemAmount == 0) {
            setTotalPrice("0.00")
            return
        }

        const priceString = focusItem.currentPrice.replace(",", ".")
        const price = Number(priceString)
        let totalPrice = (itemAmount * price).toString() // + ".00"

        if (totalPrice.match(/\./) != null) {
            totalPrice += "00"
        } else {
            totalPrice += ".00"
        }

        const regex = /\d+\.\d{2}/
        const roundedTotalPrice = totalPrice.match(regex)[0]

        handleEnoughMoneyCheck(Number(totalPrice))
        setTotalPrice(roundedTotalPrice.replace(".", ","))
        setAmount(itemAmount)
    }

    // @ts-ignore
    const handleNextDay = async () => {
        try {
            const response = await axios.get('http://localhost:8080/interaction/nextDay');
            const firstMerchant: ItemObject[] = response.data[0].currentItems
            const secondMerchant: ItemObject[] = response.data[1].currentItems
            const thirdMerchant: ItemObject[] = response.data[2].currentItems
            const merchantList = [firstMerchant, secondMerchant, thirdMerchant]
            setMerchantsItems(merchantList)
        } catch (error) {
        }

        await getPlayerData()

    }

    const handleFocusItem = (clickedItem: ItemObject) => {
        if (clickedItem == null) {
            setFocusItem(null)
            return
        }

        const itemName = clickedItem.item.name
        const itemImage = clickedItem.item.image
        let averageMerchantPrice = clickedItem.item.averageMerchantPrice
        const merchantItems = merchantsItems[activeMerchant]
        const filteredMatchingItems = merchantItems.filter((item): boolean => item.item.name == itemName)

        let matchingItem: ItemObject = null
        let merchantAmount: number

        if (filteredMatchingItems.length == 0) {
            merchantAmount = 0
        } else {
            matchingItem = filteredMatchingItems[0]
            merchantAmount = matchingItem.amount
        }

        let playerAmount: number
        const playerItems = inventoryItems.filter((item): boolean => item.item.name == itemName)

        if (playerItems.length == 0) {
            playerAmount = 0
        } else {
            playerAmount = playerItems[0].amount
        }

        let price: string

        if (merchantAmount != 0 || matchingItem != null) {
            price = matchingItem.item.currentPrice
        } else {
            price = averageMerchantPrice
        }

        let averageBuyingPrice: string

        if (clickedItem.averageBuyingPrice == null && playerAmount != 0) {
            const playerItemList = inventoryItems.filter((item): boolean => item.item.name == itemName)
            const playerItem = playerItemList[0]
            averageBuyingPrice = playerItem.averageBuyingPrice
        } else {
            averageBuyingPrice = clickedItem.averageBuyingPrice
        }

        const focusItem: FocusItemObject = {
            name: itemName,
            image: itemImage,
            merchantAmount: merchantAmount,
            playerAmount: playerAmount,
            averageMerchantPrice: averageMerchantPrice,
            currentPrice: price,
            avgBuyingPrice: averageBuyingPrice
        }

        setFocusItem(focusItem)
    }

    // @ts-ignore
    const handleItemTrade = async (isBuying: boolean, amount: number) => {
        try {
            const itemDTO = {
                name: focusItem.name,
                image: focusItem.image,
                averageMerchantPrice: focusItem.averageMerchantPrice,
                currentPrice: focusItem.currentPrice
            }

            const requestData = {
                newItem: {
                    first: itemDTO,
                    second: amount
                },
                merchantID: activeMerchant
            };

            if (isBuying) {
                await axios.post('http://localhost:8080/interaction/buyItem', requestData)

            } else {
                await axios.post('http://localhost:8080/interaction/sellItem', requestData)
            }
        } catch (error) {
            console.error('There was an error trading with server!', error);
        }
    }

    // @ts-ignore
    const gameIsRunning = async () => {
        try {
            const response = await axios.get('http://localhost:8080/interaction/gameRunning');
            setIsCurrentlyPlaying(response.data)
        } catch (error) {
        }
    }

    // @ts-ignore
    const getPlayerData = async () => {
        try {
            const response = await axios.get('http://localhost:8080/interaction/getData');

            // Update state in a single setState call
            setMoney(response.data.money)
            setInventorySpace(response.data.inventorySpace)
            setInventoryItems(response.data.inventoryItems)
            setDay(response.data.day)
        } catch (error) {
            console.error('There was an error fetching the player data!', error);
        }
    };

    // @ts-ignore
    const getMerchantItems = async () => {
        try {
            const response = await axios.get('http://localhost:8080/interaction/merchantInventory');
            const firstMerchant: ItemObject[] = response.data[0].currentItems
            const secondMerchant: ItemObject[] = response.data[1].currentItems
            const thirdMerchant: ItemObject[] = response.data[2].currentItems

            const merchantList = [firstMerchant, secondMerchant, thirdMerchant]
            setMerchantsItems(merchantList)
        } catch (error) {
            console.error('There was an error fetching merchant data!', error)
        }
    }

    gameIsRunning()

    useEffect(() => {
        getPlayerData()
        getMerchantItems()
    }, []);

    useEffect(() => {
        if (focusItem != null) {
            const merchantItems = merchantsItems[activeMerchant]
            const filteredMatchingItems = merchantItems.filter((item): boolean => item.item.name == focusItem.name)

            let newFocusItem: ItemObject;

            if (filteredMatchingItems[0] == null) {
                // item clicked in player inventory
                newFocusItem = {
                    item: {
                        name: focusItem.name,
                        image: focusItem.image,
                        averageMerchantPrice: focusItem.averageMerchantPrice,
                        currentPrice: focusItem.currentPrice
                    },
                    amount: 0, // merchant amount
                    averageBuyingPrice: focusItem.avgBuyingPrice
                }
            } else {
                newFocusItem = filteredMatchingItems[0]
            }

            handleFocusItem(newFocusItem)
        }
    }, [activeMerchant])

    useEffect(() => {
        getPlayerData()
        getMerchantItems()
    }, [handleItemTrade]);

    useEffect(() => {
       handleAmountChange(amount)
    }, [activeMerchant, focusItem]);

    return <div className="window-container">
        <Inventory
            entity="Merchant"
            isCurrentlyPlaying={isCurrentlyPlaying}
            merchantItems={merchantsItems}
            inventorySpace={inventorySpace}
            day={day}
            activeMerchant={activeMerchant}
            handleFocusItem={handleFocusItem}
            showHighScoreModal={showHighScoreModal}
            handleShowHighScoreModal={handleHighScoreModal}
        />
        <div className="game-container">
            <Marketplace handleActiveMerchant={handleActiveMerchant}/>
            <Menu isCurrentlyPlaying={isCurrentlyPlaying}
                  focusItem={focusItem}
                  handleNextDay={handleNextDay}
                  handleItemTrade={handleItemTrade}
                  money={money}
                  handleAmountChange={handleAmountChange}
                  amount={amount}
                  totalPrice={totalPrice}
                  enoughMoney={enoughMoney}
                  showHighScoreModal={showHighScoreModal}
                  handleShowHighScoreModal={handleHighScoreModal}
            />
        </div>
        <Inventory entity="Player" isCurrentlyPlaying={isCurrentlyPlaying}
                   money={money}
                   inventorySpace={inventorySpace}
                   inventoryItems={inventoryItems}
                   unlockInventory={unlockInventory}
                   handleFocusItem={handleFocusItem}/>
    </div>
}

export default App;
