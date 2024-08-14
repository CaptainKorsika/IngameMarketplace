import {Component, useEffect, useState} from "react";
import "./InventoryMenuStyle.css"


class PlayerMenu extends Component {
    render() {
        return (
            <div className="inventory-menu">
                <div className="name">player</div>
                <div className="purchase-price">5</div>
                <div className="amount">3</div>
            </div>
        );
    }
}

export default PlayerMenu;
