import React, { useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import StoreInfo from "../../components/StoreInfo/StoreInfo";
import StoreForm from "../../components/StoreForm/StoreForm";
import "./StoreProfilePage.css";

const StoreProfilePage = () => {
  const [storeInfo, setStoreInfo] = useState(null);
  const [isAddingStore, setIsAddingStore] = useState(false);

  // State to hold form input values
  const [formValues, setFormValues] = useState({
    storeName: "",
    phone: "",
    taxNumber: "",
    storeDesc: "",
    storeAddress: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormValues({ ...formValues, [name]: value });
  };

  const handleAddStore = () => {
    setIsAddingStore(true);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Store information submitted:", formValues);
    setStoreInfo(formValues); // Update storeInfo with the form data
    setIsAddingStore(false); // Hide the form after submission
  };

  const handleUpdateStore = () => {
    // When updating, preload formValues with existing storeInfo
    setFormValues(storeInfo);
    setIsAddingStore(true);
  };

  const handleCancel = () => {
    setIsAddingStore(false);
  };

  return (
    <div className="main-container">
      <Navbar />
      <div className="main-content">
        <div className="storeprofile-header">
          <h1>Hoşgeldiniz, Kullanıcı Adı!</h1>
          <p>Mağaza bilgilerinizi ekleyin veya güncelleyin.</p>
          {!storeInfo && !isAddingStore && (
            <button
              className="btn-store btn-store-primary"
              onClick={handleAddStore}
            >
              Mağaza Ekle
            </button>
          )}
        </div>
        {/* Store Information Display */}
        {storeInfo && !isAddingStore && (
          <StoreInfo storeInfo={storeInfo} onUpdate={handleUpdateStore} />
        )}

        {/* Add or Update Store Form */}
        {isAddingStore && (
          <StoreForm
            formValues={formValues}
            handleChange={handleChange}
            handleSubmit={handleSubmit}
            handleCancel={handleCancel}
            isUpdating={!!storeInfo}
          />
        )}
      </div>
      <Footer />
    </div>
  );
};

export default StoreProfilePage;
