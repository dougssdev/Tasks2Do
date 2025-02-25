import { useNavigate } from "react-router-dom";
import "../styles/Home.css"
import "../styles/Login.css"
import mockups from "../assets/Tarefas-teste.png"
import { useEffect } from "react";

const Home = () =>{

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (token){
            navigate("/minhas_tarefas");
        }
    })

    return (
        <div className="main-content">
           <div className="home-content">
           <h1> Bem vindo ao Tasks2Do! </h1>
            <p>Com Tasks2Do, você pode organizar suas tarefas de maneira fácil e prática com apenas alguns cliques!</p>

                <div className="tasks-mockups">
                    <img className="mockups" src={mockups} alt="Representação das tarefas do usuário."></img>
                </div>
                
                <button className='signup-button'> <a href="/cadastro" className="cadastro-link"> Cadastre-se</a>  </button>
            
            <label>Já tem uma conta? </label> <a href="/login" className='signin-link'> Entrar </a>
           </div>
        </div>
    )
}

export default Home;