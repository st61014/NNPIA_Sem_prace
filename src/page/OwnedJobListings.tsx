import JobListingList from "../component/JobListingList";
import {JobListing, JobsInterestedIn} from "../data/init-data";
import {useEffect, useState} from "react";
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

const OwnedJobListings = () => {
    const [ownedListings, setOwnedListings] = useState<Array<JobListing>>([]);
    async function fetchData(){
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-listing/owned`,{
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
   // const [alreadyInterested, setAlreadyInterested] = useState<Array<Number>>([]);

    const listingsQuery = useQuery({ queryKey: ['listings'], queryFn: fetchData })

    //if (interestedQuery.data) console.log(interestedQuery.data)
    //console.log(listingsData)

    //https://www.js-howto.com/how-to-handle-multiple-queries-with-react-query/

    //const isLoggedIn = useSelector((state: RootState) => state.login.value);
    //const {data} = useQuery({queryKey: ['alreadyInterested'], queryFn: fetchAlreadyInterested})
    //const {isLoading, data, isError, error} = useQuery({queryKey: ['joblistings'], queryFn: fetchData})
    //console.log(alreadyInterestedIn);
    //const {error, loading, tasks}=useTask(isLoggedIn);
    //console.log(data);
    //console.log(alreadyInterestedIn);

    if (listingsQuery.isLoading){
        return <div className="alert alert-danger">loading</div>
    }
    if (listingsQuery.isError){
        console.log("error")
    }
    if (listingsQuery.isFetched){
        return <div>
            <h1>My listings</h1>
            {ownedListings.map(t =>
                <OwnedJobListingCard key={t.listingID} job_listing={t}/>
            )}
        </div>
    }
    //console.log(alreadyInterested);

};

export default OwnedJobListings;