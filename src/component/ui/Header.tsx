import React, {useEffect, useState} from "react";
import './Header.css';
import {useSelector} from "react-redux";
import {setLogin} from "../../features/login/loginSlice";
import {useAppDispatch} from "../../features/hook";
import {RootState} from "../../features/store";
import {Link as RouterLink} from "react-router-dom";
import {Button, Link, Stack} from "@mui/material";

const Header = () => {
    //const isLoggedIn = useSelector((state: RootState) => state.login.value);
    const [isLoggedIn, setIsLoggedIn] = useState(localStorage.getItem('token') && localStorage.getItem('loginFlag') === 'true');
    //const dispatch = useAppDispatch();
    const handleLogout = () => {
        // Perform any necessary logout logic (e.g., clearing local storage, resetting state)
        localStorage.removeItem('token');
        localStorage.removeItem('login');
        localStorage.removeItem('bearer');
        setIsLoggedIn(false);
    };
    const linkStyle = {
        textDecoration: "none",
        color: 'white'
    };
    return <div className="header">
        <Stack spacing={2} direction="row">
            <Button variant="contained"><RouterLink to="job-listings" style={linkStyle}>Job
                listings</RouterLink></Button>
            <Button variant="contained"><RouterLink to="job-interests" style={linkStyle}>My
                interests</RouterLink></Button>
            <Button variant="contained"><RouterLink to="job-listing/create" style={linkStyle}>Create new job listing</RouterLink></Button>
            <Button variant="contained"><RouterLink to="login" style={linkStyle}>Log In</RouterLink></Button>
            <Button onClick={handleLogout} variant="contained">Log out</Button>
        </Stack>
    </div>
};

export default Header;