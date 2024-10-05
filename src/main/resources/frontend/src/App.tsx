import { useEffect, useState} from "react";
import Inventory from "./components/Inventory/Inventory";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css';
import Menu from "./components/Menu/Menu";
import axios from "axios";
import {ItemList} from "./Interfaces/ItemListType";
import {ItemObject} from "./Interfaces/ItemObject";

function App() {
    const [isCurrentlyPlaying, setIsCurrentlyPlaying] = useState(false)
    const [money, setMoney] = useState(1)
    const [inventorySpace, setInventorySpace] = useState(10)
    const [inventoryItems, setInventoryItems] = useState<ItemList>([])
    const [day, setDay] = useState(1)
    const [merchantsItems, setMerchantsItems] = useState<ItemList[]>([])
    const [activeMerchant, setActiveMerchant] = useState(1)
    const [focusItem, setFocusItem] = useState<ItemObject>()

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
            const firstMerchant: ItemList = response.data[0].inventory.currentItems
            const secondMerchant: ItemList = response.data[1].inventory.currentItems
            const thirdMerchant: ItemList = response.data[2].inventory.currentItems

            const merchantList = [firstMerchant, secondMerchant, thirdMerchant]
            setMerchantsItems(merchantList)
        } catch (error) {
        }

        getPlayerData()

    }

    const handleFocusItem = (clickedItem) => {
        setFocusItem(clickedItem)
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
            const firstMerchant: ItemList = response.data[0].currentItems
            const secondMerchant: ItemList = response.data[1].currentItems
            const thirdMerchant: ItemList = response.data[2].currentItems

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
            handleNextDay={handleNextDay}
            handleFocusItem={handleFocusItem}
        />
        <div className="game-container">
            <Marketplace handleActiveMerchant={handleActiveMerchant}/>
            <Menu isCurrentlyPlaying={isCurrentlyPlaying} focusItem={focusItem}/>
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