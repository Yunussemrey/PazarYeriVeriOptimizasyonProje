import React from "react";
import PropTypes from "prop-types";
import "./ProductModal.css";

function ProductModal({
  showModal,
  onClose,
  onSubmit,
  productData,
  handleInputChange,
}) {
  if (!showModal) return null;

  return (
    <div className="product-modal-overlay">
      <div className="product-modal-dialog">
        <div className="product-modal-content">
          <div className="product-modal-header">
            <h5 className="product-modal-title">Ürün Ekle</h5>
            <button
              type="button"
              className="product-btn-close"
              onClick={onClose}
            >
              &times;
            </button>
          </div>
          <div className="product-modal-body">
            <form>
              <div className="product-form-group">
                <label htmlFor="name">Ürün Adı</label>
                <input
                  type="text"
                  id="name"
                  className="product-form-control"
                  name="name"
                  value={productData.name}
                  onChange={handleInputChange}
                  placeholder="Ürün adını girin"
                />
              </div>
              <div className="product-form-group">
                <label htmlFor="code">Ürün Kodu</label>
                <input
                  type="text"
                  id="code"
                  className="product-form-control"
                  name="code"
                  value={productData.code}
                  onChange={handleInputChange}
                  placeholder="Ürün kodunu girin"
                />
              </div>
              <div className="product-form-group">
                <label htmlFor="stock">Stok Sayısı</label>
                <input
                  type="number"
                  id="stock"
                  className="product-form-control"
                  name="stock"
                  value={productData.stock}
                  onChange={handleInputChange}
                  placeholder="Stok sayısını girin"
                />
              </div>
              <div className="product-form-group">
                <label htmlFor="price">Birim Fiyat</label>
                <input
                  type="text"
                  id="price"
                  className="product-form-control"
                  name="price"
                  value={productData.price}
                  onChange={handleInputChange}
                  placeholder="Birim fiyatı girin"
                />
              </div>
              <div className="product-form-group">
                <label htmlFor="description">Açıklama</label>
                <textarea
                  id="description"
                  className="product-form-control"
                  name="description"
                  value={productData.description}
                  onChange={handleInputChange}
                  rows="3"
                  placeholder="Ürün açıklamasını girin"
                ></textarea>
              </div>
              <div className="product-form-group">
                <label htmlFor="brand">Marka</label>
                <input
                  type="text"
                  id="brand"
                  className="product-form-control"
                  name="brand"
                  value={productData.brand}
                  onChange={handleInputChange}
                  placeholder="Marka adını girin"
                />
              </div>
              <div className="product-form-group">
                <label htmlFor="category">Kategori</label>
                <select
                  id="category"
                  className="product-form-control"
                  name="category"
                  value={productData.category}
                  onChange={handleInputChange}
                >
                  <option value="Elektronik">Elektronik</option>
                  <option value="Giyim">Giyim</option>
                  <option value="Ev & Bahçe">Ev & Bahçe</option>
                </select>
              </div>
            </form>
          </div>
          <div className="product-modal-footer">
            <button
              type="button"
              className="product-btn-secondary"
              onClick={onClose}
            >
              Kapat
            </button>
            <button
              type="button"
              className="product-btn-primary"
              onClick={onSubmit}
            >
              Ekle
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}

ProductModal.propTypes = {
  showModal: PropTypes.bool.isRequired,
  onClose: PropTypes.func.isRequired,
  onSubmit: PropTypes.func.isRequired,
  productData: PropTypes.object.isRequired,
  handleInputChange: PropTypes.func.isRequired,
};

export default ProductModal;
