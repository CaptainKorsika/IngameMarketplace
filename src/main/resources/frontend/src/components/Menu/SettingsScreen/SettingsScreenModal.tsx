import {Component, useState} from 'react';
import {Modal, Box, Typography, Input} from '@mui/material';
import EndGameModal from "./EndGameModal";
import ShowHighScoreModal from "../ShowHighScoreModal";
import "./SettingsScreenModal.css"

class SettingsScreenModal extends Component {
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
        // @ts-ignore
        const { open } = this.state;
        return (
            <div>
                <button className="settings-button" onClick={this.handleOpen}>Settings</button>
                <Modal
                    open={open}
                    onClose={this.handleClose}
                    aria-labelledby="simple-modal-title"
                    aria-describedby="simple-modal-description"
                >
                    <Box className="settings-modal">
                        <ShowHighScoreModal></ShowHighScoreModal>
                        <EndGameModal></EndGameModal>
                    </Box>
                </Modal>
            </div>
        );
    }
}

export default SettingsScreenModal;
