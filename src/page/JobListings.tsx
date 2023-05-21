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

const JobListings = () => {

    async function fetchData(){
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-listing`,{
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });
            if (!response.ok) {
                throw new Error('Request failed');
            }

            const data = await response.json();
            return data;

        } catch (error) {
            console.error('Error fetching data:', error);
            throw error;
        }
    };
    const [alreadyInterested, setAlreadyInterested] = useState<Array<Number>>([]);

    async function fetchAlreadyInterested() {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-interest/already-interested`,{
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });
            if (!response.ok) {
                throw new Error('Request failed');
            }

            const data = await response.json();
            return data;

        } catch (error) {
            console.error('Error fetching data:', error);
            throw error;
        }
    }
    /*
    useEffect(() => {
        fetchAlreadyInterested();
    }, []);

     */
/*
    const [results] = useQueries({
        queries: [
            {
                queryKey: ['listings', 1],
                queryFn: fetchData
            },

            {
                queryKey: ['alreadyInterested', 2],
                queryFn: fetchAlreadyInterested
            },
        ],
    });

 */

    const listingsQuery = useQuery({ queryKey: ['listings'], queryFn: fetchData })
    const interestedQuery = useQuery({ queryKey: ['interested'], queryFn: fetchAlreadyInterested })

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

    if (listingsQuery.isLoading && interestedQuery.isLoading){
        return <div className="alert alert-danger">loading</div>
    }
    if (listingsQuery.isError && interestedQuery.isError){
        console.log("error")
    }

    if (listingsQuery.isFetched && interestedQuery.isFetched){
        //console.log(listingsQuery.data)
        return <div className="jobListings">
            {/*isError && <div className="alert alert-danger">{JSON.stringify(error)}</div>*/}
            <JobListingList jobListings={listingsQuery.data} alreadyInterested={interestedQuery.data}/>
        </div>
    }
    //console.log(alreadyInterested);

};

export default JobListings;