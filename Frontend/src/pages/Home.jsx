import React from "react"
import { FaUsers, FaBriefcase, FaBuilding, FaChartLine } from "react-icons/fa"
import { Link } from "react-router-dom"

export default function Home() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-cyan-50 to-blue-100 p-10">
      {/* Encabezado principal */}
      <div className="text-center mb-12">
        <h1 className="text-4xl md:text-5xl font-extrabold text-gray-800 mb-4">
          Bienvenido al <span className="text-blue-600">Sistema de Gestión de RRHH</span>
        </h1>
        <p className="text-lg text-gray-600 max-w-2xl mx-auto">
          Administra de manera eficiente los empleados, departamentos, salarios y mucho más
          desde un solo lugar.
        </p>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-2 gap-8 max-w-6xl mx-auto">
        <div className="bg-white flex flex-col items-center shadow-md text-center rounded-2xl p-6 hover:shadow-xl transition transform hover:-translate-y-1">
          <FaUsers className="text-blue-600 text-4xl mb-4" />
          <h2 className="text-xl font-bold text-gray-700">Empleados</h2>
          <p className="text-gray-500 mb-3">Gestiona la información de los trabajadores.</p>
          <Link to="/personal" className="px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
            Ver más
          </Link>
        </div>

        <div className="bg-white flex flex-col items-center shadow-md rounded-2xl p-6 hover:shadow-xl transition transform hover:-translate-y-1">
          <FaBuilding className="text-green-600 text-4xl mb-4" />
          <h2 className="text-xl font-bold text-gray-700">Departamentos</h2>
          <p className="text-gray-500 mb-3">Organiza los diferentes departamentos.</p>
          <Link to="/departamentos" className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700">
            Ver más
          </Link>
        </div>
      </div>
    </div>
  )
}
