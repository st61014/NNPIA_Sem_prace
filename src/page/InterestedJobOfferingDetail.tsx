import React, {useEffect, useState} from "react";
import {JobListing, UsersInterestedInJob} from "../data/init-data";
import JobListingCard from "../component/JobListingCard";
import {useParams} from "react-router";
import InterestedJobOfferingCard from "../component/InterestedJobOfferingCard";

const InterestedJobOfferingDetail = () => {
    const {id} = useParams<'id'>();

    const [loading, setLoading] = useState<boolean>(true)
    const [data, setData] = useState<UsersInterestedInJob | undefined>();

    useEffect(() => {
        fetchData(id!!);
    }, []);

    const fetchData = async (id: number | string) => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;

        const result = await fetch(`${backendUrl}/job-interest/user/${id}`);
        //console.log(await (result));
        setData(await (result.json()));
        setLoading(false);
    };

    return <div>
        {loading && <div>Loading ...</div>}
        {data && <InterestedJobOfferingCard interested_in_offering={data} />}
    </div>
};

export default InterestedJobOfferingDetail;