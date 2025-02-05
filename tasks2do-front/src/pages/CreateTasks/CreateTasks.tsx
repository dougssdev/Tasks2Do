import { useEffect, useState } from "react";
import "./CreateTasks.css"
import { useNavigate } from "react-router-dom";
import api from "../../services/axiosConfig";


const CreateTasks = () => {
    const navigate = useNavigate();

    const [taskData, setTaskData] = useState({
        nome: "",
        descricao: "",
        statusDaTarefa: "Não_Iniciado",
    });

    const [error, setError] = useState("");

    const handleChange = (e: React.ChangeEvent<HTMLInputElement | 
        HTMLTextAreaElement | HTMLSelectElement>) =>{
        setTaskData({...taskData, [e.target.name]: e.target.value});

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
            <select className="status-task" name="statusDaTarefa" value={taskData.statusDaTarefa} onChange={handleChange}>
              <option value="Não_Iniciado">Não Iniciado</option>
              <option value="Fazendo">Fazendo</option>
              <option value="Feita">Feita</option>
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