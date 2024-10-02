import "./Inventory-Row.css"
import Item from "./Item";
import {ItemList} from "../../Interfaces/ItemListType";

interface InventoryRowProps{
    itemList: ItemList
    entity: string
}


const InventoryRow = (props: InventoryRowProps) => {
    if (props.entity == "Merchant") {
        const itemsWithAttributes = props.itemList.map((x, index) => (
            <Item item={x.first} key={index}></Item>
        ));

        return (
            <div className="inventory-row">
                {itemsWithAttributes}
            </div>
        )



    } else if (props.itemList != null) {
        const itemsWithAttributes = props.itemList.map((item, index) => (
            <Item item={item.first} key={index} ></Item>
        ));

        const emptyItemCount = 10 - itemsWithAttributes.length
        const emptyItems = Array()
        for (let i = 0; i < emptyItemCount; i++) {
            emptyItems.push(<Item/>)
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
            <Item></Item>
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
