// src/pages/LoginPage/LoginPage.js
import React, { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import "./LoginPage.css";
import "../../styles/FormStyles.css";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";

function LoginPage() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    const payload = { email, password };

    fetch("http://localhost:8080/api/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(payload),
    })
      .then(async (response) => {
        const data = await response.json();
        if (!response.ok) {
          const error =
            data.message || "An unexpected error occurred. Please try again.";
          throw new Error(error);
        }
        return data;
      })
      .then((data) => {
        if (data.token && data.role) {
          // Store token and role in localStorage
          localStorage.setItem("token", data.token);
          localStorage.setItem("role", data.role);

          // Redirect based on the user role
          if (data.role === "ADMIN" || data.role === "SUPER_ADMIN") {
            navigate("/user-management");
          } else {
            navigate("/");
          }
        } else {
          setErrorMessage("Login failed. Please check your credentials.");
        }
      })
      .catch((error) => {
        setErrorMessage(error.message); // Display specific error message
      });
  };

  return (
    <div className="main-container">
      <Navbar />
      <div className="login-page">
        <div className="login-container">
          <h1 className="login-title">Giriş Yap</h1>
          <form onSubmit={handleLogin}>
            <div className="input-group">
              <span className="input-icon">
                <i className="fas fa-envelope"></i>
              </span>
              <input
                type="email"
                className="form-input"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="E-posta Adresiniz"
                required
              />
            </div>
            <div className="input-group">
              <span className="input-icon">
                <i className="fas fa-lock"></i>
              </span>
              <input
                type="password"
                className="form-input"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                placeholder="Şifreniz"
                required
              />
            </div>
            {errorMessage && <p className="error-message">{errorMessage}</p>}
            <button type="submit" className="login-button">
              Giriş Yap
            </button>
          </form>
          <p>
            Henüz bir hesabınız yok mu ?{" "}
            <Link to="/register" className="register-link">
              Kayıt Olun
            </Link>
          </p>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default LoginPage;
