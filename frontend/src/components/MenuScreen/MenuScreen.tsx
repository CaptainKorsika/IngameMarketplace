import {Component, useEffect, useState} from "react";
import Menu from "./Menu/Menu";
import StartingScreen from "./StartingScreen/StartingScreen";


interface MenuScreenProps {
    isCurrentlyPlaying: boolean
}

class MenuScreen extends Component<MenuScreenProps> {
    render() {
        const {isCurrentlyPlaying} = this.props;
        return (
            <div className="menu-screen-container">
                {isCurrentlyPlaying ? (
                    <Menu/>
                ) : (
                    <StartingScreen/>
                )}
            </div>
        );
    }
}

export default MenuScreen;
