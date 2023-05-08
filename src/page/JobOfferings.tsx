import JobListingList from "../component/JobListingList";
import {JobListing} from "../data/init-data";
import {useEffect, useState} from "react";
import './JobListings.css';
import {useSelector} from "react-redux";
import {RootState} from "../features/store";
import {useTask} from "../features/hook/hooks";
import {useQuery} from "@tanstack/react-query";
import {queryKey} from "@tanstack/react-query/build/lib/__tests__/utils";
import JobListingForm from "../component/JobListingForm";
import header from "../component/ui/Header";

const JobOfferings = () => {
    const fetchData = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        let response = null;

        response = await fetch(`${backendUrl}/job-offering`);
        let jsonResponse = await response.json();
        //console.table(jsonResponse);
        return jsonResponse;
    };

    //const isLoggedIn = useSelector((state: RootState) => state.login.value);
    const {isLoading, data, isError, error} = useQuery({queryKey: ['joblistings'], queryFn: fetchData})
    //const {error, loading, tasks}=useTask(isLoggedIn);

    if (isLoading){
        return <div className="alert alert-danger">loading</div>
    }
    //console.log(data);
    return <div className="tasks">
        {isError && <div className="alert alert-danger">{JSON.stringify(error)}</div>}
        <JobListingList jobListings={data}/>
        <JobListingForm/>
    </div>
};

export default JobOfferings;