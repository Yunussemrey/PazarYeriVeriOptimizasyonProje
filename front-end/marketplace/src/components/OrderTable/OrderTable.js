import React from "react";
import PropTypes from "prop-types";
import "./OrderTable.css";

function OrderTable({ orders, handleCancel, handleReturn }) {
  return (
    <div className="orderManagement-table-responsive">
      <table className="orderManagement-table">
        <thead>
          <tr>
            <th>Ürün Adı</th>
            <th>Kategori</th>
            <th>Özellikler</th>
            <th>Satış Adedi</th>
            <th>Fiyat</th>
            <th>KDV Oranı</th>
            <th>Sipariş Durumu</th>
            <th>Sipariş Adresi</th>
            <th>Kargo Durumu</th>
            <th>Müşteri Telefonu</th>
            <th>İptal</th>
            <th>İade</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr key={order.id}>
              <td>{order.productName}</td>
              <td>{order.category}</td>
              <td>{order.features}</td>
              <td>{order.salesQuantity}</td>
              <td>{order.price}</td>
              <td>{order.taxRate}</td>
              <td>{order.orderStatus}</td>
              <td>{order.orderAddress}</td>
              <td>{order.shippingStatus}</td>
              <td>{order.customerPhone}</td>
              <td>
                <button
                  className="orderManagement-btn orderManagement-btn-danger"
                  onClick={() => handleCancel(order.id)}
                  aria-label={`Cancel order ${order.id}`}
                >
                  İptal Et
                </button>
              </td>
              <td>
                <button
                  className="orderManagement-btn orderManagement-btn-warning"
                  onClick={() => handleReturn(order.id)}
                  aria-label={`Return order ${order.id}`}
                >
                  İade Al
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

OrderTable.propTypes = {
  orders: PropTypes.array.isRequired,
  handleCancel: PropTypes.func.isRequired,
  handleReturn: PropTypes.func.isRequired,
};

export default OrderTable;
