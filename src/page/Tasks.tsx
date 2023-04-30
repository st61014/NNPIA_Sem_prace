import TaskList from "../component/TaskList";
import {Task} from "../data/init-data";
import {useEffect, useState} from "react";
import './Tasks.css';
import {useSelector} from "react-redux";
import {RootState} from "../features/store";
import {useTask} from "../features/hook/hooks";
import {useQuery} from "@tanstack/react-query";
import {queryKey} from "@tanstack/react-query/build/lib/__tests__/utils";
import TaskForm from "../component/TaskForm";

const Tasks = () => {
    const fetchData = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        let response = null;

        response = await fetch(`${backendUrl}/task`);

        return await response.json();
    };

    //const isLoggedIn = useSelector((state: RootState) => state.login.value);
    const {isLoading, data, isError, error} = useQuery({queryKey: ['tasks'], queryFn: fetchData})
    //const {error, loading, tasks}=useTask(isLoggedIn);

    if (isLoading){
        return <div className="alert alert-danger">loading</div>
    }

    return <div className="tasks">
        {isError && <div className="alert alert-danger">{JSON.stringify(error)}</div>}
        <TaskList tasks={data}/>
        <TaskForm/>
    </div>
};

export default Tasks;