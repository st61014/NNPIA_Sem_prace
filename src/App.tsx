import './App.css'
import {Provider} from "react-redux";
import store from "./features/store";
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import JobListings from "./page/JobListings";
import Header from "./component/ui/Header";
import JobListingDetail from "./page/JobListingDetail";
import {QueryClient, QueryClientProvider} from "@tanstack/react-query";
import React from "react";
import JobOfferings from "./page/JobOfferings";
import InterestedJobOfferingCard from "./component/InterestedJobOfferingCard";
import InterestedJobOfferingDetail from "./page/InterestedJobOfferingDetail";

const queryClient = new QueryClient();
function App() {
    
    return (
        <QueryClientProvider client={queryClient}>
            <Provider store={store}>
                <BrowserRouter>
                    <Header/>
                    <Routes>
                        <Route path={"/job-listings"} element={<JobListings/>}/>
                        <Route path={"/job-offerings"} element={<JobOfferings/>}/>
                        <Route path={"/job-listing/:id"} element={<JobListingDetail/>}/>
                        <Route path={"/job-interest/:id"} element={<InterestedJobOfferingDetail/>}/>
                    </Routes>
                </BrowserRouter>
            </Provider>
        </QueryClientProvider>
    )
}

export default App
