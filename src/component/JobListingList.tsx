import {JobListing} from "../data/init-data";
import JobListingCard from "./JobListingCard";

interface Props {
    tasks: Array<JobListing>
}

const JobListingList = ({tasks} : Props) => {
    const taskDoneHandle = (task: JobListing) => {
    }

    return <div>
        <h1>Aktuální tasky</h1>
        {tasks.filter(t => !t.position).map(t =>
            <JobListingCard key={t.id} task={t} onTaskDone={taskDoneHandle} />
        )}
        <h1>Splněné tasky</h1>
        {tasks.filter(t => t.position).map(t =>
            <JobListingCard key={t.id} task={t} onTaskDone={taskDoneHandle} />
        )}
    </div>
};

export default JobListingList;