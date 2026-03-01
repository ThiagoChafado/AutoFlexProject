  import { useParams } from "react-router-dom";
  import { useEffect, useState } from "react";
  import { attachRawMaterial, getProductRawMaterials } from "../../api/productComposition.api";
  import SimulationPanel from "./SimulationPanel";
  import AttachRawMaterialForm from "./AtachRawMaterialsForm"; 


  export default function ProductDetailsPage() {
    const { id } = useParams();
    const [rawMaterials, setRawMaterials] = useState([]);

    const loadRawMaterials = async () => {
  const res = await getProductRawMaterials(id);
  setRawMaterials(res.data);
  };

    useEffect(() => {
      loadRawMaterials();
    }, [id]);

    return (
      <div className="max-w-7xl mx-auto p-4 md:p-8">
        <div className="mb-8 border-b pb-4">
          <h1 className="text-3xl font-extrabold text-gray-900">Composition details</h1>
          <p className="text-gray-500 mt-2">Manage raw materials and see production simulation.</p>
        </div>

        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          <div className="space-y-6">
            <div className="bg-white p-6 md:p-8 rounded-xl shadow-sm border border-gray-200">
              <h2 className="text-xl font-semibold text-gray-800 mb-6">Required Raw Materials</h2>
              
              {rawMaterials.length === 0 ? (
                <p className="text-gray-500 italic bg-gray-50 p-4 rounded-lg text-center">No raw materials attached to this product.</p>
              ) : (
                <ul className="divide-y divide-gray-100">
                  {rawMaterials.map(rm => (
                    <li key={rm.id} className="py-4 flex justify-between items-center">
                      <span className="font-medium text-gray-700 text-lg">{rm.rawMaterialName}</span>
                      <span className="bg-blue-50 text-blue-700 border border-blue-200 px-4 py-1.5 rounded-full font-semibold text-sm">
                        Qtd: {rm.requiredQuantity}
                      </span>
                    </li>
                  ))}
                </ul>
              )}
            </div>

            <AttachRawMaterialForm 
              productId={id} 
              onSuccess={() => {
                loadRawMaterials(); 
              }} 
            />
          </div>


          <div>
            <SimulationPanel productId={id} />
          </div>
        </div>
      </div>
    );
  }