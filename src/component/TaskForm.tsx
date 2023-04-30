import { useForm } from "react-hook-form";
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from "yup";

const resolver = yupResolver(yup.object({
    "title": yup.string().max(128, "Max delka 128 znaku").required("Povinne pole")
}));

interface FormValues{
    title: String
}
const TaskForm = () => {

    const {register, handleSubmit, formState:{errors}} = useForm<FormValues>({resolver})

    const submitHandle = (data: FormValues) =>{
        console.table(data);
    }

    return <>
        <h1>Nový task</h1>
        <form onSubmit={handleSubmit(submitHandle)}>
            <input {...register("title")}/>
            {errors.title && <p>{errors.title.message}</p>}
            <button type="submit">Odeslat</button>
        </form>
    </>
}
export default TaskForm;