import { API } from "./Api";

export async function getEventos() {
    try {
      const response = await API.get(`/eventos`)
      return response;
    } catch (error) {
      console.error(error)
      console.log("Não houve retorno!")
    }
  }

export async function getDeputadosByEvento(id:number) {
  try {
    const response = await API.get(`/deputados/evento/${id}`)
    return response.data;
  } catch(error) {
    console.error(error)
    console.log("Sem retorno!")
  }
}