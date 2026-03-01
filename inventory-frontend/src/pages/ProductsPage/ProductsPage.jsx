import { useEffect, useState } from "react";
import { getProducts, createProduct } from "../../api/products.api";
import { useNavigate } from "react-router-dom";
import ProductsForm from "./ProductsForm";

export default function ProductsPage() {
  const [products, setProducts] = useState([]);
  const navigate = useNavigate();

  const loadProducts = async () => {
    const res = await getProducts();
    setProducts(res.data);
  };

  useEffect(() => {
    loadProducts();
  }, []);

  const handleCreate = async (data) => {
    await createProduct(data);
    loadProducts();
  };

  return (
    <div className="max-w-7xl mx-auto p-4 md:p-8">
      <h1 className="text-3xl font-extrabold text-gray-900 mb-8">Products Management</h1>

      <ProductsForm onCreate={handleCreate} />

      <h3 className="text-xl font-semibold text-gray-800 mb-4 mt-10">Products List</h3>
      
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {products.map(p => (
          <div key={p.id} className="bg-white p-6 rounded-xl shadow-sm hover:shadow-md transition-shadow border border-gray-100 flex flex-col justify-between">
            <div>
              <h4 className="text-lg font-bold text-gray-800">{p.name}</h4>
              <p className="text-gray-500 mt-2">
                Price: <span className="text-green-600 font-bold text-lg">${p.price}</span>
              </p>
            </div>
            <button 
              onClick={() => navigate(`/products/${p.id}`)}
              className="mt-6 w-full bg-blue-600 hover:bg-blue-700 text-white font-medium py-2 px-4 rounded-lg transition-colors"
            >
              View Details
            </button>
          </div>
        ))}
        
        {products.length === 0 && (
          <p className="text-gray-500 col-span-full">No products registered yet.</p>
        )}
      </div>
    </div>
  );
}