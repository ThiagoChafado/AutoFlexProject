import http from "./http";
export const getProducts = () =>
  http.get("/products");

export const createProduct = (data) =>
  http.post("/products", data);