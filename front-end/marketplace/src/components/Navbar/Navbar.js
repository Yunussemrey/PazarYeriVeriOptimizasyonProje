// src/components/Navbar/Navbar.js
import React, { useState } from "react";
import { useLocation, Link, useNavigate } from "react-router-dom";
import { getUserRole, isAuthenticated, logout } from "../../utils/auth";
import "./Navbar.css";
import Logo from "../../assets/images/logoyazi.png";

function Navbar() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const isLoggedIn = isAuthenticated();
  const role = getUserRole();
  const location = useLocation();
  const navigate = useNavigate();
  const currentPath = location.pathname;

  const toggleMenu = () => {
    setIsMenuOpen(!isMenuOpen);
  };

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="navbar-brand">
          <img src={Logo} alt="Logo" className="navbar-logo" />
        </Link>
        <button
          className="navbar-toggler"
          onClick={toggleMenu}
          aria-label="Toggle menu"
        >
          <i className="fas fa-bars"></i>
        </button>
        <ul className={`navbar-links ${isMenuOpen ? "active" : ""}`}>
          <li>
            <Link to="/" className={currentPath === "/" ? "active" : ""}>
              Ana Sayfa
            </Link>
          </li>

          {!isLoggedIn && (
            <>
              <li>
                <Link
                  to="/about-us"
                  className={currentPath === "/about-us" ? "active" : ""}
                >
                  Hakkımızda
                </Link>
              </li>
              <li>
                <Link
                  to="/register"
                  className={currentPath === "/register" ? "active" : ""}
                >
                  Kayıt Ol
                </Link>
              </li>
              <li>
                <Link
                  to="/login"
                  className={currentPath === "/login" ? "active" : ""}
                >
                  Giriş Yap
                </Link>
              </li>
            </>
          )}

          {isLoggedIn && (role === "USER" || role === "STORE_OWNER") && (
            <>
              <li>
                <Link
                  to="/profile"
                  className={currentPath === "/profile" ? "active" : ""}
                >
                  Kullanıcı
                </Link>
              </li>
              <li>
                <Link
                  to="/storeprofile"
                  className={currentPath === "/storeprofile" ? "active" : ""}
                >
                  Mağazam
                </Link>
              </li>
              <li>
                <Link
                  to="/products-section"
                  className={
                    currentPath === "/products-section" ? "active" : ""
                  }
                >
                  Ürünler
                </Link>
              </li>
              <li>
                <Link
                  to="/marketplace"
                  className={currentPath === "/marketplace" ? "active" : ""}
                >
                  Pazar Yeri
                </Link>
              </li>
              <li>
                <Link
                  to="/ordermanagement"
                  className={currentPath === "/ordermanagement" ? "active" : ""}
                >
                  Siparişler
                </Link>
              </li>
              <li>
                <Link
                  to="/salesmanagement"
                  className={currentPath === "/salesmanagement" ? "active" : ""}
                >
                  Satış Yönetimi
                </Link>
              </li>
              <li>
                <Link
                  to="/efatura"
                  className={currentPath === "/efatura" ? "active" : ""}
                >
                  E-Fatura
                </Link>
              </li>
            </>
          )}

          {isLoggedIn && (role === "ADMIN" || role === "SUPER_ADMIN") && (
            <>
              <li>
                <Link
                  to="/user-management"
                  className={currentPath === "/user-management" ? "active" : ""}
                >
                  Kullanıcı Yönetimi
                </Link>
              </li>
              <li>
                <Link
                  to="/category-brand-management"
                  className={
                    currentPath === "/category-brand-management" ? "active" : ""
                  }
                >
                  Kategori ve Marka Yönetimi
                </Link>
              </li>
            </>
          )}

          {isLoggedIn && (
            <li>
              <button onClick={handleLogout} className="logout-btn">
                Çıkış Yap
              </button>
            </li>
          )}
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
