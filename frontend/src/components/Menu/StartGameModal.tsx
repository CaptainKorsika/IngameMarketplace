import React, { Component } from 'react';
import {Modal, Box, Typography, Input} from '@mui/material';
import "./StartGameModal.css"

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

    render() {
        const { open } = this.state;

        const style = {

        };

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
                            <button className="start-game-button">Start Game</button>
                        </div>

                    </Box>
                </Modal>
            </div>
        );
    }
}

export default StartGameModal;
