import {FaUser, FaLock} from 'react-icons/fa';

const Login = () => {
  return (
    <div>
      <div className="container">
        <form>
          <h1>Login de Usuário</h1>
            <div>
                <p>Nome de usuário: </p>
                <input name = 'username' type="text" placeholder="Nome de usuário"></input>
                <FaUser className='icon'/>
            </div>
            <div>
                <p>Senha: </p>
                <input name= 'password' type="password" placeholder="Sua senha"></input>
                <FaLock className='icon'/>
            </div>

            <div className='recall-forget'>
            <label>
              <input type='checkbox'></input>
              Lembrar do usuário
            </label>
            </div>

            <button> Entrar </button>

            <div className='signup-link'>
                <p>É novo por aqui? <a href='#'> Cadastre-se </a></p>
            </div>

        </form>
      </div>
    </div>
  )
}

export default Login
