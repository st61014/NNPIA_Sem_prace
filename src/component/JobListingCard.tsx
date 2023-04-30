import {JobListing} from "../data/init-data";
import React from "react";

interface Props {
    task: JobListing
    onTaskDone: (task: JobListing) => void
}

const JobListingCard = ({task, onTaskDone} : Props) => {
    const doneClickHandle = (e: React.ChangeEvent<HTMLInputElement>) => {
        task.position = "e.target.checked";

        onTaskDone(task);
        console.table(task);
    };
    console.log(task);
    return <div>
        <h2>{task.position}</h2>
        <p>{task.jobField}</p>
        <p>{task.pay}</p>
        <p>{task.listingPosterId}</p>
    </div>
}

export default JobListingCard;