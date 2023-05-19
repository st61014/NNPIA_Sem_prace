import React, {ChangeEvent, useState} from "react";
import {setLogin} from "../features/login/loginSlice";
import {useAppDispatch} from "../features/hook";

const LoginPage = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const dispatch = useAppDispatch();
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
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                const { accessToken, tokenType, userID } = await response.json();
                localStorage.setItem("token", accessToken);
                localStorage.setItem("bearer", tokenType);
                dispatch(setLogin(true));
                // Redirect or perform any other necessary action upon successful login
            } else {
                // Handle login error
            }
        } catch (error) {
            console.error("Error occurred during login:", error);
        }
    };

    return (
        <div>
            <h1>Login Page</h1>
    <div>
    <label>Username:</label>
    <input type="text" value={username} onChange={handleUsernameChange} />
    </div>
    <div>
    <label>Password:</label>
    <input
    type="password"
    value={password}
    onChange={handlePasswordChange}
    />
    </div>
    <button onClick={handleLogin}>Login</button>
        </div>
);
};

export default LoginPage;
