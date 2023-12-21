import { useState, useEffect } from "react";
import { IListEvento } from "../../components/interfaces/Interfaces";
import { getEventos } from "../../services/eventos.service";
import { ListaEventos } from "../../components/list/EventosList";
import "./Eventos.style.css"

export function EventosPage() {

  const [eventos, setEventos] = useState<IListEvento[]>([]);

  useEffect(() => {
    const loadEventos = async () => {
      try {
        const response = await getEventos();
        setEventos(response?.data);
      } catch (error) {
        console.log("Erro ao buscar deputados: ", error);
      }
    };
    loadEventos();
  }, []);

  return (
    <div className="eventos-container">
        <ListaEventos eventos={eventos} />
    </div>
  );
}
