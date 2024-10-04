import "./Item.css"
import {ItemObject} from "../../Interfaces/ItemObject";

interface ItemProps {
    item?: ItemObject
    key?: number
    amount?: number
    handleFocusItem?: (focusItem: ItemObject) => void
}

const Item = (props: ItemProps) => {
    if (props.item == null) {
        return (
            <div className="item-container"></div>
        )
    }

    const focusItem = () => {
        props.handleFocusItem(props.item)
    }

    return (
        <div className="item-container" onClick={focusItem}>
            <img src={props.item.first.image} alt={props.item.first.name} className="item-image"/>
            <p>{props.amount}</p>
        </div>
    );
}

export default Item;
