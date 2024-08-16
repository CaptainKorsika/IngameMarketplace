import {Component, useEffect, useState} from "react";
import "./InventoryMenuStyle.css"


class PlayerMenu extends Component {
    render() {
        return (
            <div className="inventory-menu">
                <div className="player-money">
                    <h2>Money: 5$</h2>
                </div>
                <button>Buy Inventory Space</button>
            </div>
        );
    }
}

export default PlayerMenu;
