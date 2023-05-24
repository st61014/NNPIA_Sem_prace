import {JobListing} from "../data/init-data";
import JobListingCard from "./JobListingCard";
import React from "react";
import {Box, Button, FormControl, Grid, Select} from "@mui/material";
import MenuItem from "@mui/material/MenuItem";

interface Props {
    jobListings: Array<JobListing>
    alreadyInterested: Array<Number>
}

const JobListingList = ({jobListings, alreadyInterested}: Props) => {

    return <div>
            <Grid container spacing={2} direction="row" columnSpacing={{ xs: 1, sm: 2, md: 3 }} justifyContent="center"
                  alignItems="center">
                {jobListings.map((elem) => (
                    <Grid item xs={6} lg={3} xl={2} key={jobListings.indexOf(elem)}>
                        <JobListingCard key={elem.listingID} job_listing={elem} alreadyInterested={alreadyInterested}/>
                    </Grid>
                ))}
            </Grid>

    </div>

};

export default JobListingList;