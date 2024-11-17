import React from "react";
import PropTypes from "prop-types";
import "./StoreInfo.css";

const StoreInfo = ({ storeInfo, onUpdate }) => {
  return (
    <div className="store-profile store-info">
      <h2>Mağaza Bilgileri</h2>
      <div className="info-row">
        <div className="info-label">Mağaza Adı:</div>
        <div className="info-value">{storeInfo.storeName}</div>
      </div>
      <div className="info-row">
        <div className="info-label">Telefon Numarası:</div>
        <div className="info-value">{storeInfo.phone}</div>
      </div>
      <div className="info-row">
        <div className="info-label">Vergi Numarası:</div>
        <div className="info-value">{storeInfo.taxNumber}</div>
      </div>
      <div className="info-row">
        <div className="info-label">Mağaza Açıklaması:</div>
        <div className="info-value">{storeInfo.storeDesc}</div>
      </div>
      <div className="info-row">
        <div className="info-label">Mağaza Adresi:</div>
        <div className="info-value">{storeInfo.storeAddress}</div>
      </div>
      <button className="btn-store btn-store-update" onClick={onUpdate}>
        Güncelle
      </button>
    </div>
  );
};

StoreInfo.propTypes = {
  storeInfo: PropTypes.object.isRequired,
  onUpdate: PropTypes.func.isRequired,
};

export default StoreInfo;
