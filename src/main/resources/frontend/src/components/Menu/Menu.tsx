import MenuScreen from "./MenuScreen/MenuScreen";
import StartingScreen from "./StartingScreen/StartingScreen";
import {FocusItemObject} from "../../Interfaces/FocusItemObject";


interface MenuProps {
    isCurrentlyPlaying: boolean,
    focusItem: FocusItemObject,
    handleNextDay: () => void,
    handleItemTrade: (isBuying: boolean, amount: number) => void
}

const Menu = (props: MenuProps) => {
    return (
        <div className="menu-screen-container">
            {props.isCurrentlyPlaying ? (
                <MenuScreen focusItem={props.focusItem} handleNextDay={props.handleNextDay} handleItemTrade={props.handleItemTrade}/>
            ) : (
                <StartingScreen/>
            )}
        </div>
    );
}

export default Menu;
