import "../styles/Header.css"
import { NavLink } from "react-router-dom"
import logo from "../../src/assets/Task2DoLogo.png"
import { useEffect, useState } from "react";

const Header = () => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    useEffect(() => {
        const token = localStorage.getItem("token");
        setIsLoggedIn(!!token);
    }, []);

    const handleLogout = () => {
        localStorage.removeItem("token");
        setIsLoggedIn(false);
    }

    return (

        <div className='bar'>
            <div className="logo-div">
                <NavLink to = "/" className={"logo-link"}>
                    <img className='logo' src={logo} alt="Logo Tasks2Do" />
                </NavLink>
            </div>
            {isLoggedIn && (
                <NavLink to="/login" className="logout-button" onClick={handleLogout}>
                    Sair
                </NavLink>
            )}
        </div>

    )

}
export default Header;