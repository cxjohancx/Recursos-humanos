import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { FaUser, FaLock } from "react-icons/fa";

export default function Login() {
  const [persona, setPersona] = useState({
    usuario: "",
    contraseña: "",
  });

  const { usuario, contraseña } = persona;
  const [verContraseña, setVerContraseña] = useState(false);
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const onInputChange = (e) => {
    setPersona({ ...persona, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const res = await axios.post("http://localhost:8080/auth/inicio", persona);
      localStorage.setItem("token", res.data.token);
      navigate("/home");
    } catch (err) {
      setError("Credenciales inválidas o error en el servidor");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gradient-to-br from-blue-100 to-blue-300">
      <form
        onSubmit={handleSubmit}
        className="border rounded-3xl p-8 w-96 shadow-2xl bg-white/80 backdrop-blur-md transform transition duration-500 hover:scale-[1.01]"
      >
        <h2 className="text-center text-3xl font-bold text-blue-700 mb-6 flex items-center justify-center gap-2">
          <FaLock className="text-blue-600" /> Iniciar Sesión
        </h2>

        {/* Usuario */}
        <label className="block mb-2 font-medium text-gray-700">Usuario</label>
        <div className="relative mb-4">
          <FaUser className="absolute left-3 top-3.5 text-gray-400" />
          <input
            type="text"
            name="usuario"
            value={usuario}
            onChange={onInputChange}
            className="border w-full rounded-xl p-3 pl-10 focus:ring-2 focus:ring-blue-400 outline-none shadow-sm"
            placeholder="Ingresa tu usuario"
            required
          />
        </div>

        {/* Contraseña */}
        <label className="block mb-2 font-medium text-gray-700">Contraseña</label>
        <div className="relative mb-4">
          <FaLock className="absolute left-3 top-3.5 text-gray-400" />
          <input
            type={verContraseña ? "text" : "password"}
            name="contraseña"
            value={contraseña}
            onChange={onInputChange}
            className="border w-full rounded-xl p-3 pl-10 pr-10 focus:ring-2 focus:ring-blue-400 outline-none shadow-sm"
            placeholder="Ingresa tu contraseña"
            required
          />
          <button
            type="button"
            onClick={() => setVerContraseña(!verContraseña)}
            className="absolute right-3 top-3 text-gray-500 hover:text-gray-700"
          >
            {verContraseña ? "🙈" : "👁️"}
          </button>
        </div>

        {/* Error */}
        {error && (
          <p className="text-red-500 text-sm mb-4 text-center font-medium">
            {error}
          </p>
        )}

        {/* Botón login */}
        <button
          type="submit"
          className="w-full bg-blue-600 text-white rounded-xl p-3 font-semibold shadow-md hover:bg-blue-700 hover:shadow-lg transition"
        >
          Ingresar
        </button>
      </form>
    </div>
  );
}
