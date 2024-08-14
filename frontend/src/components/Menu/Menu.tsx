import {Component, useEffect, useState} from "react";
import "./Menu.css"

class Menu extends Component {
    render() {
        return (
            <div className="menu">
                <div className="item-info">
                    <div>
                        Item Image
                        <img src="" alt=""/>
                    </div>
                    <div>Price</div>
                    <div>Average Price</div>
                    <div>Buying Price</div>
                    <div>Amount</div>
                </div>
                <div className="interaction-container">
                    <button>Price Information</button>
                    <input type="number"/>
                    <button>Buy Product</button>
                    <button>Sell Product</button>
                </div>
            </div>
        );
    }
}

export default Menu;
