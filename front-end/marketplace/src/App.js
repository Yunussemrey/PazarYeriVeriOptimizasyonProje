// src/App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/HomePage/HomePage";
import LoginPage from "./pages/LoginPage/LoginPage";
import RegistrationPage from "./pages/RegistrationPage/RegistrationPage";
import AboutUsPage from "./pages/AboutUsPage/AboutUsPage";
import MarketplacePage from "./pages/MarketplacePage/MarketplacePage";
import OrderManagementPage from "./pages/OrderManagementPage/OrderManagementPage";
import EfaturaPage from "./pages/EfaturaPage/EfaturaPage";
import SalesManagementPage from "./pages/SalesManagementPage/SalesManagementPage";
import ProfilePage from "./pages/ProfilePage/ProfilePage";
import StoreProfilePage from "./pages/StoreProfilePage/StoreProfilePage";
import ProductsSectionPage from "./pages/ProductsSectionPage/ProductsSectionPage";
import UserManagementPage from "./pages/UserManagementPage/UserManagementPage";
import CategoryBrandManagementPage from "./pages/CategoryBrandManagementPage/CategoryBrandManagementPage";
function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegistrationPage />} />
        <Route path="/about-us" element={<AboutUsPage />} />
        <Route path="/marketplace" element={<MarketplacePage />} />
        <Route path="/ordermanagement" element={<OrderManagementPage />} />
        <Route path="/efatura" element={<EfaturaPage />} />
        <Route path="/salesmanagement" element={<SalesManagementPage />} />
        <Route path="/profile" element={<ProfilePage />} />
        <Route path="/storeprofile" element={<StoreProfilePage />} />
        <Route path="/products-section" element={<ProductsSectionPage />} />
        <Route path="/user-management" element={<UserManagementPage />} />
        <Route
          path="/category-brand-management"
          element={<CategoryBrandManagementPage />}
        />
      </Routes>
    </Router>
  );
}

export default App;
