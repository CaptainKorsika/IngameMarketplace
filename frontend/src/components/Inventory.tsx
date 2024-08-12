import React, { Component } from 'react';
import "./InventoryStyle.css"


class FocusItem extends Component {
    render() {
        return (
            <div className="container">
               <div>
                   <div className="item-info">
                       <div className="name">sword</div>
                       <div className="purchase-price">2</div>
                       <div className="amount">3</div>
                   </div>
                   <div className="blanace"></div>
               </div>
                <div className="grid-container">
                    <div className="inventory-row" id="row1"></div>
                    <div className="inventory-row" id="row2"></div>
                    <div className="inventory-row" id="row3"></div>
                </div>
            </div>
        );
    }
}

export default FocusItem;
