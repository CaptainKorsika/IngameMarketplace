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
    const [activeMerchant, setActiveMerchant] = useState(1)
    const [focusItem, setFocusItem] = useState<FocusItemObject>(null)

    const unlockInventory = () => {
        axios.get('http://localhost:8080/interaction/buyInventorySpace')
            .then(response => {
                setInventorySpace(response.data);
            })
    }

    const handleActiveMerchant = (merchantId) => {
        setActiveMerchant(merchantId)
    }

    // @ts-ignore
    const handleNextDay = async () => {
        try {
            const response = await axios.get('http://localhost:8080/interaction/nextDay');
            const firstMerchant: ItemObject[] = response.data[0].inventory.currentItems
            const secondMerchant: ItemObject[] = response.data[1].inventory.currentItems
            const thirdMerchant: ItemObject[] = response.data[2].inventory.currentItems

            const merchantList = [firstMerchant, secondMerchant, thirdMerchant]
            setMerchantsItems(merchantList)
        } catch (error) {
        }

        getPlayerData()

    }

    const handleFocusItem = (clickedItem: ItemObject) => {
        const itemName = clickedItem.first.name
        const itemImage = clickedItem.first.image

        const merchantItems = merchantsItems[activeMerchant]
        const filteredItems = merchantItems.filter((item): boolean => item.first.name == itemName)

        let merchantAmount: number
        if (filteredItems.length == 0) {
            merchantAmount = 0
        } else {
            merchantAmount = filteredItems[0].second
        }


        let playerAmount: number
        const playerItem = inventoryItems.filter((item): boolean => item.first.name == itemName)
        if (playerItem.length == 0) {
            playerAmount = 0
        } else {
            playerAmount = playerItem[0].second
        }

        const focusItem: FocusItemObject = {
            name: itemName,
            image: itemImage,
            merchantAmount: merchantAmount,
            playerAmount: playerAmount,
            price: filteredItems[0].first.price,
            avgBuyingPrice: "10"
        }



        setFocusItem(focusItem)
    }

    // @ts-ignore
    const handleItemTrade = async (isBuying: boolean, amount: number) => {
        try {
            const itemDTO = {
                name: focusItem.name,
                image: focusItem.image,
                price: focusItem.price
            }

            const requestData = {
                newItem: {
                    first: itemDTO,
                    second: amount
                },
                merchantID: activeMerchant
            };

            console.log(requestData)

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

    useEffect(() => {
        getPlayerData()
        gameIsRunning()
        getMerchantItems()
    }, []);

    return <div className="window-container">
        <Inventory
            entity="Merchant"
            isCurrentlyPlaying={isCurrentlyPlaying}
            merchantItems={merchantsItems}
            inventorySpace={inventorySpace}
            day={day}
            activeMerchant={activeMerchant}
            handleFocusItem={handleFocusItem}
        />
        <div className="game-container">
            <Marketplace handleActiveMerchant={handleActiveMerchant}/>
            <Menu isCurrentlyPlaying={isCurrentlyPlaying} focusItem={focusItem} handleNextDay={handleNextDay} handleItemTrade={handleItemTrade}/>
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
