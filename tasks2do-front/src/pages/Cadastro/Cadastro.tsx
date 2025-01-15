import React, { useState } from "react";
import "./Cadastro.css";
import api from "../../services/axiosConfig.tsx"

const Cadastro = () => {

  const [login, setLogin] = useState("");
  const [senha, setSenha] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e: React.FormEvent) => {
    e.preventDefault();

    try {
      const response = await api.post("/auth/cadastro", {
        login,
        senha,
      });
      
      const token = response.data.token;


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

            <button className='entrar' type="submit"> Cadastre-se </button>


        </form>
      </div>
    </div>
  )
}

export default Cadastro
