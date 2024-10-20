import "./StartingScreen.css"
import StartGameModal from "../StartGameModal";
import ShowHighScoreModal from "../ShowHighScoreModal";
import * as React from 'react';

interface StartingScreenProps {
    showHighScoreModal: boolean
    handleShowHighScoreModal: (open: boolean) => void
}

const StartingScreen = (props: StartingScreenProps) => {
    return (
        <div className="starting-screen">
            <StartGameModal/>
            <ShowHighScoreModal
                showHighScoreModal={props.showHighScoreModal}
                handleShowHighScoreModal={props.handleShowHighScoreModal}
            />
        </div>
    );

}

export default StartingScreen;
