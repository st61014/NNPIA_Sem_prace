import React, { useState, useEffect } from 'react';
import {UsersInterestedInJob} from "../data/init-data";
import JobListingCard from "../component/JobListingCard";
import InterestedJobOfferingCard from "../component/InterestedJobOfferingCard";

interface Props {
    interested_in_offering: Array<UsersInterestedInJob>
}

function InterestedInOfferings() {
    const [usersInterests, setUsersInterests] = useState<Array<UsersInterestedInJob>>([]);
    const [currentPage, setCurrentPage] = useState(0);

    useEffect(() => {
        fetchUsersInterestedListings();
    }, [currentPage]);

    const fetchUsersInterestedListings = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        //console.log(localStorage.getItem("token"));
        try {
            const response = await fetch(`${backendUrl}/job-interest/user?page=${currentPage}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });

            const data = await response.json();
            setUsersInterests(data);
        } catch (error) {
            console.error('Error fetching job listings:', error);
        }
    };

    const handleNextPage = () => {
        setCurrentPage(prevPage => prevPage + 1);
    };
    //console.log(usersInterests);
    return (
        <div>
            <h1>Jobs</h1>
            {usersInterests.map(t =>
                <InterestedJobOfferingCard key={t.creation_date} interested_in_offering={t} />
            )}
            <button onClick={handleNextPage}>Next Page</button>
        </div>
    );
}

export default InterestedInOfferings;