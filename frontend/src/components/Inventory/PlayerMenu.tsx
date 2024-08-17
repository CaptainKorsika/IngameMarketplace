import {Component, useEffect, useState} from "react";
import "./InventoryMenuStyle.css"


class PlayerMenu extends Component {
    render() {
        return (
            <div className="inventory-menu">
                <div className="player-money">
                    <h2>Money: 100$</h2>
                </div>
                <div className="buy-inventory-container">
                    <h3>$1000</h3>
                    <button className="buy-inventory-button">Unlock Inventory Space</button>

                </div>

            </div>
        );
    }
}

export default PlayerMenu;
