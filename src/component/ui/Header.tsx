import React, {useEffect} from "react";
import './Header.css';
import {useSelector} from "react-redux";
import {setLogin} from "../../features/login/loginSlice";
import {useAppDispatch} from "../../features/hook";
import {RootState} from "../../features/store";
import {Link as RouterLink} from "react-router-dom";
import {Button, Link, Stack} from "@mui/material";

const Header = () => {
    const isLoggedIn = useSelector((state: RootState) => state.login.value);
    const dispatch = useAppDispatch();

    useEffect(() => {
        console.log(`State changed in ${Header.name}: ${isLoggedIn}`);
    }, [isLoggedIn]);

    const clickHandle = (e: React.MouseEvent<HTMLElement>) => {
        e.preventDefault();
        dispatch(setLogin(true));
    };
    const linkStyle = {
        textDecoration: "none",
        color: 'white'
    };
    return <div className="header">
        <Stack spacing={2} direction="row">
            <Button variant="contained" onClick={clickHandle}>Log in</Button>
            <Button variant="contained"><RouterLink to="job-listings" style={linkStyle}>Job
                listings</RouterLink></Button>
            <Button variant="contained"><RouterLink to="job-interest/1" style={linkStyle}>My
                interests</RouterLink></Button>
        </Stack>
    </div>
};

export default Header;