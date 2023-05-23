import React, {useEffect, useState} from "react";
import './Header.css';
import {useSelector} from "react-redux";
import {setLogin} from "../../features/login/loginSlice";
import {useAppDispatch} from "../../features/hook";
import {RootState} from "../../features/store";
import {Link as RouterLink} from "react-router-dom";
import {AppBar, Box, Button, Container, IconButton, Link, Stack, Toolbar, Typography} from "@mui/material";
import {useNavigate} from "react-router-dom";
import Avatar from '@mui/material/Avatar';
import Tooltip from '@mui/material/Tooltip';
import MenuItem from '@mui/material/MenuItem';
import Menu from '@mui/material/Menu';

const Header = () => {
    const dispatch = useAppDispatch();
    const [isLoggedInO, setIsLoggedIn] = useState(localStorage.getItem('token') && localStorage.getItem('loginFlag') === 'true');
    const isLoggedIn = useSelector((state: RootState) => state.login.value);
    const navigate = useNavigate();

    const handleLogout = () => {
        localStorage.removeItem('token');
        localStorage.removeItem('loginFlag');
        localStorage.removeItem('bearer');
        localStorage.removeItem("loggedUser");
        //setIsLoggedIn(false);
        dispatch(setLogin(false));
        navigate("/login")
    };
    const linkStyle = {
        textDecoration: "none",
        color: 'white'
    };


    if (isLoggedIn) {
        return (
            <AppBar position="static">
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                            <Button
                                key="joblistings"
                                onClick={()=>{navigate("job-listings")}}
                                sx={{my: 2, color: 'white', display: 'block'}}>
                                Job listings
                            </Button>
                            <Button
                                key="ownedjoblistings"
                                onClick={()=>{navigate("job-listings/owned")}}
                                sx={{my: 2, color: 'white', display: 'block'}}>
                                My job listings
                            </Button>
                            <Button
                                key="choseninterests"
                                onClick={()=>{navigate("job-interests")}}
                                sx={{my: 2, color: 'white', display: 'block'}}>
                                My interests
                            </Button>
                            <Button
                                key="createlisting"
                                onClick={()=>{navigate("job-listing/create")}}
                                sx={{my: 2, color: 'white', display: 'block'}}>
                                Create new job listing
                            </Button>

                        </Box>
                        <Typography textAlign="center">Logged in as: {localStorage.getItem("loggedUser")}</Typography>
                        <Box sx={{flexGrow: 0}}>

                            <MenuItem key="logout" onClick={handleLogout}>
                                <Typography textAlign="center">Log out</Typography>
                            </MenuItem>
                        </Box>
                    </Toolbar>
                </Container>
            </AppBar>
        );
    }else{
        return (
            <AppBar position="static">
                <Container maxWidth="xl">
                    <Toolbar disableGutters>
                        <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                            <Button
                                key="joblistings"
                                onClick={()=>{navigate("job-listings")}}
                                sx={{my: 2, color: 'white', display: 'block'}}>
                                Job listings
                            </Button>
                        </Box>

                        <Box sx={{flexGrow: 0}}>
                            <MenuItem key="login" onClick={()=>{navigate("login")}}>
                                <Typography textAlign="center">Log in</Typography>
                            </MenuItem>
                        </Box>
                    </Toolbar>
                </Container>
            </AppBar>
        );
    }



};

export default Header;