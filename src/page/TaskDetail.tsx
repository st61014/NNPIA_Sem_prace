import {useEffect, useState} from "react";
import {JobListing} from "../data/init-data";
import JobListingCard from "../component/JobListingCard";
import {useParams} from "react-router";

const TaskDetail = () => {
    const {id} = useParams<'id'>();

    const [loading, setLoading] = useState<boolean>(true)
    const [data, setData] = useState<JobListing | undefined>();

    useEffect(() => {
        fetchData(id!!);
    }, []);

    const fetchData = async (id: number | string) => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;

        const result = await fetch(`${backendUrl}/job-listing/${id}`);
        setData(await (result.json()));
        setLoading(false);
    };

    return <div>
        {loading && <div>Loading ...</div>}
        {data && <JobListingCard task={data} onTaskDone={() => {}} />}
    </div>
};

export default TaskDetail;