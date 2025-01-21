import React, { useEffect, useState } from "react";
import "./Login.css";
import api from "../../services/axiosConfig.tsx"

const Login = () => {

  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [rememberMe, setRememberMe] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    const savedLogin = localStorage.getItem("savedLogin");
    if(savedLogin){
      setLogin(savedLogin);
      setRememberMe (true);
    }
  }, []);
  


  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await api.post("/auth/login", {
        login,
        senha,
      });
      
      const token = response.data.token;

      if(rememberMe){
        localStorage.setItem("savedLogin", login);
      } else {
        localStorage.removeItem("savedLogin");
      }


      localStorage.setItem("token", token);
      window.location.href = "/minhas_tarefas"
      console.log(response)
    } catch (err: any) {
      setError("Credenciais inválidas. Tente novamente.");
    }
  };

 
  return (
    
    <div>

      <div className='bar'>
        <div className='logo'>
        <img src="src\assets\TASKS__2_-removebg-preview.png" alt="Logo Tasks2Do"/>
        </div>
      </div>

      <div className="container">
        <form onSubmit={handleLogin}>
            <div className='login
            '>
                <h2>Nome de usuário: </h2>
                <input id = 'login' 
                name = 'login' 
                type="text" placeholder="Nome de usuário"
                value={login }
                onChange={(e) => setLogin
                  (e.target.value)}
                ></input>
               
            </div>
            <div className='senha'>
                <h2>Senha: </h2>
                <input id= 'senha'
                 name= 'senha' 
                 type="password" 
                 placeholder="Sua senha"
                 value={senha}
                onChange={(e) => setSenha
                (e.target.value)}></input>
            </div>

            <div className='recall-forget'>
            <label>
              <input type='checkbox'></input>
              Lembrar do usuário
            </label>
            </div>

            {error && <p className="error">{error}</p>}

            <button className='entrar' type="submit"> Entrar </button>

            <div className='signup-link'>
                <p>É novo por aqui? <a href='/cadastro'> Cadastre-se </a></p>
            </div>

        </form>
      </div>
    </div>
  )
}

export default Login
