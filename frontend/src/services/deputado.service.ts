import { IDefineEvento } from "../components/interfaces/Interfaces";
import { API } from "./Api";

export async function getDeputados() {
    try {
      const response = await API.get(`/deputados`)
      return response;
    } catch (error) {
      console.error(error)
      console.log("Não houve retorno!")
    }
  }

  export async function setDeputyOnEvent({ idDeputy, idEvent }: IDefineEvento) {
    try {
      const response = await API.put('/deputados/define-evento', {
        idEvent,
        idDeputy,
      });
      return response.data; 
    } catch (error) {
      console.error('Erro ao chamar a API:', error);
      throw error; 
    }
  }