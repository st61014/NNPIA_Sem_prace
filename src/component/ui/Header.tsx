import React, {useEffect} from "react";
import './Header.css';
import {useSelector} from "react-redux";
import {setLogin} from "../../features/login/loginSlice";
import {useAppDispatch} from "../../features/hook";
import {RootState} from "../../features/store";

const Header = () => {
    const isLoggedIn = useSelector((state: RootState) => state.login.value);
    const dispatch = useAppDispatch();

    useEffect(()=> {
        console.log(`State changed in ${Header.name}: ${isLoggedIn}`);
    }, [isLoggedIn]);

    const clickHandle = (e: React.MouseEvent<HTMLElement>) => {
        e.preventDefault();
        dispatch(setLogin(true));
    };

    return <div className="header">
        <button onClick={clickHandle}>Přihlásit se</button>
    </div>
};

export default Header;