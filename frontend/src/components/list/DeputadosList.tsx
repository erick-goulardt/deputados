import { useEffect, useState } from "react";
import { IDeputado, IEvento } from "../interfaces/Interfaces";
import "./DeputadosList.style.css";
import { getEventos } from "../../services/eventos.service";
import { setDeputyOnEvent } from "../../services/deputado.service";
import Return from "../../assets/return.png"
import { useNavigate } from "react-router-dom";

interface ListaDeputadosProps {
  deputados: IDeputado[];
}

export function ListaDeputados({ deputados }: ListaDeputadosProps) {
  const [eventos, setEventos] = useState<IEvento[]>([]);
  const [eventoSelecionado, setEventoSelecionado] = useState<number | null>(
    null
  );
  const navigate = useNavigate();

  useEffect(() => {
    const fetchEventos = async () => {
      try {
        const response = await getEventos();
        setEventos(response?.data || []);
      } catch (error) {
        console.error("Erro ao buscar eventos: ", error);
      }
    };

    fetchEventos();
  }, []);

  const handleSelectChange = (eventoId: string) => {
    setEventoSelecionado(parseInt(eventoId, 10));
  };

  const handleAnexarEvento = async (deputadoId: number) => {
    try {
      if (eventoSelecionado !== null) {
        await setDeputyOnEvent({
          idDeputy: deputadoId,
          idEvent: eventoSelecionado,
        });

        console.log(`Evento anexado ao deputado ${deputadoId}`);
      } else {
        console.log("Nenhum evento selecionado");
      }
    } catch (error) {
      console.error("Erro ao anexar evento: ", error);
    }
  };

  return (
    <div>
      <div  className="text-header">
        <img src={Return} alt="" onClick={() => (navigate("/"))}/>
        <h2>Lista de Deputados</h2>
      </div>
      <div className="card-container">
        {deputados.map((deputado) => (
          <div key={deputado.id} className="card">
            <img src={deputado.urlPhoto} alt={`Foto de ${deputado.name}`} />
            <div className="container-infos">
              <strong className="info-deputado">{deputado.name}</strong>
              <span className="info-deputado">{deputado.acronymParty}</span>
              <select onChange={(e) => handleSelectChange(e.target.value)}>
                <option value="">Selecione um evento</option>
                {eventos.map((evento) => (
                  <option key={evento.id} value={evento.id}>
                    {evento.descType}
                  </option>
                ))}
              </select>
              <button onClick={() => handleAnexarEvento(deputado.id)}>
                Anexar Evento
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
