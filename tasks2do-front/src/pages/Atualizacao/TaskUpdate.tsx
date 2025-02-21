import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom"
import api from "../../services/axiosConfig";
import "../Atualizacao/TaskUpdate.css";

interface Task {
  id: number;
  nome: string;
  descricao: string
}

const TaskUpdate = () => {
  const navigate = useNavigate();

  const { id } = useParams<{ id: string }>();
  const [task, setTask] = useState<Task | null>(null);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchTask = async () => {
      try {
        const response = await api.get(`/tarefas/${id}`);
        setTask(response.data);
      } catch (error) {
        setError("Erro ao carregar a tarefa.");
      }
    }

    fetchTask();
  }, [id]);

  const token = localStorage.getItem("token");

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    if (task) {
      setTask({ ...task, [e.target.name]: e.target.value });
    }
  };

  const handleUpdate = async (e: React.FormEvent) => {
    e.preventDefault();

    if (!task?.nome || !task?.descricao) {
      setError("Todos os campos são obrigatórios!");
      return;
    }

    try {
      await api.put(`/tarefas/atualizar_tarefa/${id}`, task, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        }
      });
      alert("Tarefa atualizada com sucesso!");
      navigate("/minhas_tarefas")
    } catch (error) {
      setError("Erro ao atualizar a tarefa.");
    }
  };

  if (error) return <p className="error-message">{error}</p>;
  if (!task) return <p>Carregando...</p>;

  return (
    <div className="main-content">
      <div className="task-detail-card">
        <h1>Editar Tarefa</h1>

        <form onSubmit={handleUpdate}>
          
          <p>Nome da Tarefa:</p>
          <input id="task-input-name" type="text" name="nome" value={task.nome} onChange={handleChange} />

          <p>Descrição:</p>
          <textarea name="descricao" value={task.descricao} onChange={handleChange}></textarea>

          <div className="task-update-actions">
            <button type="submit" className="update-button">Atualizar</button>
            <button type="button" className="delete-button" onClick={() => navigate("/minhas_tarefas")}>Cancelar</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default TaskUpdate;
