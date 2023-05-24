import React, {FormEvent, useState} from "react";
import {JobListing} from "../data/init-data";
import {Box, Button, Container, CssBaseline, TextField, Typography} from "@mui/material";
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";
import { useForm } from "react-hook-form";

const resolver = yupResolver(yup.object({
    "position": yup.string().max(128, "Max length 128 characters").required("Required field"),
    "jobField": yup.string().max(128, "Max length 128 characters").required("Required field"),
    "pay": yup.number().typeError("Pay must be a number").min(1).required("Required field")
}));
interface FormValues{
    position: String
    jobField: String
    pay: Number
}

const CreateJobListing = () => {
    const {register, handleSubmit, formState:{errors}} = useForm<FormValues>({resolver})
    //const {register,handleSubmit, formState: { errors },} = useForm();
    const [jobField, setJobField] = useState('Job field for position');
    const [pay, setPay] = useState(0);
    const [position, setPosition] = useState('Job position');
    const listingID = -1;
    const listingPosterId = -1;

    const submitHandle = async (data: FormValues) => {

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
                <Box component="form" onSubmit={handleSubmit(submitHandle)} noValidate sx={{mt: 1}}>
                    <TextField
                        {...register("position")}
                        margin="normal"
                        required
                        fullWidth
                        name="position"
                        label={position}
                        type="text"
                        id="position"
                        onChange={(e) => setPosition(e.target.value)}
                        autoComplete="current-password"
                        helperText={errors.position?.message}
                    />
                    <TextField
                        {...register("jobField")}
                        margin="normal"
                        required
                        fullWidth
                        id="jobField"
                        label={jobField}
                        name="jobField"
                        onChange={(e) => setJobField(e.target.value)}
                        autoFocus
                        helperText={errors.jobField?.message}
                    />

                    <TextField
                        {...register("pay")}
                        margin="normal"
                        required
                        fullWidth
                        id="pay"
                        label="Job pay in $"
                        type="number"
                        name="pay"
                        onChange={(e) => setPay(parseInt(e.target.value))}
                        autoFocus
                        helperText={errors.pay?.message}
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
};

export default CreateJobListing;