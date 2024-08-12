import axios from 'axios';
import React, {useEffect, useState} from "react";
import Inventory from "./components/Inventory";

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
        <div>
            <div>test</div>
            <Inventory></Inventory>
        </div>
    );
};

export default App;
