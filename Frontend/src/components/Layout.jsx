import React from "react";
import { Outlet } from "react-router-dom";

function Layout() {
  return (
    <div className="flex flex-col min-h-screen">
      <header className="h-16 flex items-center justify-between px-4 border-b">
        <div>Header</div>
        <nav>Nav</nav>
      </header>

      <main className="flex-1 p-4">
        <Outlet />
      </main>

      <footer className="h-12 flex items-center justify-center border-t">
        Footer
      </footer>
    </div>
  );
}

export default Layout;
