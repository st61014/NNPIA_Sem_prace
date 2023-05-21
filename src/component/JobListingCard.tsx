import {JobListing} from "../data/init-data";
import React from "react";

interface Props {
    job_listing: JobListing
    alreadyInterested:  Array<Number>
}

const JobListingCard = ({job_listing, alreadyInterested} : Props) => {
    //console.log(alreadyInterested)
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
    if (alreadyInterested.includes(job_listing.listingID)){
        return <div>
            <h2>{job_listing.position}</h2>
            <p>{job_listing.jobField}</p>
            <p>{job_listing.pay}</p>
            <p>{job_listing.listingPosterId}</p>
        </div>
    }
    return <div>
        <h2>{job_listing.position}</h2>
        <p>{job_listing.jobField}</p>
        <p>{job_listing.pay}</p>
        <p>{job_listing.listingPosterId}</p>
        <button onClick={() => handleButtonClick(job_listing.listingID)}>
            Interested
        </button>
    </div>
}

export default JobListingCard;