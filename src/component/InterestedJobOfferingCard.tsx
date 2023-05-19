import {JobListing, JobsInterestedIn, UsersInterestedInJob} from "../data/init-data";
import React from "react";

interface Props {
    interested_in_offering: JobsInterestedIn
}

const InterestedJobOfferingCard = ({interested_in_offering} : Props) => {
    const showInterestHandle = (e: React.ChangeEvent<HTMLInputElement>) => {
    };
    console.log(interested_in_offering);
    return <div>
        <h2>{interested_in_offering.position}</h2>
        <p>{interested_in_offering.job_field}</p>
        <p>{interested_in_offering.pay}</p>
        <p>{interested_in_offering.status}</p>
        <p>{interested_in_offering.creation_date}</p>
    </div>
}

export default InterestedJobOfferingCard;