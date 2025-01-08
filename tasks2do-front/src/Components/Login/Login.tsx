
import "./Login.css";


const Login = () => {

 
  return (
    
    <div>

      <div className='bar'>
        <div className='logo'>
        <img src="src\assets\TASKS__2_-removebg-preview.png" alt="Logo Tasks2Do"/>
        </div>
      </div>

      <div className="container">
        <form>
            <div className='username'>
                <h2>Nome de usuário: </h2>
                <input id = 'username' name = 'username' type="text" placeholder="Nome de usuário"></input>
               
            </div>
            <div className='password'>
                <h2>Senha: </h2>
                <input id= 'password' name= 'password' type="password" placeholder="Sua senha"></input>
              
            </div>

            <div className='recall-forget'>
            <label>
              <input type='checkbox'></input>
              Lembrar do usuário
            </label>
            </div>

            <button className='entrar'> Entrar </button>

            <div className='signup-link'>
                <p>É novo por aqui? <a href='#'> Cadastre-se </a></p>
            </div>

        </form>
      </div>
    </div>
  )
}

export default Login
