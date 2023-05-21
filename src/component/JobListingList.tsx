import {JobListing} from "../data/init-data";
import JobListingCard from "./JobListingCard";

interface Props {
    jobListings: Array<JobListing>
    alreadyInterested:  Array<Number>
}

const JobListingList = ({jobListings, alreadyInterested} : Props) => {
    //console.log(jobListings);
    //console.log(alreadyInterested);
    return <div>
        <h1>Jobs</h1>
        {jobListings.map(t =>
            <JobListingCard key={t.listingID} job_listing={t} alreadyInterested={alreadyInterested}/>
        )}
    </div>
};

export default JobListingList;