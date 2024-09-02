import {Component} from "react";
import "./InventoryStyle.css"
import InventoryRow from "./InventoryRow";
import PlayerMenu from "./Data Sections/PlayerMenu";
import MerchantMenu from "./Data Sections/StatisticsMenu";
import {Simulate} from "react-dom/test-utils";
import mouseEnter = Simulate.mouseEnter;
import StatisticsMenu from "./Data Sections/StatisticsMenu";


interface InventoryProps {
    entity: string,
    money?: number,
    isCurrentlyPlaying: boolean
    inventorySpace: number
    day?: number
    unlockInventory(): void
}

class Inventory extends Component<InventoryProps> {
    render() {
        const {entity, money, isCurrentlyPlaying, inventorySpace, day, unlockInventory} = this.props

        if (!isCurrentlyPlaying) {
            return <div className="inventory-container"></div>
        }

        return (
            <div className="inventory-container">
                {entity === "Player" && <PlayerMenu money={money} inventorySpace={inventorySpace} unlockInventory={unlockInventory}/>}
                {entity === "Merchant" && <StatisticsMenu day={day}/>}
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
