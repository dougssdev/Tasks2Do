import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheckCircle, faEllipsisH, faCircleMinus } from "@fortawesome/free-solid-svg-icons";
import api from "../services/axiosConfig";
import "../styles/TasksDetail.css"


interface Task {
  id: number;
  nome: string;
  descricao: string;
  data_de_adicao: string;
  statusDaTarefa: "Não_Iniciado" | "Feita" | "Fazendo";
}


const TasksDetail = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const [task, setTask] = useState<Task | null>(null)
  const [error, setError] = useState("");

  const token = localStorage.getItem("token");

  useEffect(() => {
    const fetchTask = async () => {
      try {
        const response = await api.get(`/tarefas/${id}`);
        setTask(response.data);

      } catch (error) {
        setError("Erro ao carregar a tarefa.")
      }
    };

    fetchTask();

  }, [id])


  const handleDelete = async () => {
    try {
      await api.delete(`/tarefas/excluir/${id}`);
      alert("Tarefa deletada")
      navigate("/minhas_tarefas")
    } catch (error) {
      alert("Erro ao deletar tarefa, tente novamente.")
    }
  }

  const handleUpdate = () => {

    navigate(`/tarefas/atualizar_tarefa/${id}`);
  };


  const getStatusIcon = (status: Task["statusDaTarefa"]) => {
    switch (status) {
      case "Feita":
        return <FontAwesomeIcon icon={faCheckCircle} color="green" />;
      case "Fazendo":
        return <FontAwesomeIcon icon={faEllipsisH} color="blue" />;
      case "Não_Iniciado":
        return <FontAwesomeIcon icon={faCircleMinus} color="gray" />;
      default:
        return null;
    }
  };

  const updateStatus = async (novoStatus: "Não_Iniciado" | "Feita" | "Fazendo") => {
    if (!task) return;

    try {
      let endpoint = "";

      if(novoStatus === "Não_Iniciado"){
        endpoint = `/tarefas/status/nao_iniciada/${id}`
      }
      else if (novoStatus === "Feita"){
        endpoint = `/tarefas/status/feita/${id}`
      }
      else if (novoStatus === "Fazendo"){
        endpoint = `/tarefas/status/fazendo/${id}`
      }

      await api.put(endpoint, {}, {headers: {Authorization: `Bearer ${token}`}});

      setTask({...task, statusDaTarefa: novoStatus})
      alert(`Status atualizado para: ${novoStatus}`);
    } catch (error) {
      alert("Erro ao atualizar status.")
    }
  };

  if (error) return <p>{error}</p>;
  if (!task) return <p>Carregando...</p>;

  return (

    <div className="main-content">

      <div className="task-detail-card">
        <h1>{task.nome}</h1>
        <p><strong>Descrição:</strong></p>
        <div className="textarea">{task.descricao}</div>
        
        <div className="data-status">
          <p><strong>Data:</strong> {task.data_de_adicao} </p>  
          <p><strong>Status:</strong> {task.statusDaTarefa}</p>
        </div>

        <select 
          value={task.statusDaTarefa}
          onChange={(e) => updateStatus(e.target.value as "Não_Iniciado" | "Fazendo" | "Feita")}
          className="status-dropdown">
            <option value="Não_Iniciado"> Não Iniciado </option>
            <option value="Fazendo"> Fazendo </option>
            <option value="Feita"> Feita </option>
          </select>

          <span className="task-status"> {getStatusIcon(task.statusDaTarefa)} </span>

        <div className="task-detail-actions">
          <button className="update-button" onClick={handleUpdate}>Atualizar</button>
          <button className="delete-button" onClick={handleDelete}> Deletar</button>
        </div>

      </div>
    </div>

  );

}

export default TasksDetail;