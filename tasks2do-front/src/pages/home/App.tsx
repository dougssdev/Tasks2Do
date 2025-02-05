
import './App.css'
import Login from '../Login/Login.tsx'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Tasks from '../tasks/Tasks.tsx'
import Cadastro from '../Cadastro/Cadastro.tsx'
import TasksDetail from '../TasksDetail/TasksDetail.tsx'
import Header from '../Components/Cabecalho.tsx'
import '../Components/Style/Global.css'
import CreateTasks from '../CreateTasks/CreateTasks.tsx'

function App() {

  return (

    <div className='App'>


      <BrowserRouter>
        <Header />
        <Routes>
          <Route path='/login' element={<Login />} />
          <Route path='/minhas_tarefas' element={<Tasks />} />
          <Route path='/cadastro' element={<Cadastro />} />
          <Route path="/tarefas/:id" element={<TasksDetail />} />
          <Route path="/tarefas/nova_tarefa" element={<CreateTasks />}/>
        </Routes>
      </BrowserRouter>

    </div>

  )
}

export default App
