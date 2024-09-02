import { Component } from 'react';
import { Modal, Box, Typography } from '@mui/material';
import "./ShowHighScoreModal.css"

class ShowHighScoreModal extends Component {
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
                <button className="show-highscore" onClick={this.handleOpen}>Show Highscore</button>
                <Modal
                    open={open}
                    onClose={this.handleClose}
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
                        <button onClick={this.handleClose}>Close</button>
                    </Box>
                </Modal>
            </div>
        );
    }
}

export default ShowHighScoreModal;
