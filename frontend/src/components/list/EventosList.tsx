import { IListEvento } from "../interfaces/Interfaces";
import { useNavigate } from "react-router-dom";
import Return from "../../assets/return.png"
import "./EventosList.style.css"
import Logo from "../../assets/ciencia-politica.png"
import { useState } from "react";
import Modal from "../modals/Modal";

interface ListaEventosProps {
    eventos: IListEvento[]
}

export function ListaEventos({eventos} : ListaEventosProps) {
    const navigate = useNavigate();
    const [selectedEvento, setSelectedEvento] = useState<IListEvento | null>(null);

    const openModal = (evento: IListEvento) => {
        setSelectedEvento(evento);
      };
    
      const closeModal = () => {
        setSelectedEvento(null);
      };

    const formatarData = (data : Date) => {
        const dataFormatada = new Date(data).toLocaleDateString();
        const horarioFormatado = new Date(data).toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
    
        return { data: dataFormatada, horario: horarioFormatado };
      };

return (
    <div>
      <div className="header-events">
        <img src={Return} alt="" onClick={() => (navigate("/"))}/>
        <h2>Lista de Eventos</h2>
      </div>
      <div className="event-container">
        {eventos.map((evento) => (
          <div key={evento.id} className="card">
            <img src={Logo} alt="" />
            <p>Data de Início: {formatarData(evento.dateInicio).data}</p>
            <p>Horário de Início: {formatarData(evento.dateInicio).horario}</p>
            <p>Situação: {evento.situation}</p>
              <button onClick={() => openModal(evento)}>
                Informações
              </button>
            </div>
        ))}
      </div>
      {selectedEvento && (
        <Modal evento={selectedEvento} onClose={closeModal} />
      )}
    </div>
  );
}