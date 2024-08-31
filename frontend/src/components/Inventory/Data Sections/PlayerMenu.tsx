import {Component, useEffect, useState} from "react";
import "./InventoryMenu.css"
import axios from "axios";


interface PlayerMenuProps {
    inventorySpace: number
    unlockInventory(): void;
}

class PlayerMenu extends Component<PlayerMenuProps> {
    render() {
        const {inventorySpace, unlockInventory} = this.props
        return (
            <div className="inventory-menu">
                <div className="player-money">
                    <h2>Money: 100$</h2>
                </div>
                {inventorySpace !== 3 && <div
                    className="buy-inventory-container">
                    <h3>${inventorySpace * 1000}</h3>
                    <button className="buy-inventory-button" onClick={unlockInventory}>Unlock Inventory Space</button>
                </div>}
            </div>
        );
    }
}

export default PlayerMenu;
