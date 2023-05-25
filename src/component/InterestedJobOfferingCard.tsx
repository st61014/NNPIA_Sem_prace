import {JobListing, JobsInterestedIn, UsersInterestedInJob} from "../data/init-data";
import React, {useState} from "react";
import {Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";

interface Props {
    interested_in_offering: JobsInterestedIn
    onDelete: (job: number)=> void
}

const InterestedJobOfferingCard = ({interested_in_offering, onDelete} : Props) => {
    const handleDelete = (interestRecordId:number) => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        fetch(`${backendUrl}/job-interest/remove`, {

            method: 'DELETE',
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(interestRecordId),
        })

        onDelete(interestRecordId);
    };

    return (
        <Card sx={{ maxWidth: 345 , mt: 2, ml: 2, mr: 2}}>
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {interested_in_offering.position}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Job field: {interested_in_offering.job_field}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Position pay: {interested_in_offering.pay}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Offering status: {interested_in_offering.status}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Position: {interested_in_offering.position}
                </Typography>
            </CardContent>
            <CardActions>
                <Button onClick={() => handleDelete(interested_in_offering.job_listing_id)}>Remove interest</Button>
            </CardActions>
        </Card>
    );
}

export default InterestedJobOfferingCard;