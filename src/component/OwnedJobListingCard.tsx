import {JobListing} from "../data/init-data";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import {Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";

interface Props {
    job_listing: JobListing
}

const OwnedJobListingCard = ({job_listing} : Props) => {
    const [interestedInListing, setInterestedInListing] = useState("");
    const handleButtonClick = (jobId:number) => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        fetch(`${backendUrl}/job-listing/remove`, {
            method: 'DELETE',
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(jobId),
        })
        setInterestedInListing("deleted");
    };
    if (interestedInListing == "deleted"){
        return <></>
    }

    return (
        <Card sx={{ maxWidth: 345 , mt: 2, ml: 2, mr: 2}}>
            <CardContent>
                <Typography gutterBottom variant="h5" component="div">
                    {job_listing.position}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Job field: {job_listing.jobField}
                </Typography>
                <Typography variant="body2" color="text.secondary">
                    Position pay: {job_listing.pay}
                </Typography>
            </CardContent>
            <CardActions>
                <Button onClick={() => handleButtonClick(job_listing.listingID)}>
                    Delete
                </Button>
            </CardActions>
        </Card>
    );

}

export default OwnedJobListingCard;