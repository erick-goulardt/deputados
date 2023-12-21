import React from "react";
import ReactDOM from "react-dom/client";
import { SiteRoutes } from "./routes/site.routes";
import { BrowserRouter } from "react-router-dom";

ReactDOM.createRoot(document.getElementById("root")!).render(
  <React.StrictMode>
    <BrowserRouter>
      <SiteRoutes />
    </BrowserRouter>
  </React.StrictMode>
);
