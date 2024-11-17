// src/components/MarketplaceSection/MarketplaceSection.js
import React from "react";
import PropTypes from "prop-types";
import "./MarketplaceSection.css";

function MarketplaceSection({ title, products, link }) {
  return (
    <div className="marketplace-section-container">
      <h2 className="marketplace-section-title">{title}</h2>
      <div className="marketplace-section-table-responsive">
        <table className="marketplace-section-table">
          <thead>
            <tr>
              <th>Ürün Adı</th>
              <th>Kategori</th>
              <th>Özellikler</th>
              <th>Satış Adedi</th>
              <th>Fiyat</th>
              <th>KDV Oranı</th>
              <th>Sipariş Durumu</th>
              <th>Kalan Stok</th>
              <th>Aksiyonlar</th>
            </tr>
          </thead>
          <tbody>
            {products.map((product, index) => (
              <tr key={index}>
                <td>{product.name}</td>
                <td>{product.category}</td>
                <td>{product.features}</td>
                <td>{product.sales}</td>
                <td>{product.price}</td>
                <td>{product.taxRate}</td>
                <td>{product.orderStatus}</td>
                <td>{product.remainingStock}</td>
                <td>
                  <a href="#" className="marketplace-section-btn">
                    Fiyat/KDV Güncelle
                  </a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <a
        href={link}
        target="_blank"
        rel="noopener noreferrer"
        className="marketplace-section-btn marketplace-section-link-btn"
      >
        {title.split(" ")[0]} Mağazam
      </a>
    </div>
  );
}

MarketplaceSection.propTypes = {
  title: PropTypes.string.isRequired,
  products: PropTypes.arrayOf(
    PropTypes.shape({
      name: PropTypes.string.isRequired,
      category: PropTypes.string.isRequired,
      features: PropTypes.string.isRequired,
      sales: PropTypes.number.isRequired,
      price: PropTypes.string.isRequired,
      taxRate: PropTypes.string.isRequired,
      orderStatus: PropTypes.string.isRequired,
      remainingStock: PropTypes.number.isRequired,
    })
  ).isRequired,
  link: PropTypes.string.isRequired,
};

export default MarketplaceSection;
