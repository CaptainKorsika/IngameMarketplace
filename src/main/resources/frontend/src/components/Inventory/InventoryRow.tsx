import "./Inventory-Row.css"
import Item from "./Item";

interface InventoryRowProps{
    itemList?: {
        name: string;
        image: string;
        averagePrice: number;
        currentPrice: number
    }[]
}

const InventoryRow = (props: InventoryRowProps) => {
    if (props.itemList != null) {
        const itemsWithAttributes = props.itemList.map((item, index) => (
            <Item item={item} key={index} ></Item>
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
