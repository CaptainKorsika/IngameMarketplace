
import {useEffect, useState, FC} from "react";
import Inventory from "./components/Inventory/Inventory";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css'
import Menu from "./components/Menu/Menu";
import axios from "axios";

const App: FC = () => {
    const [isCurrentlyPlaying, setIsCurrentlyPlaying] = useState()

    useEffect(() => {
        // Fetch the data from the backend when the component is mounted
        const fetchData = () => {
            const response = axios.get('http://localhost:8080/api/game-status');

            response.then(function(response) {
                setIsCurrentlyPlaying(response.data);
            })
        };
        fetchData();
    }, []);


    return (
        <div className="window-container">
            <Inventory
                entity="Merchant" isCurrentlyPlaying={isCurrentlyPlaying}
            />
            <div className="game-container">
                <Marketplace/>
                <Menu isCurrentlyPlaying={isCurrentlyPlaying}/>
            </div>
            <Inventory entity="Player" isCurrentlyPlaying={isCurrentlyPlaying}/>
        </div>
    );
};

export default App;
