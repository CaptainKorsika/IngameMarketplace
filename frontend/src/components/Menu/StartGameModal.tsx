import { Component } from 'react';
import {Modal, Box, Typography, Input} from '@mui/material';
import "./StartGameModal.css"
import axios from "axios";

class StartGameModal extends Component {
    constructor(props) {
        super(props);
        this.state = {
            open: false,
        };
    }

    handleOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    startGame = () => {
        // @ts-ignore
        const name = document.getElementsByClassName("name-input")[0].value
        axios.post('http://localhost:8080/start-game/create-player', name)
            .then(response => {
                console.log(response.data);
                this.handleClose()
            })
            .catch(error => {
                console.error("There was an error!", error.response ? error.response.data : error.message);
            });
    }

    render() {
        // @ts-ignore
        const { open } = this.state;
        return (
            <div>
                <button className="start-game" onClick={this.handleOpen}>Start New Game</button>
                <Modal
                    open={open}
                    onClose={this.handleClose}
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description"
                >
                    <Box className="start-game-modal">
                        <h2 className="name-header">Start a new Adventure</h2>
                        <input className="name-input" type="text" placeholder="Enter your name"/>
                        <div className="start-game-button-wrapper">
                            <button className="back-button" onClick={this.handleClose}>Back</button>
                            <button className="start-game-button" onClick={this.startGame}>Start Game</button>
                        </div>

                    </Box>
                </Modal>
            </div>
        );
    }
}

export default StartGameModal;
