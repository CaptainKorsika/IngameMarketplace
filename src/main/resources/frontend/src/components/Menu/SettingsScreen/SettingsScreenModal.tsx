import {Modal, Box } from '@mui/material';
import EndGameModal from "./EndGameModal";
import ShowHighScoreModal from "../ShowHighScoreModal";
import "./SettingsScreenModal.css"
import {useState} from "react";

interface SettingsScreenModalProps {
    showHighScoreModal: boolean
    handleShowHighScoreModal: (open: boolean) => void
}

const SettingsScreenModal = (props: SettingsScreenModalProps) => {
    const [open, setOpen] = useState(false)

    const handleOpen = () => {
        setOpen(true)
    }

    const handleClose = () => {
        setOpen(false)
    }

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
                    <ShowHighScoreModal
                        showHighScoreModal={props.showHighScoreModal}
                        handleShowHighScoreModal={props.handleShowHighScoreModal}
                    />
                    <EndGameModal handleShowSettingsModal={handleClose}/>
                </Box>
            </Modal>
        </div>
    );

}

export default SettingsScreenModal;
