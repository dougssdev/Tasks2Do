import { useEffect, useState } from "react";
import "./CreateTasks.css"
import { useNavigate } from "react-router-dom";
import api from "../../services/axiosConfig";
import { StatusDaTarefa } from "./StatusDaTarefa";


const CreateTasks = () => {
    const navigate = useNavigate();

    const [taskData, setTaskData] = useState({
        nome: "",
        descricao: "",
        status: StatusDaTarefa.NaoIniciado,
    });

    const [error, setError] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | 
        HTMLTextAreaElement | HTMLSelectElement>) =>{
        const {name, value} = e.target;

        if (name === "status"){
          setTaskData({...taskData, [name]: StatusDaTarefa [value as keyof typeof StatusDaTarefa]});
        } else {
          setTaskData({...taskData, [name]: value})
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        if(!taskData.nome || !taskData.descricao){
            setError("Todos os campos são obrigatórios")
            return;
        }

        try{
            await api.post("/tarefas/nova_tarefa", taskData);
            alert("Tarefa criada com sucesso!");
            navigate("/minhas_tarefas");
        } catch (error){
            setError("Erro ao criar a tarefa. Tente novamente.")
        }
    };

    return (
        <div className="main-content">
          <h1>Criar Nova Tarefa</h1>
    
          {error && <p className="error-message">{error}</p>}
    
          <form className="create-tasks-form" onSubmit={handleSubmit}>
            <label className="title">Nome da Tarefa:</label>
            <input className="task-name" type="text" name="nome" value={taskData.nome} onChange={handleChange} placeholder="Digite o nome da tarefa" />
    
            <label className="title">Descrição:</label>
            <textarea name="descricao" value={taskData.descricao} onChange={handleChange} placeholder="Digite a descrição da tarefa"></textarea>
    
    
            <label className="title">Status:</label>
            <select className="status-task" name="status" value={taskData.status} onChange={handleChange} required >
              {Object.values(StatusDaTarefa).map((status) => (
                <option key={status} value={status}>{status.replace("_", " ")}</option>
              ))}
            </select>
    
            <div className="task-form-actions">
              <button type="submit" className="submit-button">Criar Tarefa</button>
            </div>
            <label><a className="cancel" href="/minhas_tarefas">Cancelar</a></label>
          </form>
        </div>
      );
    
}

export default CreateTasks;