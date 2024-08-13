import axios from 'axios';
import React, {useEffect, useState} from "react";
import Inventory from "./components/Inventory";
import Menu from "./components/Menu";
import Marketplace from "./components/Marketplace";
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
            <Inventory></Inventory>
            <div className="game-container">
                <Marketplace></Marketplace>
                <Menu></Menu>
            </div>

            <Inventory></Inventory>
        </div>
    );
};

export default App;
