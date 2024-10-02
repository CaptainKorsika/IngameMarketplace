import "./InventoryStyle.css"
import InventoryRow from "./InventoryRow";
import PlayerMenu from "./Data Sections/PlayerMenu";
import StatisticsMenu from "./Data Sections/StatisticsMenu";


interface InventoryProps {
    entity: string,
    money?: number,
    isCurrentlyPlaying: boolean,
    inventoryItems: Item[],
    inventorySpace: number,
    day?: number,
    unlockInventory?(): void
}

type Item = {
    name: string;
    image: string;
    averagePrice: number;
    currentPrice: number
}

const Inventory = (props: InventoryProps) => {
    let firstRowList: Item[] = []
    let secondRowList: Item[] = []
    let thirdRowList: Item[] = []

    // TODO: Fix error when uncommented

    // if (props.inventoryItems.length <= 10) {
    //     firstRowList = props.inventoryItems
    // } else if (props.inventoryItems.length <= 20) {
    //     firstRowList = props.inventoryItems.slice(0, 10)
    //     secondRowList = props.inventoryItems.slice(10)
    // } else {
    //     firstRowList = props.inventoryItems.slice(0, 10)
    //     secondRowList = props.inventoryItems.slice(10, 20)
    //     thirdRowList = props.inventoryItems.slice(20)
    // }

    console.log(props.isCurrentlyPlaying)

    if (!props.isCurrentlyPlaying) {
        return <div className="inventory-container"></div>
    }

    return (
        <div className="inventory-container">
            {props.entity === "Player" && <PlayerMenu money={props.money} inventorySpace={props.inventorySpace} unlockInventory={props.unlockInventory}/>}
            {props.entity === "Merchant" && <StatisticsMenu day={props.day}/>}
            <div className="grid-container">
                {props.entity === "Merchant" && <InventoryRow/>}
                {props.entity === "Player" && <InventoryRow itemList={firstRowList}/>}
                {props.entity === "Player" && props.inventorySpace > 10 && <InventoryRow itemList={secondRowList}/>}
                {props.entity === "Player" && props.inventorySpace > 20 && <InventoryRow itemList={thirdRowList}/>}
            </div>
        </div>
    );
}

export default Inventory;
