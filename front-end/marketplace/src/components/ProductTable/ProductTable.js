import React from "react";
import PropTypes from "prop-types";
import "./ProductTable.css";

function ProductTable({ products, onEdit, onDelete, onAddStock }) {
  return (
    <div className="product-table-container">
      <table className="product-table">
        <thead>
          <tr>
            <th>Ürün Adı</th>
            <th>Ürün Kodu</th>
            <th>Stok Sayısı</th>
            <th>Birim Fiyat</th>
            <th>Açıklama</th>
            <th>Marka</th>
            <th>Kategori</th>
            <th>İşlemler</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product, index) => (
            <tr key={index}>
              <td>{product.name}</td>
              <td>{product.code}</td>
              <td>{product.stock} Adet</td>
              <td>{product.price} TL</td>
              <td>{product.description}</td>
              <td>{product.brand}</td>
              <td>{product.category}</td>
              <td className="product-actions">
                <button
                  className="product-btn-stock"
                  title="Stok Ekle"
                  aria-label={`Stok Ekle ${product.name}`}
                  onClick={() => onAddStock(index)}
                >
                  Stok Ekle
                </button>
                <button
                  className="product-btn-edit"
                  title="Düzenle"
                  aria-label={`Düzenle ${product.name}`}
                  onClick={() => onEdit(index)}
                >
                  Düzenle
                </button>
                <button
                  className="product-btn-delete"
                  title="Sil"
                  aria-label={`Sil ${product.name}`}
                  onClick={() => onDelete(index)}
                >
                  Sil
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

ProductTable.propTypes = {
  products: PropTypes.array.isRequired,
  onEdit: PropTypes.func,
  onDelete: PropTypes.func.isRequired,
  onAddStock: PropTypes.func.isRequired,
};

export default ProductTable;
