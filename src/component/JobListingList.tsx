import {JobListing} from "../data/init-data";
import JobListingCard from "./JobListingCard";

interface Props {
    tasks: Array<JobListing>
}

const JobListingList = ({tasks} : Props) => {
    const taskDoneHandle = (task: JobListing) => {
    }
    //console.log(tasks);
    return <div>
        <h1>Jobs</h1>
        {tasks.map(t =>
            <JobListingCard key={t.id} task={t} onTaskDone={taskDoneHandle} />
        )}
    </div>
};

export default JobListingList;