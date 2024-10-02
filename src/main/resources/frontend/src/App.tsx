import { useEffect, useState} from "react";
import Inventory from "./components/Inventory/Inventory";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css';
import Menu from "./components/Menu/Menu";
import axios from "axios";

interface Item {
    name: string;
    image: string;
    averagePrice: number;
    currentPrice: number
}

function App() {
    const [isCurrentlyPlaying, setIsCurrentlyPlaying] = useState(false)
    const [money, setMoney] = useState(1)
    const [inventorySpace, setInventorySpace] = useState(10)
    const [inventoryItems, setInventoryItems] = useState<Item[]>([])
    const [day, setDay] = useState(1)

    const unlockInventory = () => {
        axios.get('http://localhost:8080/interaction/buyInventorySpace')
            .then(response => {
                setInventorySpace(response.data);
            })
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



    console.log(money)

    useEffect(() => {
        getPlayerData()
        gameIsRunning()

    }, []);

    return <div className="window-container">
        <Inventory
            entity="Merchant"
            isCurrentlyPlaying={isCurrentlyPlaying}
            inventoryItems={inventoryItems}
            inventorySpace={inventorySpace}
            day={day}
        />
        <div className="game-container">
            <Marketplace/>
            <Menu isCurrentlyPlaying={isCurrentlyPlaying}/>
        </div>
        <Inventory entity="Player" isCurrentlyPlaying={isCurrentlyPlaying}
                   money={money}
                   inventorySpace={inventorySpace}
                   inventoryItems={inventoryItems}
                   unlockInventory={unlockInventory}/>
    </div>
}

export default App;
