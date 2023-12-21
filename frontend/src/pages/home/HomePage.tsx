import { Button } from "../../components/button/Button";
import "./Home.style.css";
import { useNavigate } from "react-router-dom"

export function HomePage() {

  const navigate = useNavigate();

  return (
    <div className="home-container">
      <div className="content-container">
        <div>
          <h1 className="text-container">API dos Deputados</h1>
          <h3 className="text-container">Érick Goulardt & Pedro Zandoná</h3>
        </div>
        <div className="button-container">
          <Button children="Deputados" onClick={() => (navigate("/deputados"))}/><Button children="Eventos" onClick={() => (navigate("/eventos"))}/>
        </div>
      </div>
    </div>
  );
}
