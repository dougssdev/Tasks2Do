import "../styles/Tasks.css";
import { useEffect, useState } from "react";
import api from "../services/axiosConfig";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheckCircle, faEllipsisH, faCircleMinus } from "@fortawesome/free-solid-svg-icons";
import { useNavigate } from "react-router-dom";


interface Task {
  id: number;
  nome: string;
  descricao: string;
  data_de_adicao: string;
  statusDaTarefa: "Não_Iniciado" | "Feita" | "Fazendo";
}

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

const Tasks = () => {

  const [tasks, setTasks] = useState<Task[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const navigate = useNavigate();


  useEffect(() => {
    const fetchTasks = async () => {
      try {
        setIsLoading(true);
        const response = await api.get("/tarefas/minhas_tarefas");
        setTasks(response.data);
        console.log(response.data);
        setError(null);
      } catch (error) {
        console.error("Erro ao buscar tarefas:", error);
        setError("Você não tem nenhuma tarefa ou ocorreu um erro.");
      } finally {
        setIsLoading(false);
      }
    };

    fetchTasks();
  }, []);

  const createTask = () => {
    navigate(`/tarefas/nova_tarefa`)
  }

  return (
    <div className="tasks-page">
      <main className="main-content">
        <h1>Minhas Tarefas</h1>

        {isLoading && <p>Carregando tarefas...</p>}
        {error && <p className="error-message">{error}</p>}

        <div className="tasks-container">
          {!isLoading && !error && tasks.length === 0 && (
            <p>Você ainda não possui tarefas. </p>
          )}
          {tasks.map((task) => (
            <div key={task.id}
              className="task-card"
              onClick={() => navigate(`/tarefas/${task.id}`)}
            >
              <div className="status-icon">{getStatusIcon(task.statusDaTarefa)}</div>
              <p>
                <p className="nome">Nome: {task.nome}</p>
              </p>
              <p>
                <strong>Descrição:</strong> {task.descricao}
              </p>
              <p>
                <strong>Data:</strong> {task.data_de_adicao}
              </p>
            </div>
          ))}
        </div>
        <button className="create-task-button" onClick={createTask}>Criar Tarefa</button>
      </main>
    </div>
  );
};

export default Tasks;
