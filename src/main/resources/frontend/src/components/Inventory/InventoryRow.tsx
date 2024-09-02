import {Component, useEffect, useState} from "react";
import "./Inventory-Row.css"
import Item from "./Item";

class InventoryRow extends Component {
    render() {
        return (
            <div className="inventory-row">
                <Item></Item>
                <Item></Item>
                <Item></Item>
                <Item></Item>
                <Item></Item>
                <Item></Item>
                <Item></Item>
                <Item></Item>
                <Item></Item>
                <Item></Item>
            </div>
        );
    }
}

export default InventoryRow;
