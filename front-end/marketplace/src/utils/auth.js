// src/utils/auth.js
// Utility functions to check if a user is logged in and get role

export const isAuthenticated = () => {
  const token = localStorage.getItem("token");
  return token ? true : false;
};

// Function to extract role
export const getUserRole = () => {
  const role = localStorage.getItem("role");
  return role;
};

export const logout = () => {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
};
