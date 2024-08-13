import {Component, useEffect, useState} from "react";
import "./Marketplace.css"
import Merchant from "./Merchant";

class Marketplace extends Component {
    render() {
        return (
            <div className="marketplace">
                <Merchant></Merchant>
                <Merchant></Merchant>
                <Merchant></Merchant>
            </div>
        );
    }
}

export default Marketplace;
