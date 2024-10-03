import "./Item.css"
import {ItemObject} from "../../Interfaces/ItemObject";

interface ItemProps {
    item?: ItemObject
    key?: number
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
            <img src={props.item.image} alt={props.item.name} className="item-image"/>
        </div>
    );
}

export default Item;
