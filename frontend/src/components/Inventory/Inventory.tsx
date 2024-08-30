import {Component} from "react";
import "./InventoryStyle.css"
import InventoryRow from "./InventoryRow";
import PlayerMenu from "./Data Sections/PlayerMenu";
import MerchantMenu from "./Data Sections/StatisticsMenu";


interface InventoryProps {
    entity: string,
    isCurrentlyPlaying: boolean
    inventorySpace: number
}

class Inventory extends Component<InventoryProps> {
    render() {
        const {entity, isCurrentlyPlaying, inventorySpace} = this.props

        if (!isCurrentlyPlaying) {
            return <div className="inventory-container"></div>
        }

        return (
            <div className="inventory-container">
                {entity === "Player" && <PlayerMenu/>}
                {entity === "Merchant" && <MerchantMenu/>}
                <div className="grid-container">
                    <InventoryRow/>
                    {entity === "Player" && inventorySpace > 1 && <InventoryRow/>}
                    {entity === "Player" && inventorySpace > 2 && <InventoryRow/>}
                </div>
            </div>
        );
    }
}

export default Inventory;
