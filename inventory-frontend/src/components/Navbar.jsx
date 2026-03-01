import { NavLink } from "react-router-dom";

export default function Navbar() {
  const linkClass = ({ isActive }) =>
    isActive
      ? "bg-blue-50 text-blue-700 font-semibold px-4 py-2 rounded-lg transition-colors"
      : "text-gray-600 hover:bg-gray-100 hover:text-gray-900 font-medium px-4 py-2 rounded-lg transition-colors";

  return (
    <nav className="bg-white shadow-sm border-b border-gray-200 sticky top-0 z-50">
      <div className="max-w-7xl mx-auto px-4 md:px-8">
        <div className="flex justify-between h-16 items-center">
          
          <div className="flex-shrink-0 flex items-center gap-2">
            <div className="w-8 h-8 bg-blue-600 rounded-lg flex items-center justify-center">
              <span className="text-white font-bold text-xl">I</span>
            </div>
            <span className="font-bold text-xl text-gray-800 tracking-tight">
              Inventory<span className="text-blue-600">App</span>
            </span>
          </div>

          <div className="flex gap-2 md:gap-4">
            <NavLink to="/" className={linkClass}>
              Products
            </NavLink>
            <NavLink to="/raw-materials" className={linkClass}>
              Raw Materials
            </NavLink>
            <NavLink to="/production-simulation" className={linkClass}>
              Production Simulation
            </NavLink>
          </div>

        </div>
      </div>
    </nav>
  );
}