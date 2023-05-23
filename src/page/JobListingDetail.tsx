import {useEffect, useState} from "react";
import {JobListing} from "../data/init-data";
import JobListingCard from "../component/JobListingCard";
import {useParams} from "react-router";

const JobListingDetail = () => {
    const {id} = useParams<'id'>();

    const [loading, setLoading] = useState<boolean>(true)
    const [data, setData] = useState<JobListing | undefined>();

    useEffect(() => {
        fetchData(id!!);
    }, []);

    const fetchData = async (id: number | string) => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;

        const result = await fetch(`${backendUrl}/job-listing/${id}`);
        //console.log(await (result));
        setData(await (result.json()));
        setLoading(false);
    };

    return <div>
        {loading && <div>Loading ...</div>}
        {data && <JobListingCard job_listing={data}  alreadyInterested={[]}/>}
    </div>
};

export default JobListingDetail;