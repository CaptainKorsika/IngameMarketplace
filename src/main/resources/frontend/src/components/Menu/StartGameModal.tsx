import {useState} from 'react';
import {Modal, Box } from '@mui/material';
import "./StartGameModal.css"
import axios from "axios";

interface StartGameModalProps {
    showStartGameModal: boolean
    handleStartGameModal: (open: boolean) => void
}

const StartGameModal = (props: StartGameModalProps) => {
    const [open, setOpen] = useState(false)

    const handleOpen = () => {
        props.handleStartGameModal(true)
    };

    const handleClose = () => {
        props.handleStartGameModal(false)
    };

    const startGame = () => {
        // @ts-ignore
        const name = document.getElementsByClassName("name-input")[0].value
        handleClose()
        axios.post('http://localhost:8080/interaction/createPlayer', name, {
            headers: {
                "Content-Type": "text/plain"
            }
        })
            .then(response => {
                console.log(response.data);
            })
            .catch(error => {
                console.error("There was an error!", error.response ? error.response.data : error.message);
            });
    }
    return (
        <div>
            <button className="start-game" onClick={handleOpen}>Start New Game</button>
            <Modal
                open={props.showStartGameModal}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <Box className="start-game-modal">
                    <h2 className="name-header">Start a new Adventure</h2>
                    <input className="name-input" type="text" placeholder="Enter your name"/>
                    <div className="start-game-button-wrapper">
                        <button className="back-button" onClick={handleClose}>Back</button>
                        <button className="start-game-button" onClick={startGame}>Start Game</button>
                    </div>

                </Box>
            </Modal>
        </div>
    );
}

export default StartGameModal;
