import React, {useState, useEffect} from 'react';
import {JobsInterestedIn, UsersInterestedInJob} from "../data/init-data";
import JobListingCard from "../component/JobListingCard";
import InterestedJobOfferingCard from "../component/InterestedJobOfferingCard";
import {Box, Button, FormControl, Grid, Select, SelectChangeEvent} from "@mui/material";
import OwnedJobListingCard from "../component/OwnedJobListingCard";
import MenuItem from "@mui/material/MenuItem";

interface Props {
    interested_in_offering: Array<JobsInterestedIn>
}

function InterestedInOfferings() {
    const [usersInterests, setUsersInterests] = useState<Array<JobsInterestedIn>>([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [sortBySelect, setSortBySelect] = useState("status");

    useEffect(() => {
        fetchUsersInterestedListings();
    }, [currentPage]);
    useEffect(() => {
        fetchUsersInterestedListings();
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
    const fetchUsersInterestedListings = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/job-interest/user?page=${currentPage}&sort=${sortBySelect}`, {
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

    return (
        <div style={{width: '100%'}}>
            <Grid container spacing={2} direction="row" columnSpacing={{xs: 1, sm: 2, md: 3}} justifyContent="center"
                  alignItems="center">
                {usersInterests.map(t =>
                    <Grid item xs={6} lg={3} xl={2} key={usersInterests.indexOf(t)}>
                        <InterestedJobOfferingCard key={t.creation_date} interested_in_offering={t}/>
                    </Grid>
                )}
            </Grid>
            <Box
                m={1}
                display="flex"
                justifyContent="center"
                alignItems="center">
                <Button variant="contained" onClick={handlePrevPage} sx={{mr: 1}}>Previous Page</Button>
                <Button variant="contained" onClick={handleNextPage}>Next Page</Button>
                <FormControl sx={{m: 1, minWidth: 120}} size="small">
                    <Select
                        labelId="demo-simple-select-label"
                        id="demo-simple-select"
                        value={sortBySelect}
                        label="SortBy"
                        onChange={handleChange}>
                        <MenuItem value={"status"}>Status</MenuItem>
                        <MenuItem value={"creation_date"}>Creation date</MenuItem>
                    </Select>
                </FormControl>
            </Box>
        </div>
    );

}

export default InterestedInOfferings;