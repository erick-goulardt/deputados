import { Route, Routes } from "react-router-dom";
import { DeputadosPage } from "../pages/deputados/DeputadosPage";
import { EventosPage } from "../pages/eventos/EventosPage";
import { HomePage } from "../pages/home/HomePage";

export function SiteRoutes() {
  return (
    <>
      <Routes>
        <Route path="/eventos" element={<EventosPage />} />
        <Route path="/deputados" element={<DeputadosPage />} />
        <Route path="/" element={<HomePage />} />
      </Routes>
    </>
  );
}
