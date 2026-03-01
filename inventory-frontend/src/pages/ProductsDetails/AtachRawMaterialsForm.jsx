import { useEffect, useState } from "react";
import { attachRawMaterial } from "../../api/productComposition.api";
import { getRawMaterials } from "../../api/rawMaterials.api";

export default function AttachRawMaterialForm({ productId, onSuccess }) {
  const [rawMaterials, setRawMaterials] = useState([]);
  const [rawMaterialId, setRawMaterialId] = useState("");
  const [requiredQuantity, setRequiredQuantity] = useState("");
  const [error, setError] = useState(null);

  useEffect(() => {
    getRawMaterials()
      .then(res => setRawMaterials(res.data))
      .catch(() => setError("Fail to load raw materials."));
  }, []);

  const handleSubmit = (e) => {
    e.preventDefault();

    attachRawMaterial(productId, {
      rawMaterialId,
      requiredQuantity: parseFloat(requiredQuantity)
    })
      .then(() => {
        setRawMaterialId("");
        setRequiredQuantity("");
        setError(null);
        if (onSuccess) onSuccess();
      })
      .catch(() => setError("Fail to attach raw material. Please try again."));
  };

  return (
    <form onSubmit={handleSubmit} className="bg-white p-6 md:p-8 rounded-xl shadow-sm border border-gray-200">
      <h3 className="text-xl font-semibold text-gray-800 mb-6">Attach Raw Material</h3>

      {error && (
        <div className="bg-red-50 border-l-4 border-red-500 text-red-700 p-4 rounded mb-6 text-sm">
          {error}
        </div>
      )}

      <div className="flex flex-col gap-5">
        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Select Raw Material</label>
          <select
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 outline-none w-full bg-white transition-all cursor-pointer"
            value={rawMaterialId}
            onChange={e => setRawMaterialId(e.target.value)}
            required
          >
            <option value="" disabled>-- Choose one option --</option>
            {rawMaterials.map(rm => (
              <option key={rm.id} value={rm.id}>
                {rm.name} (Stock: {rm.stockQuantity})
              </option>
            ))}
          </select>
        </div>

        <div className="flex flex-col">
          <label className="text-sm font-medium text-gray-700 mb-2">Required Quantity (per unit)</label>
          <input
            type="number"
            step="0.01"
            className="border border-gray-300 rounded-lg p-3 focus:ring-2 focus:ring-blue-500 outline-none w-full transition-all"
            placeholder="Ex: 50"
            value={requiredQuantity}
            onChange={e => setRequiredQuantity(e.target.value)}
            required
          />
        </div>

        <button type="submit" className="mt-2 bg-blue-600 hover:bg-blue-700 text-white font-medium py-3 rounded-lg transition-colors shadow-sm">
          Add to Composition
        </button>
      </div>
    </form>
  );
}