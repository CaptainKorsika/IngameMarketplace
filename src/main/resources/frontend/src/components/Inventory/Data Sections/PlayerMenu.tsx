import {Component, useEffect, useState} from "react";
import "./InventoryMenu.css"
import axios from "axios";


interface PlayerMenuProps {
    money: number;
    inventorySpace: number
    unlockInventory(): void;
}

class PlayerMenu extends Component<PlayerMenuProps> {
    render() {
        const {money, inventorySpace, unlockInventory} = this.props
        return (
            <div className="inventory-menu">
                <div className="player-money">
                    <h2>Money: ${money}</h2>
                </div>
                {inventorySpace !== 30 && <div
                    className="buy-inventory-container">
                    <h3>${inventorySpace * 100}</h3>
                    <button className="buy-inventory-button" onClick={unlockInventory}>Unlock Inventory Space</button>
                </div>}
            </div>
        );
    }
}

export default PlayerMenu;
