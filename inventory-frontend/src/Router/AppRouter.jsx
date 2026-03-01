import { BrowserRouter, Routes, Route } from "react-router-dom";
import ProductsPage from "../pages/ProductsPage/ProductsPage";
import RawMaterialsPage from "../pages/RawMaterialsPage/RawMaterialsPage"
import ProductsDetailsPage from "../pages/ProductsDetails/ProductsDetailsPage";
import Navbar from "../components/Navbar";
import ProductionSimulationPage from "../pages/ProductionSimulationPage/ProductionSimulationPage";

export default function AppRouter() {
  return (

    <BrowserRouter>
    <Navbar/>
      <Routes>
        <Route path="/" element={<ProductsPage />} />
        <Route path="/raw-materials" element={<RawMaterialsPage />} />
        <Route path="/products/:id" element={<ProductsDetailsPage />} />
        <Route path="/production-simulation" element={<ProductionSimulationPage/>} />
      </Routes>
    </BrowserRouter>
  );
}