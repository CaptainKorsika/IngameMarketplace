import axios from 'axios';
import React, {useEffect, useState} from "react";
import Inventory from "./components/Inventory/Inventory";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css'
import MenuScreen from "./components/MenuScreen/MenuScreen";

const App: React.FC = () => {
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
    }, []); // Empty dependency array



    return (
        <div className="window-container">
            <Inventory
                entity="Merchant"
            />
            <div className="game-container">
                <Marketplace/>
                <MenuScreen isCurrentlyPlaying={isCurrentlyPlaying}/>
            </div>
            <Inventory
                entity="Player"
            />
        </div>
    );
};

export default App;
