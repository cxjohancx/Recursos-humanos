import React, { useState, useEffect } from "react"
import Api from "../api/AxiosInstance.js"
import { FaPlusCircle } from "react-icons/fa"

const Departamentos = () => {
  const urlBase = "http://localhost:8080/departamento"

  const [departamentos, setDepartamentos] = useState([])
  const [departamento, setDepartamento] = useState({
    idDepartamento: "",
    departamento: ""
  })

  const [verModal, setVerModal] = useState(false)
  const [modo, setModo] = useState("agregar")

  useEffect(() => {
    cargarDepartamentos()
  }, [])

  const cargarDepartamentos = async () => {
    const resultado = await Api.get(urlBase)
    setDepartamentos(resultado.data)
  }

  const handleChange = (e) => {
    const { name, value } = e.target
    setDepartamento({ ...departamento, [name]: value })
  }

  const handleSubmit = async (e) => {
    e.preventDefault()
    try {
      if (modo === "agregar") {
        await Api.post(urlBase, departamento)
      } else {
        await Api.put(urlBase, departamento)
      }
      cargarDepartamentos()
      cerrarModal()
    } catch (error) {
      console.error("Error guardando departamento", error)
    }
  }

  const editarDepartamento = (dep) => {
    setDepartamento(dep)
    setModo("editar")
    setVerModal(true)
  }

  const eliminarDepartamento = async (id) => {
    try {
      await Api.delete(`${urlBase}/${id}`)
      cargarDepartamentos()
    } catch (error) {
      console.error("Error eliminando departamento", error)
    }
  }

  const cerrarModal = () => {
    setVerModal(false)
    setDepartamento({ idDepartamento: "", departamento: "" })
    setModo("agregar")
  }

  return (
    <div className="p-6">
      <h1 className="text-center text-3xl font-extrabold text-blue-700 mb-6">Departamentos</h1>

      {/* Botón agregar */}
      <div className="flex justify-center my-5">
        <button
          onClick={() => setVerModal(true)}
          className="flex items-center gap-2 px-4 py-2 border rounded-lg bg-green-500 text-white 
                     hover:bg-green-600 shadow-md hover:scale-95 transition active:scale-90"
        >
          <FaPlusCircle /> Agregar
        </button>
      </div>

      {/* Tabla */}
      <table className="table-auto border-collapse w-full mt-4 rounded-lg overflow-hidden shadow-md">
        <thead className="bg-blue-600 text-white">
          <tr>
            <th className="px-4 py-2 text-center">Departamento</th>
            <th className="px-4 py-2 text-center">Salario</th>
            <th className="px-4 py-2 text-center">Opciones</th>
          </tr>
        </thead>
        <tbody>
          {departamentos.map((dep) => (
            <tr key={dep.idDepartamento} className="text-center odd:bg-gray-50 even:bg-white hover:bg-blue-50 transition">
              <td className="px-4 py-2">{dep.departamento}</td>
              <td className="px-4 py-2">{dep.salario}</td>
              <td className="px-4 py-2 text-center space-x-2">
                <button
                  onClick={() => editarDepartamento(dep)}
                  className="px-3 py-1 bg-blue-500 text-white rounded-lg hover:bg-blue-600 shadow-sm active:scale-95"
                >
                  Editar
                </button>
                <button
                  onClick={() => eliminarDepartamento(dep.idDepartamento)}
                  className="px-3 py-1 bg-red-500 text-white rounded-lg hover:bg-red-600 shadow-sm active:scale-95"
                >
                  Eliminar
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Modal */}
      {verModal && (
        <div className="fixed inset-0 flex items-center justify-center bg-black/50 z-50">
          <div className="bg-white p-6 rounded-xl shadow-2xl w-full max-w-lg animate-fadeIn">
            <h2 className="text-2xl font-bold text-blue-600 mb-6 text-center">
              {modo === "agregar" ? "Agregar departamento" : "Editar departamento"}
            </h2>
            <form onSubmit={handleSubmit} className="space-y-4">
              <input
                type="number"
                name="idDepartamento"
                value={departamento.departamento}
                onChange={handleChange}
                placeholder="ID Departamento"
                className="border p-3 w-full rounded-lg focus:ring-2 focus:ring-blue-400 outline-none shadow-sm"
                disabled={modo === "editar"}
              />
              <input
                type="text"
                name="departamento"
                value={departamento.salario}
                onChange={handleChange}
                placeholder="Nombre del departamento"
                className="border p-3 w-full rounded-lg focus:ring-2 focus:ring-blue-400 outline-none shadow-sm"
              />

              <div className="flex justify-end gap-3 pt-4">
                <button
                  type="button"
                  onClick={cerrarModal}
                  className="px-4 py-2 rounded-lg bg-gray-300 hover:bg-gray-400 transition shadow-sm active:scale-95"
                >
                  Cancelar
                </button>
                <button
                  type="submit"
                  className="px-4 py-2 rounded-lg bg-green-500 text-white hover:bg-green-600 shadow-md transition active:scale-95"
                >
                  Guardar
                </button>
              </div>
            </form>
          </div>
        </div>
      )}
    </div>
  )
}

export default Departamentos
