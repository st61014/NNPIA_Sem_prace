import {JobListing} from "../data/init-data";
import React from "react";

interface Props {
    job_listing: JobListing
    alreadyInterested:  Array<Number>
}

const OwnedJobListingCard = ({job_listing} : Props) => {
    //console.log(alreadyInterested)
    const handleButtonClick = (jobId:number) => {
        // console.log(data);
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        fetch(`${backendUrl}/job-listing/remove`, {
            method: 'DELETE',
            headers: {
                Authorization: `Bearer ${localStorage.getItem("token")}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(jobId),
        })
    };
    return <div>
        <h2>{job_listing.position}</h2>
        <p>{job_listing.jobField}</p>
        <p>{job_listing.pay}</p>
        <p>{job_listing.listingPosterId}</p>
        <button onClick={() => handleButtonClick(job_listing.listingID)}>
            Delete
        </button>
    </div>
}

export default OwnedJobListingCard;