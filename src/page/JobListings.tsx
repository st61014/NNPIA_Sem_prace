import JobListingList from "../component/JobListingList";
import {JobListing, JobsInterestedIn} from "../data/init-data";
import React, {useEffect, useState} from "react";
import './JobListings.css';
import {useSelector} from "react-redux";
import {RootState} from "../features/store";
import {useTask} from "../features/hook/hooks";
import {useQueries, useQuery, useQueryClient} from "@tanstack/react-query";
import {queryKey} from "@tanstack/react-query/build/lib/__tests__/utils";
import header from "../component/ui/Header";
import {Box, Button, FormControl, Select, SelectChangeEvent} from "@mui/material";
import MenuItem from "@mui/material/MenuItem";

const JobListings = () => {
    const [usersInterests, setUsersInterests] = useState<Array<Number>>([]);
    const [allListings, setAllListings] = useState<Array<JobListing>>([]);
    const [loggedUserExcludedListings, setLoggedUserExcludedListings] = useState<Array<JobListing>>([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [sortBySelect, setSortBySelect] = useState("jobField");
    const isLoggedIn = useSelector((state: RootState) => state.login.value);
    const queryClient = useQueryClient();
    useEffect(() => {
        if (isLoggedIn) {
            fetchDataAuthorized()
            fetchAlreadyInterested()
        } else {
            fetchDataUnauthorized();
        }
    }, [currentPage]);
    useEffect(() => {
        if (isLoggedIn) {
            fetchDataAuthorized()
            fetchAlreadyInterested()
        } else {
            fetchDataUnauthorized();
        }


    }, [sortBySelect]);


    const handleNextPage = () => {
        setCurrentPage(prevPage => prevPage + 1);
    };
    const handlePrevPage = () => {
        if (currentPage > 0) {
            setCurrentPage(prevPage => prevPage - 1);
        }
    };
    const handleChange = (event: SelectChangeEvent) => {
        setSortBySelect(event.target.value as string);
    };

    const fetchDataAuthorized = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-listing/all-not-owned?page=${currentPage}&sort=${sortBySelect}`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });
            if (!response.ok) {
                throw new Error('Request failed');
            }

            const data = await response.json();
            setLoggedUserExcludedListings(data);
            return data;

        } catch (error) {
            console.error('Error fetching data:', error);
            throw error;
        }
    };

    const fetchDataUnauthorized = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-listing/all?page=${currentPage}&sort=${sortBySelect}`);
            if (!response.ok) {
                throw new Error('Request failed');
            }

            const data = await response.json();
            setAllListings(data)
            return data;

        } catch (error) {
            console.error('Error fetching data:', error);
            throw error;
        }
    };

    const fetchAlreadyInterested = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-interest/already-interested`, {
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                },
            });
            if (!response.ok) {
                throw new Error('Request failed');
            }

            const data = await response.json();
            setUsersInterests(data)
            return data;

        } catch (error) {
            console.error('Error fetching data:', error);
            throw error;
        }
    }


    if (isLoggedIn) {
        return <div className="jobListings">
            <JobListingList jobListings={loggedUserExcludedListings} alreadyInterested={usersInterests}/>
            <Box
                m={1}
                display="flex"
                justifyContent="center"
                alignItems="center">
                <Button variant="contained" onClick={handlePrevPage} sx={{mr: 1}}>Previous Page</Button>
                <Button variant="contained" onClick={handleNextPage}>Next Page</Button>
                <FormControl sx={{m: 1, minWidth: 120}} size="small">
                    <Select
                        value={sortBySelect}
                        label="SortBy"
                        onChange={handleChange}>
                        <MenuItem value={"jobField"}>Job field</MenuItem>
                        <MenuItem value={"pay"}>Pay</MenuItem>
                    </Select>
                </FormControl>
            </Box>
        </div>

    } else {
        return <div className="jobListings">
            <JobListingList jobListings={allListings} alreadyInterested={[-1]}/>
            <Box
                m={1}
                display="flex"
                justifyContent="center"
                alignItems="center">
                <Button variant="contained" onClick={handlePrevPage} sx={{mr: 1}}>Previous Page</Button>
                <Button variant="contained" onClick={handleNextPage}>Next Page</Button>
                <FormControl sx={{m: 1, minWidth: 120}} size="small">
                    <Select
                        value={sortBySelect}
                        label="SortBy"
                        onChange={handleChange}>
                        <MenuItem value={"jobField"}>Job field</MenuItem>
                        <MenuItem value={"pay"}>Pay</MenuItem>
                    </Select>
                </FormControl>
            </Box>
        </div>
    }
};

export default JobListings;