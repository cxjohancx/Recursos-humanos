import React from "react";
import { IoExit, IoHomeSharp } from "react-icons/io5";
import { Link, Outlet, useNavigate } from "react-router-dom";

function Layout() {
  const navigate = useNavigate();

  const removerToken = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <div className="flex flex-col min-h-screen bg-gradient-to-br from-cyan-50 to-blue-100">
      {/* Header */}
      <header className="h-16 flex items-center justify-between px-6 shadow-md bg-gradient-to-r from-cyan-400 to-blue-400 text-white">
        <div className="flex gap-4 items-center">
          <Link to="/home" className="hover:scale-90 transition">
            <IoHomeSharp className="size-8" />
          </Link>
          <h1 className="text-xl md:text-2xl font-bold tracking-wide">
            Sistema de Recursos Humanos
          </h1>
        </div>

        <button
          onClick={removerToken}
          className="flex items-center gap-2 px-4 py-2 bg-red-500 hover:bg-red-600 rounded-lg shadow-md transition"
        >
          <IoExit className="size-5" />
          <span className="hidden sm:inline font-semibold">Salir</span>
        </button>
      </header>

      {/* Navbar */}
      <nav className="flex gap-6 justify-center py-3 bg-white shadow-md rounded-md mx-4 mt-4">
        <Link
          to="/personal"
          className="px-4 py-2 rounded-full bg-cyan-200 hover:bg-cyan-300 hover:scale-95 shadow-sm font-medium text-gray-700 transition"
        >
          Personal
        </Link>
        <Link
          to="/departamentos"
          className="px-4 py-2 rounded-full bg-cyan-200 hover:bg-cyan-300 hover:scale-95 shadow-sm font-medium text-gray-700 transition"
        >
          Departamentos
        </Link>
      </nav>

      {/* Main Content */}
      <main className="flex-1 p-6">
        <Outlet />
      </main>

      {/* Footer */}
      <footer className="h-14 flex items-center justify-center bg-gradient-to-r from-cyan-400 to-blue-400 text-white text-sm shadow-inner">
        © 2025 Todos los derechos reservados — <span className="font-semibold ml-1">Johan-dev</span>
      </footer>
    </div>
  );
}

export default Layout;
