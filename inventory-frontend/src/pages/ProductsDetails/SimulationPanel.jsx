import { useEffect, useState } from "react";
import { getSimulation } from "../../api/productRawMaterials.api";

export default function SimulationPanel({ productId }) {
  const [simulation, setSimulation] = useState(null);

  const loadSimulation = async () => {
    const res = await getSimulation(productId);
    setSimulation(res.data);
  };

  useEffect(() => {
    loadSimulation();
  }, [productId]);

  if (!simulation) {
    return (
      <div className="bg-gray-50 border border-gray-200 p-8 rounded-xl shadow-sm flex items-center justify-center animate-pulse">
        <p className="text-gray-500 font-medium">No simulation data available</p>
      </div>
    );
  }

  return (
    <div className="bg-gradient-to-br from-gray-900 to-gray-800 p-6 md:p-8 rounded-xl shadow-lg border border-gray-700 text-white sticky top-6">
      <div className="flex items-center gap-3 mb-8">
        <div className="p-2 bg-indigo-500 rounded-lg">
          <svg className="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
          </svg>
        </div>
        <h2 className="text-2xl font-bold">Simulation Panel</h2>
      </div>
      
      <div className="space-y-6">
        <div className="bg-gray-800/50 p-5 rounded-lg border border-gray-600 backdrop-blur-sm">
          <p className="text-gray-400 text-sm font-medium uppercase tracking-wider mb-1">Production Capacity</p>
          <div className="flex items-end gap-2">
            <span className="text-4xl font-extrabold text-indigo-400">{simulation.producibleQuantity}</span>
            <span className="text-gray-300 font-medium pb-1">units</span>
          </div>
          <p className="text-xs text-gray-500 mt-2">Based on current raw materials stock</p>
        </div>
        
        <div className="bg-gray-800/50 p-5 rounded-lg border border-gray-600 backdrop-blur-sm">
          <p className="text-gray-400 text-sm font-medium uppercase tracking-wider mb-1">Total Value Estimated</p>
          <div className="flex items-end gap-2">
            <span className="text-4xl font-extrabold text-green-400">${simulation.totalValue}</span>
          </div>
        </div>
      </div>

      <button 
        onClick={loadSimulation}
        className="mt-8 w-full bg-gray-700 hover:bg-gray-600 text-white font-medium py-3 rounded-lg transition-colors border border-gray-600"
      >
        Update Simulation
      </button>
    </div>
  );
}