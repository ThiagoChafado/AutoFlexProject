import { useEffect, useState } from "react";
import { getRawMaterials, createRawMaterial } from "../../api/rawMaterials.api";
import RawMaterialForm from "./RawMaterialsForm";

export default function RawMaterialsPage() {
  const [rawMaterials, setRawMaterials] = useState([]);

  const loadRawMaterials = async () => {
    const response = await getRawMaterials();
    setRawMaterials(response.data);
  };

  useEffect(() => {
    loadRawMaterials();
  }, []);

  const handleCreate = async (data) => {
    await createRawMaterial(data);
    loadRawMaterials();
  };

  return (
    <div className="max-w-7xl mx-auto p-4 md:p-8">
      <h1 className="text-3xl font-extrabold text-gray-900 mb-8">Raw Materials Stock</h1>

      <RawMaterialForm onCreate={handleCreate} />

      <h3 className="text-xl font-semibold text-gray-800 mb-4 mt-10">List of Raw Materials</h3>
      
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {rawMaterials.map(rm => (
          <div key={rm.id} className="bg-white p-6 rounded-xl shadow-sm border border-gray-100 flex items-center justify-between">
            <div>
              <h4 className="text-lg font-bold text-gray-800">{rm.name}</h4>
              <p className="text-gray-500 text-sm">Code: {rm.code}</p>
            </div>
            <div className="text-right">
              <p className="text-xs text-gray-500 uppercase tracking-wider font-semibold">Stock</p>
              <p className="text-xl font-bold text-indigo-600">{rm.stockQuantity}</p>
            </div>
          </div>
        ))}

        {rawMaterials.length === 0 && (
          <p className="text-gray-500 col-span-full">No raw materials registered.</p>
        )}
      </div>
    </div>
  );
}