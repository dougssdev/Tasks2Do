
import './App.css'
import Login from '../Login/Login.tsx'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Tasks from '../tasks/Tasks.tsx'
import Cadastro from '../Cadastro/Cadastro.tsx'

function App () {

  return (
    
    <div className='App'>
      
      <BrowserRouter>
        <Routes>
          <Route path='/login' element={<Login />}/>
          <Route path='/minhas_tarefas' element={<Tasks />}/>
          <Route path='/cadastro' element={<Cadastro />}/>
        </Routes>
      </BrowserRouter>
    
    </div>
  
  )
}

export default App
