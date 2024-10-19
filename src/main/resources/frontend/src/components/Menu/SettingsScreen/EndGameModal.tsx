import {useEffect, useState} from 'react';
import {Modal, Box } from '@mui/material';
import './EndGameModal.css'
import axios from "axios";

const EndGameModal = () => {
    const [open, setOpen] = useState(false)

    const handleOpen = () => {
        setOpen(true)
    };

    const handleClose = () => {
        setOpen(false)
    };

    const endGame = () => {
        handleClose()
        axios.post('http://localhost:8080/interaction/endGame', {
            headers: {
                "Content-Type": "text/plain"
            }
        })
        .then(response => {
            console.log(response.data);
        })
    }

    return (
        <div>
            <button className="end-game-button" onClick={handleOpen}>End Game</button>
            <Modal
                open={open}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <Box className="end-game-modal">
                    <h2 className="end-modal-name-header">Are you sure?</h2>
                    <div className="end-game-button-wrapper">
                        <button className="continue-button" onClick={handleClose}>Back</button>
                        <button className="confirm-end-button" onClick={endGame}>End Game</button>
                    </div>

                </Box>
            </Modal>
        </div>
    );

}

export default EndGameModal;
