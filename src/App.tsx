import './App.css'
import {Provider} from "react-redux";
import store from "./features/store";
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import JobListings from "./page/JobListings";
import Header from "./component/ui/Header";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import React from "react";
import JobOfferings from "./page/InterestedInOfferings";
import InterestedJobOfferingCard from "./component/InterestedJobOfferingCard";
import LoginPage from "./page/LoginPage";
import InterestedInOfferings from "./page/InterestedInOfferings";
import CreateJobListing from "./page/CreateJobListing";
import OwnedJobListings from "./page/OwnedJobListings";

const queryClient = new QueryClient();

function App() {
    
    return (
        <QueryClientProvider client={queryClient}>
            <Provider store={store}>
                <BrowserRouter>
                    <Header/>
                    <Routes>
                        <Route path={"/job-listings"} element={<JobListings/>}/>
                        <Route path={"/login"} element={<LoginPage/>}/>
                        <Route path={"/job-listing/create"} element={<CreateJobListing/>}/>
                        <Route path={"/job-interests"} element={<InterestedInOfferings/>}/>
                        <Route path={"/job-listings/owned"} element={<OwnedJobListings/>}/>
                    </Routes>
                </BrowserRouter>
            </Provider>
        </QueryClientProvider>
    )
}

export default App
