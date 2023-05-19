import {FormEvent, useState} from "react";
import {JobListing} from "../data/init-data";


const CreateJobListing = () => {
    const [jobField, setJobField] = useState('');
    const [pay, setPay] = useState(0);
    const [position, setPosition] = useState('');
    const id = -1;
    const listingPosterId = -1;

    const handleSubmit = async (e: FormEvent) => {
        e.preventDefault();

        // Create a new job listing object
        const jobListing: JobListing = {
            id,
            jobField,
            pay,
            position,
            listingPosterId
        };

        try {
            const backendUrl = import.meta.env.VITE_BACKEND_URL;
            // Make a POST request to the backend endpoint
            const response = await fetch(`${backendUrl}/job-listing/create`, {
                method: 'POST',
                headers: {
                    Authorization: `Bearer ${localStorage.getItem("token")}`,
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(jobListing)
            });

            // Check the response status
            if (response.ok) {
                // Handle success
                console.log('Job listing created successfully!');
                // Clear the form fields
                setJobField('');
                setPay(0);
                setPosition('');
            } else {
                // Handle error
                console.error('Failed to create job listing');
            }
        } catch (error) {
            // Handle network or other errors
            console.error('An error occurred', error);
        }


        // Clear the form fields
        setJobField('');
        setPay('');
        setPosition('');
    };

    return (
        <div>
            <h1>Create Job Listing</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Job Field:</label>
                    <input
                        type="text"
                        value={jobField}
                        onChange={(e) => setJobField(e.target.value)}
                    />
                </div>
                <div>
                    <label>Pay:</label>
                    <input
                        type="text"
                        value={pay}
                        onChange={(e) => setPay(e.target.value)}
                    />
                </div>
                <div>
                    <label>Position:</label>
                    <input
                        type="text"
                        value={position}
                        onChange={(e) => setPosition(e.target.value)}
                    />
                </div>
                <button type="submit">Create</button>
            </form>
        </div>
    );
};

export default CreateJobListing;