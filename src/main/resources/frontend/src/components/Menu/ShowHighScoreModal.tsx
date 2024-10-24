import { Modal, Box, Typography } from '@mui/material';
import "./ShowHighScoreModal.css"

interface ShowHighScoreModalProps {
    showHighScoreModal: boolean
    handleShowHighScoreModal: (open: boolean) => void
}


const ShowHighScoreModal = (props: ShowHighScoreModalProps) => {
    const handleOpen = () => {
        props.handleShowHighScoreModal(true)
    };

    const handleClose = () => {
        props.handleShowHighScoreModal(false)
    };

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
    };

    return (
        <div>
            <button className="show-highscore" onClick={handleOpen}>Show Highscore</button>
            <Modal
                open={props.showHighScoreModal}
                onClose={handleClose}
                aria-labelledby="simple-modal-title"
                aria-describedby="simple-modal-description"
            >
                <Box sx={style}>
                    <Typography id="simple-modal-title" variant="h6" component="h2">
                        Simple Modal
                    </Typography>
                    <Typography id="simple-modal-description" sx={{mt: 2}}>
                        This is a simple modal using Material-UI.
                    </Typography>
                    <button onClick={handleClose}>Close</button>
                </Box>
            </Modal>
        </div>
    );
}

export default ShowHighScoreModal;
