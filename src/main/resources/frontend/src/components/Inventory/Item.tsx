import {Component, useEffect, useState} from "react";
import "./Item.css"

class Item extends Component {
    render() {
        return (
            <div className="item-container">
                <img src="/assets/items/mead.png" alt="steak" className="item-image"/>
            </div>
        );
    }
}

export default Item;
