import { useEffect, useState } from "react";
import { IDeputado, IListEvento } from "../interfaces/Interfaces";
import "./Modal.style.css";
import { getDeputadosByEvento } from "../../services/eventos.service";

interface ModalProps {
  evento: IListEvento;
  onClose: () => void;
}

export function Modal({ evento, onClose }: ModalProps) {
  const [deputados, setDeputados] = useState<IDeputado[]>([]);

  useEffect(() => {
    const loadDeputados = async () => {
      try {
        const response = await getDeputadosByEvento(evento.id);
        setDeputados(response);
      } catch (error) {
        console.log("Erro ao buscar deputados: ", error);
      }
    };
    loadDeputados();
  }, [evento.id]);

  if (!evento) {
    return null;
  }

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>{evento.situation}</h2>
        <p>Descrição: {evento.description}</p>
        <p>
          Participantes:{" "}
          {deputados.length > 0
            ? deputados.map((deputado) => deputado.name).join(", ")
            : "Não há participantes"}
        </p>
        <button onClick={onClose}>Fechar</button>
      </div>
    </div>
  );
}

export default Modal;
