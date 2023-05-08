import {JobListing} from "../data/init-data";
import React from "react";

interface Props {
    job_listing: JobListing
    onJobShowInterest: (jobListing: JobListing) => void
}

const JobListingCard = ({job_listing, onJobShowInterest} : Props) => {
    const showInterestHandle = (e: React.ChangeEvent<HTMLInputElement>) => {
        job_listing.position = "e.target.checked";

        onJobShowInterest(job_listing);
    };
    console.log(job_listing);
    return <div>
        <h2>{job_listing.position}</h2>
        <p>{job_listing.jobField}</p>
        <p>{job_listing.pay}</p>
        <p>{job_listing.listingPosterId}</p>
        <label>Show interest</label>
        <input type="checkbox" checked={false} name="done" onChange={showInterestHandle} />
    </div>
}

export default JobListingCard;