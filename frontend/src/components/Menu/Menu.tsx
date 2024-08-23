import {Component, useEffect, useState} from "react";
import MenuScreen from "./MenuScreen/MenuScreen";
import StartingScreen from "./StartingScreen/StartingScreen";


interface MenuProps {
    isCurrentlyPlaying: boolean,
}

class Menu extends Component<MenuProps> {
    render() {
        const {isCurrentlyPlaying} = this.props;
        return (
            <div className="menu-screen-container">
                {isCurrentlyPlaying ? (
                    <MenuScreen/>
                ) : (
                    <StartingScreen/>
                )}
            </div>
        );
    }
}

export default Menu;
