import {Component, useEffect, useState} from "react";
import "./InventoryMenuStyle.css"


class PlayerMenu extends Component {
    render() {
        return (
            <div className="inventory-menu">
                <h2 className="statistics-header">Statistics</h2>
                <div className="statistics">
                    <div className="statistic-items">
                        <h4 className="most-money">Most Money:</h4>
                        <h4 className="most-profit">Most profitable Day:</h4>
                        <h4 className="number-trades">Number of Trades:</h4>
                    </div>
                    <div className="statistic-data">
                        <h4>$1000</h4>
                        <h4>$103</h4>
                        <h4>5</h4>
                    </div>
                </div>


            </div>
        );
    }
}

export default PlayerMenu;
