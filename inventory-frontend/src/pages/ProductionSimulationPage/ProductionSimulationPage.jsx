import { useEffect, useState } from "react";
import { getGlobalProductionSimulation } from "../../api/production.api";

export default function ProductionSimulationPage() {

  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const loadSimulation = async () => {
    try {
      const res = await getGlobalProductionSimulation();
      setData(res.data);
    } catch (err) {
      console.error(err);
      setError("Failed to load production simulation");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadSimulation();
  }, []);

  if (loading) return <p className="p-8">Loading simulation...</p>;
  if (error) return <p className="p-8 text-red-500">{error}</p>;

  return (
    <div className="max-w-7xl mx-auto p-4 md:p-8">

      <div className="mb-8 border-b pb-4">
        <h1 className="text-3xl font-extrabold text-gray-900">
          Global Production Simulation
        </h1>
        <p className="text-gray-500 mt-2">
          Suggested production based on stock availability and value prioritization.
        </p>
      </div>

      {data.products.length === 0 ? (
        <div className="bg-yellow-50 border border-yellow-200 p-6 rounded-lg">
          <p className="text-yellow-700 font-medium">
            No products can be produced with the current stock.
          </p>
        </div>
      ) : (
        <div className="space-y-6">

          <div className="bg-white rounded-xl shadow-sm border border-gray-200">
            <ul className="divide-y divide-gray-100">
              {data.products.map(product => (
                <li
                  key={product.productId}
                  className="p-6 flex justify-between items-center"
                >
                  <div>
                    <h3 className="text-lg font-semibold text-gray-800">
                      {product.productName}
                    </h3>
                    <p className="text-gray-500 text-sm">
                      Unit price: {product.productPrice}
                    </p>
                  </div>

                  <div className="text-right">
                    <p className="text-gray-700 font-medium">
                      Quantity: {product.producibleQuantity}
                    </p>
                    <p className="text-blue-600 font-bold text-lg">
                      {product.totalValue}
                    </p>
                  </div>
                </li>
              ))}
            </ul>
          </div>

          <div className="bg-blue-50 border border-blue-200 p-6 rounded-xl flex justify-between items-center">
            <span className="text-lg font-semibold text-blue-700">
              Total Production Value
            </span>
            <span className="text-2xl font-extrabold text-blue-800">
              {data.totalProductionValue}
            </span>
          </div>

        </div>
      )}
    </div>
  );
}