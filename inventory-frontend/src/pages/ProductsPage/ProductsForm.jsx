import { useState } from "react";

export default function ProductsForm({ onCreate }) {
  const [code, setCode] = useState("");
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!code || !name || !price) return;

    onCreate({
      code,
      name,
      price: Number(price)
    });

    setCode("");
    setName("");
    setPrice("");
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-6 md:p-8 rounded-xl shadow-sm border border-gray-200">
      <h3 className="text-xl font-semibold text-gray-800 mb-6">Create New Product</h3>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Code</label>
          <input
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all"
            value={code}
            onChange={(e) => setCode(e.target.value)}
            placeholder="Ex: 8080"
          />
        </div>

        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Name</label>
          <input
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all"
            value={name}
            onChange={(e) => setName(e.target.value)}
            placeholder="Product name"
          />
        </div>

        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Price</label>
          <input
            type="number"
            step="0.01"
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 focus:border-blue-500 outline-none transition-all"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            placeholder="0.00"
          />
        </div>
      </div>

      <div className="mt-8 flex justify-end">
        <button type="submit" className="w-full md:w-auto bg-green-600 hover:bg-green-700 text-white font-medium py-3 px-8 rounded-lg transition-colors">
            Create Product
        </button>
      </div>
    </form>
  );
}