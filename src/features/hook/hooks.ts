import {useEffect, useState} from "react";
import {JobListing} from "../../data/init-data";

export const useTask = (isLoggedIn: boolean) => {
    const [tasks, setTasks] = useState<JobListing[]>([]);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean | null>(false);

    useEffect(() => {
        if (isLoggedIn) {
            setLoading(true);
            fetchData();
        }
    }, [isLoggedIn]);

    const fetchData = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        let response = null;

        try {
            response = await fetch(`${backendUrl}/job-listing`);
        } catch (e: any) {
            setError(e.message);
            setTasks([]);
        }

        setLoading(false);
        if (response && response.ok) {
            const tasks = await response.json();
            setTasks(tasks);
        }
    };

    return {error, loading, tasks};
}