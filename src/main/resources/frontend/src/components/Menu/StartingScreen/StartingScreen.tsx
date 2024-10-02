import "./StartingScreen.css"
import StartGameModal from "../StartGameModal";
import ShowHighScoreModal from "../ShowHighScoreModal";
import * as React from 'react';

const StartingScreen = () => {
    return (
        <div className="starting-screen">
            <StartGameModal></StartGameModal>
            <ShowHighScoreModal></ShowHighScoreModal>
        </div>
    );

}

export default StartingScreen;
