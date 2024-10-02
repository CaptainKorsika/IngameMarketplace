import MenuScreen from "./MenuScreen/MenuScreen";
import StartingScreen from "./StartingScreen/StartingScreen";


interface MenuProps {
    isCurrentlyPlaying: boolean,
}

const Menu = (props: MenuProps) => {
    return (
        <div className="menu-screen-container">
            {props.isCurrentlyPlaying ? (
                <MenuScreen/>
            ) : (
                <StartingScreen/>
            )}
        </div>
    );
}

export default Menu;
