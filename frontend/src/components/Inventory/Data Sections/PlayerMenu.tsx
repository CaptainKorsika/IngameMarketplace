import {Component, useEffect, useState} from "react";
import "./InventoryMenu.css"
import axios from "axios";


class PlayerMenu extends Component {

    unlockInventory = () => {
        axios.get('http://localhost:8080/inventoryService/buyInventorySpace')
            .then(response => {
                this.setState({ inventorySpace: response.data.inventorySpace });
            })
    }

    render() {
        return (
            <div className="inventory-menu">
                <div className="player-money">
                    <h2>Money: 100$</h2>
                </div>
                <div className="buy-inventory-container">
                    <h3>$1000</h3>
                    <button className="buy-inventory-button" onClick={this.unlockInventory}>Unlock Inventory Space</button>
                </div>

            </div>
        );
    }
}

export default PlayerMenu;
