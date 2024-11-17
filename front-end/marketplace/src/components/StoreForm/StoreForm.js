import React from "react";
import PropTypes from "prop-types";
import "./StoreForm.css";

const StoreForm = ({
  formValues,
  handleChange,
  handleSubmit,
  handleCancel,
  isUpdating,
}) => {
  return (
    <div className="store-profile">
      <h2>Mağaza Bilgileri {isUpdating ? "Güncelle" : "Ekle"}</h2>
      <form onSubmit={handleSubmit}>
        <div className="store-form-group">
          <label htmlFor="storeName">Mağaza Adı:</label>
          <input
            type="text"
            className="store-form-control"
            id="storeName"
            name="storeName"
            placeholder="Mağaza Adı"
            required
            value={formValues.storeName}
            onChange={handleChange}
          />
        </div>
        <div className="store-form-group">
          <label htmlFor="phone">Telefon Numarası:</label>
          <input
            type="text"
            className="store-form-control"
            id="phone"
            name="phone"
            placeholder="Telefon Numarası"
            required
            maxLength="10"
            pattern="\d*"
            title="Lütfen sadece rakam giriniz."
            value={formValues.phone}
            onChange={handleChange}
          />
        </div>
        <div className="store-form-group">
          <label htmlFor="taxNumber">Vergi Numarası:</label>
          <input
            type="text"
            className="store-form-control"
            id="taxNumber"
            name="taxNumber"
            placeholder="Vergi Numarası"
            required
            maxLength="10"
            pattern="\d*"
            title="Lütfen sadece rakam giriniz."
            value={formValues.taxNumber}
            onChange={handleChange}
          />
        </div>
        <div className="store-form-group">
          <label htmlFor="storeDesc">Mağaza Açıklaması:</label>
          <textarea
            className="store-form-control"
            id="storeDesc"
            name="storeDesc"
            rows="3"
            placeholder="Mağaza Açıklaması"
            required
            value={formValues.storeDesc}
            onChange={handleChange}
          ></textarea>
        </div>
        <div className="store-form-group">
          <label htmlFor="storeAddress">Mağaza Adresi:</label>
          <input
            type="text"
            className="store-form-control"
            id="storeAddress"
            name="storeAddress"
            placeholder="Mağaza Adresi"
            required
            value={formValues.storeAddress}
            onChange={handleChange}
          />
        </div>
        <button type="submit" className="btn-store btn-store-primary">
          Kaydet
        </button>
        <button
          type="button"
          className="btn-store btn-store-secondary"
          onClick={handleCancel}
          style={{ marginLeft: "10px" }}
        >
          İptal
        </button>
      </form>
    </div>
  );
};

StoreForm.propTypes = {
  formValues: PropTypes.object.isRequired,
  handleChange: PropTypes.func.isRequired,
  handleSubmit: PropTypes.func.isRequired,
  handleCancel: PropTypes.func.isRequired,
  isUpdating: PropTypes.bool.isRequired,
};

export default StoreForm;
