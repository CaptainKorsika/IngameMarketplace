import axios from 'axios';
import React, {useEffect, useState} from "react";
import Inventory from "./components/Inventory/Inventory";
import Menu from "./components/Menu/Menu";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css'

const App: React.FC = () => {
    const [message, setMessage] = useState('');

    useEffect(() => {
        axios.get('/api/hello')
            .then(response => {
                setMessage(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the data!', error);
            });
    }, []);

    return (
        <div className="window-container">
            <Inventory
                entity="Merchant"
            />
            <div className="game-container">
                <Marketplace/>
                <Menu/>
            </div>
            <Inventory
                entity="Player"
            />
        </div>
    );
};

export default App;
