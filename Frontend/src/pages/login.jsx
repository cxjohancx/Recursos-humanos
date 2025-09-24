import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [verContraseña, setVerContraseña] = useState(false);
  const [error, setError] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      const res = await axios.post("http://localhost:8080/api/auth/login", {
        username,
        password,
      });

      localStorage.setItem("token", res.data.token);

      navigate("/home"); 
    } catch (err) {
      setError("Credenciales inválidas o error en el servidor");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen">
      <form
        onSubmit={handleSubmit}
        className="border rounded-2xl p-10 w-80 shadow"
      >
        <h2 className="text-center text-xl font-bold mb-6">Iniciar Sesión</h2>

        {/* Usuario */}
        <label className="block mb-2">Usuario</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="border w-full rounded-xl p-2 mb-4"
          required
        />

        {/* Contraseña */}
        <label className="block mb-2">Contraseña</label>
        <div className="relative mb-4">
          <input
            type={verContraseña ? "text" : "password"}
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="border w-full rounded-xl p-2 pr-10"
            required
          />
          <button
            type="button"
            onClick={() => setVerContraseña(!verContraseña)}
            className="absolute right-2 top-2 text-sm text-gray-500"
          >
            {verContraseña ? "🙈" : "👁️"}
          </button>
        </div>

        {/* Error */}
        {error && <p className="text-red-500 text-sm mb-4">{error}</p>}

        {/* Botón login */}
        <button
          type="submit"
          className="w-full bg-blue-500 text-white rounded-xl p-2 hover:bg-blue-600"
        >
          Ingresar
        </button>
      </form>
    </div>
  );
}
