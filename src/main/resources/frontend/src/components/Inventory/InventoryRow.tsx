import "./Inventory-Row.css"
import Item from "./Item";
import {ItemObject} from "../../Interfaces/ItemObject";

interface InventoryRowProps{
    itemList: ItemObject[]
    entity: string
    handleFocusItem: (focusItem: ItemObject) => void
}


const InventoryRow = (props: InventoryRowProps) => {
    if (props.entity == "Merchant") {
        const itemsWithAttributes = props.itemList.map((item, index) => (
            <Item item={item} key={index} handleFocusItem={props.handleFocusItem} amount={item.second}></Item>
        ));

        return (
            <div className="inventory-row">
                {itemsWithAttributes}
            </div>
        )



    } else if (props.itemList != null) {
        const itemsWithAttributes = props.itemList.map((item, index) => (
            <Item item={item} key={index} handleFocusItem={props.handleFocusItem} amount={item.second}></Item>
        ));

        const emptyItemCount = 10 - itemsWithAttributes.length
        const emptyItems = Array()
        for (let i = 0; i < emptyItemCount; i++) {
            emptyItems.push(<Item handleFocusItem={props.handleFocusItem}/>)
        }

        return (
            <div className="inventory-row">
                {itemsWithAttributes}
                {emptyItems}
            </div>
        )
    }

    return (
        <div className="inventory-row">
            <Item handleFocusItem={props.handleFocusItem}></Item>
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

export default InventoryRow;
