import {Component, useEffect, useState} from "react";
import "./InventoryStyle.css"
import Item from "./Item";
import InventoryRow from "./InventoryRow";


class FocusItem extends Component {
    render() {
        return (
            <div className="inventory-container">
               <div className="inventory-menu">
                   <div className="item-info">
                       <div className="name">sword</div>
                       <div className="purchase-price">2</div>
                       <div className="amount">3</div>
                   </div>
                   <div className="blanace"></div>
               </div>
                <div className="grid-container">
                    <InventoryRow></InventoryRow>
                    <InventoryRow></InventoryRow>
                    <InventoryRow></InventoryRow>
                </div>
            </div>
        );
    }
}

export default FocusItem;
