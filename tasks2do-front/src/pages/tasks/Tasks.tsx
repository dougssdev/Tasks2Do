import "../tasks/Tasks.css";
import { useEffect, useState } from "react";
import api from "../../services/axiosConfig";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheckCircle, faCircleNotch, faTimesCircle } from "@fortawesome/free-solid-svg-icons";


interface Task {
  tarefas_id: number;
  nome: string;
  descricao: string;
  data_de_adicao: string;
  status_da_tarefa: "Não_Iniciado" | "Feita" | "Fazendo";
}

const getStatusIcon = (status: Task["status_da_tarefa"]) => {
  switch (status) {
    case "Feita":
      return <FontAwesomeIcon icon={faCheckCircle} color="green" />;
    case "Fazendo":
      return <FontAwesomeIcon icon={faCircleNotch} color="blue" />;
    case "Não_Iniciado":
      return <FontAwesomeIcon icon={faTimesCircle} color="red" />;
    default:
      return null;
  }
};

const Tasks = () => {
  const [tasks, setTasks] = useState<Task[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchTasks = async () => {
      try {
        setIsLoading(true);
        const response = await api.get("/tarefas/minhas_tarefas");
        setTasks(response.data);
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

  return (
    <div className="tasks-page">
      
       <div className='bar'>
        <div className='logo'>
        <img src="src\assets\TASKS__2_-removebg-preview.png" alt="Logo Tasks2Do"/>
        </div>
        <button className='logout-button'> Sair </button>
      </div>

      
      <main className="main-content">
        <h2>Minhas Tarefas</h2>

        {isLoading && <p>Carregando tarefas...</p>}
        {error && <p className="error-message">{error}</p>}

        <div className="tasks-container">
          {!isLoading && !error && tasks.length === 0 && (
            <p>Nenhuma tarefa encontrada.</p>
          )}
          {tasks.map((task) => (
            <div key={task.tarefas_id} className="task-card">
              <div className="status-icon">{getStatusIcon(task.status_da_tarefa)}</div>
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
        <button className="create-task-button">Criar Tarefa</button>
      </main>
    </div>
  );
};

export default Tasks;
