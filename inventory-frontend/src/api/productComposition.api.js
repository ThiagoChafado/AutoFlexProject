import http from "./http";

export const getProductRawMaterials = (productId) =>
  http.get(`/production/${productId}/raw-materials`);

export const attachRawMaterial = (productId, data) =>
  http.post(`/production/${productId}/raw-materials`, data);