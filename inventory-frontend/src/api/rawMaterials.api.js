import http from "./http";
export const getRawMaterials = () =>
  http.get("/raw-materials");

export const createRawMaterial = (data) =>
  http.post("/raw-materials", data);