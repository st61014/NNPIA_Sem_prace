import JobListingList from "../component/JobListingList";
import {JobListing, JobsInterestedIn} from "../data/init-data";
import React, {useEffect, useState} from "react";
import './JobListings.css';
import {useSelector} from "react-redux";
import {RootState} from "../features/store";
import {useTask} from "../features/hook/hooks";
import {useQueries, useQuery} from "@tanstack/react-query";
import {queryKey} from "@tanstack/react-query/build/lib/__tests__/utils";
import JobListingForm from "../component/JobListingForm";
import header from "../component/ui/Header";
import JobListingCard from "../component/JobListingCard";
import OwnedJobListingCard from "../component/OwnedJobListingCard";
import {Grid} from "@mui/material";

const OwnedJobListings = () => {
    const [ownedListings, setOwnedListings] = useState<Array<JobListing>>([]);

    async function fetchData() {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-listing/owned`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });
            if (!response.ok) {
                throw new Error('Request failed');
            }

            const data = await response.json();
            setOwnedListings(data)
            return data;

        } catch (error) {
            console.error('Error fetching data:', error);
            throw error;
        }
    };
    const listingsQuery = useQuery({queryKey: ['listings'], queryFn: fetchData})
    if (listingsQuery.isLoading) {
        return <div className="alert alert-danger">loading</div>
    }
    if (listingsQuery.isError) {
        console.log("error")
    }
    if (listingsQuery.isFetched) {
        return <div>
            <Grid container spacing={2} direction="row" columnSpacing={{xs: 1, sm: 2, md: 3}} justifyContent="center"
                  alignItems="center">
                {ownedListings.map(t =>
                    <Grid item xs={6} lg={3} xl={2} key={ownedListings.indexOf(t)}>
                        <OwnedJobListingCard key={t.listingID} job_listing={t}/>
                    </Grid>
                )}
            </Grid>
        </div>
    }
    //console.log(alreadyInterested);

};

export default OwnedJobListings;