import { Routes, Route } from "react-router-dom";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Tasks from "../pages/Tasks";
import Cadastro from "../pages/Cadastro";
import TasksDetail from "../pages/TasksDetail";
import CreateTasks from "../pages/CreateTasks";
import TaskUpdate from "../pages/TaskUpdate";

const AppRoutes = () => {
    return (
        
          <Routes>
            <Route path='/' element={<Home />}/>
            <Route path='/login' element={<Login />} />
            <Route path='/minhas_tarefas' element={<Tasks />} />
            <Route path='/cadastro' element={<Cadastro />} />
            <Route path="/tarefas/:id" element={<TasksDetail />} />
            <Route path="/tarefas/nova_tarefa" element={<CreateTasks />}/>
            <Route path='/tarefas/atualizar_tarefa/:id' element={<TaskUpdate/>}></Route>
          </Routes>
      

    );
};

export default AppRoutes;