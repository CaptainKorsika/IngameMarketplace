import "./Item.css"
import {ItemObject} from "../../Interfaces/ItemObject";

interface ItemProps {
    item?: ItemObject
    key?: number
    amount?: number
    handleFocusItem?: (focusItem: ItemObject) => void
}

const Item = (props: ItemProps) => {
    const focusItem = () => {
        props.handleFocusItem(props.item)
    }

    if (props.item == null) {
        return (
            <div className="item-container" onClick={focusItem}></div>
        )
    }

    if (props.amount == 0) {
        return (
            <div className="item-container">
                <img src={props.item.first.image} alt={props.item.first.name} className="item-image" style={{ opacity: 0.5, filter: "saturate(0)" }}/>
            </div>
        )
    }

    return (
        <div className="item-container" onClick={focusItem}>
            <img src={props.item.first.image} alt={props.item.first.name} className="item-image"/>
        </div>
    );
}

export default Item;
