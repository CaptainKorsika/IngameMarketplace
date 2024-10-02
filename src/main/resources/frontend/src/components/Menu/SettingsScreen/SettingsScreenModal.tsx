import {useState} from 'react';
import {Modal, Box } from '@mui/material';
import EndGameModal from "./EndGameModal";
import ShowHighScoreModal from "../ShowHighScoreModal";
import "./SettingsScreenModal.css"

const SettingsScreenModal = () => {
    const [open, setOpen] = useState(false)

    const handleOpen = () => {
        setOpen(true)
    };

    const handleClose = () => {
        setOpen(false)
    };

    return (
        <div>
            <button className="settings-button" onClick={handleOpen}>Settings</button>
            <Modal
                open={open}
                onClose={handleClose}
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

export default SettingsScreenModal;
