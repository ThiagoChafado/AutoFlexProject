import http from "./http";

export const getGlobalProductionSimulation = () =>
  http.get("/production/simulation");