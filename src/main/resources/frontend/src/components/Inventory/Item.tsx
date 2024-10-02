import "./Item.css"
import {ItemObject} from "../../Interfaces/ItemObject";

interface ItemProps {
    item?: ItemObject
    key?: number
}

const Item = (props: ItemProps) => {
    if (props.item == null) {
        return (
            <div className="item-container"></div>
        )
    }

    return (
        <div className="item-container">
            <img src={props.item.image} alt={props.item.name} className="item-image"/>
        </div>
    );
}

export default Item;
