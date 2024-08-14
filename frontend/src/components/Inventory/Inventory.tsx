import {Component, useEffect, useState} from "react";
import "./InventoryStyle.css"
import InventoryRow from "./InventoryRow";
import PlayerMenu from "./PlayerMenu";
import MerchantMenu from "./MerchantMenu";


interface InventoryProps {
    entity: string
}

class Inventory extends Component<InventoryProps> {
    render() {
        const {entity} = this.props;
        return (
            <div className="inventory-container">
                {entity === "Player" && <PlayerMenu/>}
                {entity === "Merchant" && <MerchantMenu/>}
                <div className="grid-container">
                    <InventoryRow/>
                    <InventoryRow/>
                    <InventoryRow/>
                </div>
            </div>
        );
    }
}

export default Inventory;
