import { useEffect, useState } from "react";
import { IDeputado } from "../../components/interfaces/Interfaces";
import "./Deputados.style.css";
import { getDeputados } from "../../services/deputado.service";
import { ListaDeputados } from "../../components/list/DeputadosList";
export function DeputadosPage() {
  const [deputados, setDeputados] = useState<IDeputado[]>([]);

  useEffect(() => {
    const loadDeputados = async () => {
      try {
        const response = await getDeputados();
        setDeputados(response?.data);
      } catch (error) {
        console.log("Erro ao buscar deputados: ", error);
      }
    };
    loadDeputados();
  }, []);

  return (
    <div className="deputados-container">
        <ListaDeputados deputados={deputados} />
    </div>
  );
}
