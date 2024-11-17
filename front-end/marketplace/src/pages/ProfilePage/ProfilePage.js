// src/pages/ProfilePage/ProfilePage.js
import React, { useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import "./ProfilePage.css";

const ProfilePage = () => {
  // State to hold user information
  const [userInfo, setUserInfo] = useState({
    firstName: "Mevcut İsminiz",
    lastName: "Mevcut Soyisminiz",
    email: "mevcut.email@adresiniz.com",
    // phone: '' // Uncomment if you decide to include phone number
  });

  // Handle input change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setUserInfo((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    // Logic to update user information (e.g., API call)
    console.log("Updated User Info:", userInfo);
    // Display a success message or handle errors as needed
  };

  // Handle account deletion
  const handleDeleteAccount = () => {
    // Logic to delete the account (e.g., API call)
    console.log("Account Deleted");
    // Redirect the user or display a confirmation message
  };

  return (
    <div className="main-container">
      <Navbar />
      {/* Profile Header */}
      <div className="profile-page-header">
        <h1>
          Hoşgeldiniz, {userInfo.firstName} {userInfo.lastName}!
        </h1>
        <p>Burada bilgilerinizi görüntüleyebilir ve güncelleyebilirsiniz.</p>
      </div>

      {/* Profile Content */}
      <div className="profile-page-content">
        <form onSubmit={handleSubmit} className="profile-page-form">
          <div className="profile-page-form-group">
            <label htmlFor="firstName">İsim:</label>
            <input
              type="text"
              id="firstName"
              name="firstName"
              value={userInfo.firstName}
              onChange={handleChange}
              className="profile-page-input"
            />
          </div>
          <div className="profile-page-form-group">
            <label htmlFor="lastName">Soyisim:</label>
            <input
              type="text"
              id="lastName"
              name="lastName"
              value={userInfo.lastName}
              onChange={handleChange}
              className="profile-page-input"
            />
          </div>
          <div className="profile-page-form-group">
            <label htmlFor="email">E-posta Adresiniz:</label>
            <input
              type="email"
              id="email"
              name="email"
              value={userInfo.email}
              onChange={handleChange}
              className="profile-page-input"
            />
          </div>
          {/* Uncomment if you want to include phone input
          <div className="profile-page-form-group">
            <label htmlFor="phone">Telefon Numaranız:</label>
            <input
              type="text"
              id="phone"
              name="phone"
              value={userInfo.phone}
              onChange={handleChange}
              className="profile-page-input"
            />
          </div>
          */}

          {/* Button Group */}
          <div className="profile-page-button-group">
            <button
              type="submit"
              className="profile-page-btn profile-page-btn-update"
            >
              Bilgileri Güncelle
            </button>
            <button
              type="button"
              className="profile-page-btn profile-page-btn-delete"
              onClick={handleDeleteAccount}
            >
              Hesabı Sil
            </button>
          </div>
        </form>
      </div>
      <Footer />
    </div>
  );
};

export default ProfilePage;
