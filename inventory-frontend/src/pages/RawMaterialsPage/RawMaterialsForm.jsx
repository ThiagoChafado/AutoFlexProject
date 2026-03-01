import { useState } from "react";

export default function RawMaterialForm({ onCreate }) {
  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [stockQuantity, setStockQuantity] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!code || !name || !stockQuantity) return;

    onCreate({
      code,
      name,
      stockQuantity: Number(stockQuantity)
    });

    setCode("");
    setName("");
    setStockQuantity("");
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-6 md:p-8 rounded-xl shadow-sm border border-gray-200">
      <h3 className="text-xl font-semibold text-gray-800 mb-6">Create New Raw Material</h3>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Code</label>
          <input
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-indigo-500 outline-none transition-all"
            value={code}
            onChange={(e) => setCode(e.target.value)}
            placeholder="Ex: 8008"
          />
        </div>

        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Name</label>
          <input
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-indigo-500 outline-none transition-all"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Raw material name"
          />
        </div>

        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Initial Quantity</label>
          <input
            type="number"
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-indigo-500 outline-none transition-all"
            value={stockQuantity}
            onChange={(e) => setStockQuantity(e.target.value)}
            placeholder="0"
          />
        </div>
      </div>

      <div className="mt-8 flex justify-end">
        <button type="submit" className="w-full md:w-auto bg-indigo-600 hover:bg-indigo-700 text-white font-medium py-3 px-8 rounded-lg transition-colors">
          Create Raw Material
        </button>
      </div>
    </form>
  );
}