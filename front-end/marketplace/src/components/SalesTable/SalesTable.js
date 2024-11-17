import React from "react";
import PropTypes from "prop-types";
import "./SalesTable.css";

function SalesTable({ salesData }) {
  return (
    <div className="sales-table-responsive">
      <table className="sales-table">
        <thead>
          <tr>
            <th>Ürün Adı</th>
            <th>Kategori</th>
            <th>Satış Adedi</th>
            <th>Birim Fiyatı</th>
            <th>KDV Oranı</th>
            <th>Genel Toplam</th>
            <th>Kar/Zarar Durumu</th>
            <th>İade Oranı</th>
            <th>Pazar Yeri</th>
            <th>Satış Trendi</th>
            <th>Kampanya Önerisi</th>
          </tr>
        </thead>
        <tbody>
          {salesData.map((item, index) => (
            <tr key={index}>
              <td data-label="Ürün Adı">{item.productName}</td>
              <td data-label="Kategori">{item.category}</td>
              <td data-label="Satış Adedi">{item.salesCount}</td>
              <td data-label="Birim Fiyatı">{item.unitPrice} TL</td>
              <td data-label="KDV Oranı">%{item.vatRate}</td>
              <td data-label="Genel Toplam">
                {item.totalAmount.toLocaleString()} TL
              </td>
              <td
                data-label="Kar/Zarar Durumu"
                className={
                  item.profitLoss === "Kar"
                    ? "sales-text-success"
                    : "sales-text-danger"
                }
              >
                {item.profitLoss}
              </td>
              <td data-label="İade Oranı">%{item.returnRate}</td>
              <td data-label="Pazar Yeri">{item.marketplace}</td>
              <td data-label="Satış Trendi">{item.salesTrend}</td>
              <td data-label="Kampanya Önerisi">
                <button className="sales-btn-primary">Kampanya Oluştur</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

SalesTable.propTypes = {
  salesData: PropTypes.array.isRequired,
};

export default SalesTable;
