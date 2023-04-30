import {useEffect, useState} from "react";
import {Task} from "../data/init-data";
import TaskCard from "../component/TaskCard";
import {useParams} from "react-router";

const TaskDetail = () => {
    const {id} = useParams<'id'>();

    const [loading, setLoading] = useState<boolean>(true)
    const [data, setData] = useState<Task | undefined>();

    useEffect(() => {
        debugger;
        fetchData(id!!);
    }, []);

    const fetchData = async (id: number | string) => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;

        const result = await fetch(`${backendUrl}/task/${id}`);
        setData(await (result.json()));
        setLoading(false);
    };

    return <div>
        {loading && <div>Loading ...</div>}
        {data && <TaskCard task={data} onTaskDone={() => {}} />}
    </div>
};

export default TaskDetail;