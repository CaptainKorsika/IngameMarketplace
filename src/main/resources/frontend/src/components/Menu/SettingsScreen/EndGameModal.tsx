import {Component, useEffect, useState} from 'react';
import {Modal, Box, Typography, Input} from '@mui/material';
import './EndGameModal.css'
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

    endGame = () => {
        this.handleClose()
        axios.post('http://localhost:8080/playerService/deletePlayer', {
            headers: {
                "Content-Type": "text/plain"
            }
        })
            .then(response => {
                console.log(response.data);
            })
        useEffect(() => {

        }, []);
    }

    render() {
        // @ts-ignore
        const { open } = this.state;
        return (
            <div>
                <button className="end-game-button" onClick={this.handleOpen}>End Game</button>
                <Modal
                    open={open}
                    onClose={this.handleClose}
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description"
                >
                    <Box className="end-game-modal">
                        <h2 className="end-modal-name-header">Are you sure?</h2>
                        <div className="end-game-button-wrapper">
                            <button className="continue-button" onClick={this.handleClose}>Back</button>
                            <button className="confirm-end-button" onClick={this.endGame}>End Game</button>
                        </div>

                    </Box>
                </Modal>
            </div>
        );
    }
}

export default StartGameModal;
