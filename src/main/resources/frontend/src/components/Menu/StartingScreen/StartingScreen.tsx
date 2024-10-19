import "./StartingScreen.css"
import StartGameModal from "../StartGameModal";
import ShowHighScoreModal from "../ShowHighScoreModal";
import * as React from 'react';

interface StartingScreenProps {
    showStartGameModal: boolean
    handleStartGameModal: (open: boolean) => void
}


const StartingScreen = (props: StartingScreenProps) => {
    return (
        <div className="starting-screen">
            <StartGameModal showStartGameModal={props.showStartGameModal}
                            handleStartGameModal={props.handleStartGameModal}></StartGameModal>
            <ShowHighScoreModal></ShowHighScoreModal>
        </div>
    );

}

export default StartingScreen;
