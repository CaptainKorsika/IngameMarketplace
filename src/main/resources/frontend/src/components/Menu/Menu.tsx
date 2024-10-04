import MenuScreen from "./MenuScreen/MenuScreen";
import StartingScreen from "./StartingScreen/StartingScreen";
import {ItemObject} from "../../Interfaces/ItemObject";


interface MenuProps {
    isCurrentlyPlaying: boolean,
    focusItem: ItemObject
}

const Menu = (props: MenuProps) => {
    return (
        <div className="menu-screen-container">
            {props.isCurrentlyPlaying ? (
                <MenuScreen focusItem={props.focusItem}/>
            ) : (
                <StartingScreen/>
            )}
        </div>
    );
}

export default Menu;
