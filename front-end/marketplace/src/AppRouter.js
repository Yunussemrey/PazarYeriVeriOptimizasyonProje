// src/AppRouter.js
import React from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
import { isAuthenticated, getUserRole } from "./utils/auth";

// Import page components
import LoginPage from "./pages/LoginPage";
import UserPage from "./components/UserPage"; // Placeholder for user pages
import UserManagementPage from "./pages/UserManagementPage/UserManagementPage";

function AppRouter() {
  const isLoggedIn = isAuthenticated();
  const role = getUserRole();

  return (
    <Router>
      <Routes>
        {/* Route for non-logged-in users */}
        <Route
          path="/"
          element={
            !isLoggedIn ? (
              <LoginPage />
            ) : (
              <Navigate to={`/${role.toLowerCase()}`} />
            )
          }
        />

        {/* User and Store Owner Pages */}
        {isLoggedIn && (role === "USER" || role === "STORE_OWNER") && (
          <Route path="/user" element={<UserPage />} />
        )}

        {/* Admin and Super Admin Pages */}
        {isLoggedIn && (role === "ADMIN" || role === "SUPER_ADMIN") && (
          <Route path="/user-management" element={<UserManagementPage />} />
        )}

        {/* Redirect if page not found */}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </Router>
  );
}

export default AppRouter;
