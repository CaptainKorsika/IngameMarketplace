import { Component } from "react";
import Inventory from "./components/Inventory/Inventory";
import Marketplace from "./components/Marketplace/Marketplace";
import './App.css';
import Menu from "./components/Menu/Menu";
import axios from "axios";


interface AppState {
    isCurrentlyPlaying: boolean;
}


class App extends Component<{}, AppState> {
    constructor(props) {
        super(props);
        this.state = {
            isCurrentlyPlaying: undefined
        };
    }

    componentDidMount() {
        this.fetchData();
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

    render() {
        const { isCurrentlyPlaying } = this.state;

        return (
            <div className="window-container">
                <Inventory
                    entity="Merchant"
                    isCurrentlyPlaying={isCurrentlyPlaying}
                />
                <div className="game-container">
                    <Marketplace />
                    <Menu isCurrentlyPlaying={isCurrentlyPlaying} />
                </div>
                <Inventory entity="Player" isCurrentlyPlaying={isCurrentlyPlaying} />
            </div>
        );
    }
}

export default App;
