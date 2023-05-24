import React, {ChangeEvent, useState} from "react";
import {setLogin} from "../features/login/loginSlice";
import {useAppDispatch} from "../features/hook";
import {useNavigate} from "react-router-dom";
import {
    Box, Button,
    Checkbox,
    Container,
    CssBaseline,
    FormControlLabel, Grid,
    TextField,
    ThemeProvider,
    Typography
} from "@mui/material";
import Avatar from "@mui/material/Avatar";

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useAppDispatch();
    const navigate = useNavigate();
    const handleUsernameChange = (event: ChangeEvent<HTMLInputElement>) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event: ChangeEvent<HTMLInputElement>) => {
        setPassword(event.target.value);
    };

    const handleLogin = async () => {
        const backendUrl = import.meta.env.VITE_BACKEND_URL;
        try {
            const response = await fetch(`${backendUrl}/auth/signin`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({username, password})
            });

            if (response.ok) {
                const {accessToken, tokenType, userID} = await response.json();
                localStorage.setItem("token", accessToken);
                localStorage.setItem("bearer", tokenType);
                localStorage.setItem("loginFlag", "true");
                localStorage.setItem("loggedUser", username);
                dispatch(setLogin(true));
                navigate("/job-listings")
            } else {
            }
        } catch (error) {
            console.error("Error occurred during login:", error);
        }
    };


    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}>
                <Typography component="h1" variant="h5">
                    Sign in
                </Typography>
                <Box sx={{mt: 1}}>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        label="Username"
                        name="username"
                        onChange={handleUsernameChange}
                        autoFocus
                    />
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        onChange={handlePasswordChange}
                        autoComplete="current-password"
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        onClick={handleLogin}
                        sx={{mt: 3, mb: 2}}>
                        Sign In
                    </Button>
                </Box>
            </Box>
        </Container>
    );

};

export default LoginPage;
