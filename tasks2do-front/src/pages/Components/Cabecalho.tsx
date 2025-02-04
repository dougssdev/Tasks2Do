import "../Components/Style/Header.css"
import { useNavigate } from "react-router-dom"

const Header = () => {
    const navigate = useNavigate();

    const handleLogout = () => {

        localStorage.removeItem("token");

        navigate("/login");
    };
    return (

        <div className='bar'>
            <div className="logo-div">
                <img className= 'logo' src="src\assets\Task2DoLogo.png" alt="Logo Tasks2Do" />
            </div>
            <button className='logout-button' onClick={handleLogout}> Sair </button>
        </div>

    )

}
export default Header;