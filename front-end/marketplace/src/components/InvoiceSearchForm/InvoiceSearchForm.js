import React from "react";
import PropTypes from "prop-types";
import "../../styles/FormStyles.css"; // Assuming you have shared form styles
import "./InvoiceSearchForm.css";

function InvoiceSearchForm({ formData, handleChange, handleSubmit }) {
  return (
    <div className="invoice-search-section">
      <form onSubmit={handleSubmit}>
        <div className="invoice-form-row">
          <div className="invoice-form-group">
            <label htmlFor="ettin">ETTN:</label>
            <input
              type="text"
              className="invoice-form-input"
              id="ettin"
              name="ettin"
              value={formData.ettin}
              onChange={handleChange}
              placeholder="ETTN girin"
            />
          </div>
          <div className="invoice-form-group">
            <label htmlFor="receiverName">Alıcı Adı:</label>
            <input
              type="text"
              className="invoice-form-input"
              id="receiverName"
              name="receiverName"
              value={formData.receiverName}
              onChange={handleChange}
              placeholder="Alıcı Adı girin"
            />
          </div>
          <div className="invoice-form-group">
            <label htmlFor="invoiceDate">Fatura Tarihi:</label>
            <input
              type="date"
              className="invoice-form-input"
              id="invoiceDate"
              name="invoiceDate"
              value={formData.invoiceDate}
              onChange={handleChange}
            />
          </div>
        </div>
        <div className="invoice-form-row">
          <div className="invoice-form-group">
            <label htmlFor="status">Durum:</label>
            <select
              className="invoice-form-select"
              id="status"
              name="status"
              value={formData.status}
              onChange={handleChange}
            >
              <option value="all">Tümü</option>
              <option value="approved">Onaylı</option>
              <option value="pending">Beklemede</option>
              <option value="rejected">Reddedildi</option>
            </select>
          </div>
          <div className="invoice-form-group">
            <label htmlFor="invoiceType">Fatura Türü:</label>
            <select
              className="invoice-form-select"
              id="invoiceType"
              name="invoiceType"
              value={formData.invoiceType}
              onChange={handleChange}
            >
              <option value="all">Tümü</option>
              <option value="e-invoice">E-Fatura</option>
              <option value="e-archive">E-Arşiv</option>
            </select>
          </div>
          <div className="invoice-form-group">
            <label htmlFor="marketplace">Pazar Yeri:</label>
            <select
              className="invoice-form-select"
              id="marketplace"
              name="marketplace"
              value={formData.marketplace}
              onChange={handleChange}
            >
              <option value="all">Tümü</option>
              <option value="trendyol">Trendyol</option>
              <option value="hepsiburada">Hepsiburada</option>
              <option value="n11">N11</option>
            </select>
          </div>
        </div>
        <div className="invoice-form-row">
          <div className="invoice-form-group">
            <label htmlFor="storeName">Mağaza Adı:</label>
            <select
              className="invoice-form-select"
              id="storeName"
              name="storeName"
              value={formData.storeName}
              onChange={handleChange}
            >
              <option value="store1">Mağaza 1</option>
              <option value="store2">Mağaza 2</option>
              <option value="store3">Mağaza 3</option>
            </select>
          </div>
          <div className="invoice-form-group invoice-form-group-button">
            <button type="submit" className="invoice-btn-primary">
              Ara
            </button>
          </div>
        </div>
      </form>
    </div>
  );
}

InvoiceSearchForm.propTypes = {
  formData: PropTypes.object.isRequired,
  handleChange: PropTypes.func.isRequired,
  handleSubmit: PropTypes.func.isRequired,
};

export default InvoiceSearchForm;
