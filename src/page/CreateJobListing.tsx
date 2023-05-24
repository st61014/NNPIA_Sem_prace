import React, {FormEvent, useState} from "react";
import {JobListing} from "../data/init-data";
import {Box, Button, Container, CssBaseline, TextField, Typography} from "@mui/material";


const CreateJobListing = () => {
    const [jobField, setJobField] = useState('Job field for position');
    const [pay, setPay] = useState(0);
    const [position, setPosition] = useState('Job position');
    const listingID = -1;
    const listingPosterId = -1;

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();

        const jobListing: JobListing = {
            listingID,
            jobField,
            pay,
            position,
            listingPosterId
        };

        try {
            const backendUrl = import.meta.env.VITE_BACKEND_URL;
            const response = await fetch(`${backendUrl}/job-listing/create`, {
                method: 'PUT',
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(jobListing)
            });

            if (response.ok) {
                setJobField('');
                setPay(0);
                setPosition('');
            } else {
                console.error('Failed to create job listing');
            }
        } catch (error) {
            console.error('An error occurred', error);
        }

        setJobField('');
        setPay(0);
        setPosition('');
    };


    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}>
                <Typography component="h1" variant="h5">
                    Create new job listing
                </Typography>
                <Box component="form" onSubmit={handleSubmit} noValidate sx={{mt: 1}}>

                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        name="position"
                        label={position}
                        type="text"
                        id="position"
                        onChange={(e) => setPosition(e.target.value)}
                        autoComplete="current-password"
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="jobField"
                        label={jobField}
                        name="jobField"
                        onChange={(e) => setJobField(e.target.value)}
                        autoFocus
                    />

                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="pay"
                        label="Job pay in $"
                        type="number"
                        name="pay"
                        onChange={(e) => setPay(parseInt(e.target.value))}
                        autoFocus
                    />

                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{mt: 3, mb: 2}}>
                        Create listing
                    </Button>
                </Box>
            </Box>
        </Container>
    );


    return (
        <div>
            <h1>Create Job Listing</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Job Field:</label>
                    <input
                        type="text"
                        value={jobField}
                        onChange={(e) => setJobField(e.target.value)}
                    />
                </div>
                <div>
                    <label>Pay:</label>
                    <input
                        type="number"
                        value={pay}
                        onChange={(e) => setPay(e.target.valueAsNumber)}
                    />
                </div>
                <div>
                    <label>Position:</label>
                    <input
                        type="text"
                        value={position}
                        onChange={(e) => setPosition(e.target.value)}
                    />
                </div>
                <button type="submit">Create</button>
            </form>
        </div>
    );
};

export default CreateJobListing;