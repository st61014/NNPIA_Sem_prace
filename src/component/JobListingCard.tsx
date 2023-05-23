import {JobListing} from "../data/init-data";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import jobListings from "../page/JobListings";
import {Button, Card, CardActions, CardContent, CardMedia, Typography} from "@mui/material";

interface Props {
    job_listing: JobListing
    alreadyInterested:  Array<Number>
}

const JobListingCard = ({job_listing, alreadyInterested} : Props) => {

    const [jobListing, setJobListing] = useState("");
    const handleButtonClick = (jobId:number) => {
        const data = {
            job_listing_listingid: jobId,
            user_userid: -1,
            status: 'open',
            creation_date: new Date().toISOString(),
        };
       // console.log(data);
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        fetch(`${backendUrl}/job-interest/create`, {
            method: 'PUT',
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(data),
        })
        setJobListing("interested");

        /*
            .then((response) => response.json())
            .then((data) => {
                // Handle the response if necessary
                console.log(data);
            })
            .catch((error) => {
                // Handle errors
                console.error(error);
            });
         */

    };
    //console.log(job_listing);
    if (alreadyInterested.includes(job_listing.listingID) || alreadyInterested.at(0) == -1 || jobListing == "interested"){
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
                    <Button disabled>
                        Already interested
                    </Button>
                </CardActions>
            </Card>
        );
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
                    Interested
                </Button>
            </CardActions>
        </Card>
    );
}

export default JobListingCard;