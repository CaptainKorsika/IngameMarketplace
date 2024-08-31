import { Component } from "react";
import Inventory from "./components/Inventory/Inventory";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css';
import Menu from "./components/Menu/Menu";
import axios from "axios";


interface AppState {
    isCurrentlyPlaying: boolean;
    inventorySpace: number;
}


class App extends Component<{}, AppState> {
    constructor(props) {
        super(props);
        this.state = {
            isCurrentlyPlaying: undefined,
            inventorySpace: 1
        };
    }

    componentDidMount() {
        this.fetchData();
        this.getPlayerData()
    }

    fetchData = () => {
        axios.get('http://localhost:8080/playerService/gameRunning')
            .then(response => {
                this.setState({ isCurrentlyPlaying: response.data });
            })
            .catch(error => {
                console.error('There was an error fetching the game status!', error);
            });
    };


    getPlayerData = () => {
        axios.get('http://localhost:8080/playerService/getPlayer')
            .then(response => {
                this.setState({ inventorySpace: response.data.inventorySpace });
            })
            .catch(error => {
                console.error('There was an error fetching the game status!', error);
            });
    }

    unlockInventory = () => {
        console.log(this.state.inventorySpace);
        axios.get('http://localhost:8080/inventoryService/buyInventorySpace')
            .then(response => {
                this.setState({ inventorySpace: response.data });
            })
    }



    render() {
        const { isCurrentlyPlaying, inventorySpace } = this.state;

        return (
            <div className="window-container">
                <Inventory
                    entity="Merchant"
                    isCurrentlyPlaying={isCurrentlyPlaying}
                    inventorySpace={inventorySpace}
                    unlockInventory={this.unlockInventory}
                />
                <div className="game-container">
                    <Marketplace />
                    <Menu isCurrentlyPlaying={isCurrentlyPlaying} />
                </div>
                <Inventory entity="Player" isCurrentlyPlaying={isCurrentlyPlaying} inventorySpace={inventorySpace} unlockInventory={this.unlockInventory} />
            </div>
        );
    }
}

export default App;
