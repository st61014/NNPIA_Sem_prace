import {JobListing} from "../data/init-data";
import JobListingCard from "./JobListingCard";

interface Props {
    jobListings: Array<JobListing>
}

const JobListingList = ({jobListings} : Props) => {
    const jobListingShowInterestHandle = () => {
    }
    //console.log(jobListings);
    return <div>
        <h1>Jobs</h1>
        {jobListings.map(t =>
            <JobListingCard key={t.id} job_listing={t} onJobShowInterest={jobListingShowInterestHandle} />
        )}
    </div>
};

export default JobListingList;