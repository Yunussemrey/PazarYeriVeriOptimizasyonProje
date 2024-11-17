// src/pages/RegistrationPage/RegistrationPage.js
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import "../../styles/FormStyles.css";
import "./RegistrationPage.css";

function RegistrationPage() {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate();

  const handleRegister = (e) => {
    e.preventDefault();

    // Prepare the payload for the API
    const payload = { firstName, lastName, email, password };

    fetch("http://localhost:8080/api/auth/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    })
      .then((response) => {
        if (response.ok) {
          // Success: Navigate to the login page
          navigate("/login");
        } else {
          // Registration failed
          setErrorMessage("Kayıt Başarısız, tekrar deneyin.");
        }
      })
      .catch(() => {
        setErrorMessage("An error occurred. Please try again.");
      });
  };

  return (
    <div className="main-container">
      <Navbar />
      <div className="registration-section">
        <div className="registration-container">
          <div className="registration-card">
            <h2 className="text-center">Kayıt Ol</h2>
            <form onSubmit={handleRegister}>
              <div className="input-group">
                <span className="input-icon">
                  <i className="fas fa-user"></i>
                </span>
                <input
                  type="text"
                  className="form-input"
                  placeholder="Adınız"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  required
                />
              </div>
              <div className="input-group">
                <span className="input-icon">
                  <i className="fas fa-user"></i>
                </span>
                <input
                  type="text"
                  className="form-input"
                  placeholder="Soyadınız"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  required
                />
              </div>
              <div className="input-group">
                <span className="input-icon">
                  <i className="fas fa-envelope"></i>
                </span>
                <input
                  type="email"
                  className="form-input"
                  placeholder="E-posta Adresiniz"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
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
                  placeholder="Şifreniz"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              {errorMessage && <p className="error-message">{errorMessage}</p>}
              <button type="submit" className="btn-register">
                Kayıt Ol
              </button>
            </form>
            <p className="text-center mt-3">
              Zaten bir hesabınız var mı ?{" "}
              <a href="/login" className="login-link">
                Giriş Yapın
              </a>
            </p>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default RegistrationPage;
