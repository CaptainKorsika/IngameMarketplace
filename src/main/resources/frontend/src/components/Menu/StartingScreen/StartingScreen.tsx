import {Component, useState} from "react";
import "./StartingScreen.css"
import StartGameModal from "../StartGameModal";
import ShowHighScoreModal from "../ShowHighScoreModal";
import * as React from 'react';


class StartingScreen extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showGameModal: false,
            showHighScoreModal: false
        }
    }

    openStartGameModal = () => {
        this.setState({showGameModal: true});
    };

    closeStartGameModal = () => {
        this.setState({showGameModal: false});
    };

    openHighScoreModal = () => {
        this.setState({showGameModal: true});
    };

    closeHighScoreModal = () => {
        this.setState({showHighScoreModal: false});
    };


    render() {
        return (
            <div className="starting-screen">
                <StartGameModal></StartGameModal>
                <ShowHighScoreModal></ShowHighScoreModal>
            </div>
        );
    }
}

export default StartingScreen;
