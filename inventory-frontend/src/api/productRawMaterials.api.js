import http from "./http";
export const getProductRawMaterials = (productId) =>
  http.get(`/products/${productId}/raw-materials`);

export const addProductRawMaterial = (productId, data) =>
  http.post(`/products/${productId}/raw-materials`, data);

export const getSimulation = (productId) =>
  http.get(`/production/${productId}/simulation`);