import "./InventoryStyle.css"
import InventoryRow from "./InventoryRow";
import PlayerMenu from "./Data Sections/PlayerMenu";
import StatisticsMenu from "./Data Sections/StatisticsMenu";


interface InventoryProps {
    entity: string,
    money?: number,
    isCurrentlyPlaying: boolean,
    inventoryItems?: ItemList,
    merchantItems?: ItemList[]
    inventorySpace: number,
    day?: number,
    activeMerchant?: number
    unlockInventory?(): void
}

type ItemList = {
    first: {
        name: string;
        image: string;
        averagePrice: number;
        currentPrice: number
    },
    second: number
}[]

const Inventory = (props: InventoryProps) => {
    let firstRowList: ItemList = []
    let secondRowList: ItemList = []
    let thirdRowList: ItemList = []

    // TODO: Fix error when uncommented

    if (props.inventoryItems != null) {
        if (props.inventoryItems.length <= 10) {
            firstRowList = props.inventoryItems
        } else if (props.inventoryItems.length <= 20) {
            firstRowList = props.inventoryItems.slice(0, 10)
            secondRowList = props.inventoryItems.slice(10)
        } else {
            firstRowList = props.inventoryItems.slice(0, 10)
            secondRowList = props.inventoryItems.slice(10, 20)
            thirdRowList = props.inventoryItems.slice(20)}
    }

    let activeMerchantItems: ItemList = []

    if (props.entity == "Merchant") {
        activeMerchantItems = props.merchantItems[props.activeMerchant - 1]
    }

    if (!props.isCurrentlyPlaying) {
        return <div className="inventory-container"></div>
    }

    return (
        <div className="inventory-container">
            {props.entity === "Player" && <PlayerMenu money={props.money} inventorySpace={props.inventorySpace} unlockInventory={props.unlockInventory}/>}
            {props.entity === "Merchant" && <StatisticsMenu day={props.day}/>}
            <div className="grid-container">
                {props.entity === "Merchant" && <InventoryRow itemList={activeMerchantItems} entity={props.entity}/>}
                {props.entity === "Player" && <InventoryRow itemList={firstRowList} entity={props.entity}/>}
                {props.entity === "Player" && props.inventorySpace > 10 && <InventoryRow itemList={secondRowList} entity={props.entity}/>}
                {props.entity === "Player" && props.inventorySpace > 20 && <InventoryRow itemList={thirdRowList} entity={props.entity}/>}
            </div>
        </div>
    );
}

export default Inventory;
