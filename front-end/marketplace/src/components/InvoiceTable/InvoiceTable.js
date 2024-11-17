import React from "react";
import PropTypes from "prop-types";
import ActionButtons from "../ActionButtons/ActionButtons";
import "./InvoiceTable.css";

function InvoiceTable({ invoices }) {
  return (
    <div className="invoice-table-responsive">
      <table className="invoice-table">
        <thead>
          <tr>
            <th>ETTN</th>
            <th>Fatura Tarihi</th>
            <th>Alıcı Adı</th>
            <th>Tutar</th>
            <th>KDV</th>
            <th>Genel Toplam</th>
            <th>Durum</th>
            <th>Fatura Türü</th>
            <th>İşlem Türü</th>
            <th>Pazar Yeri</th>
            <th>Mağaza Adı</th>
            <th>İşlemler</th>
          </tr>
        </thead>
        <tbody>
          {invoices.map((invoice) => (
            <tr key={invoice.id}>
              <td>{invoice.ettin}</td>
              <td>{invoice.invoiceDate}</td>
              <td>{invoice.receiverName}</td>
              <td>{invoice.amount}</td>
              <td>{invoice.vat}</td>
              <td>{invoice.total}</td>
              <td>{invoice.status}</td>
              <td>{invoice.invoiceType}</td>
              <td>{invoice.transactionType}</td>
              <td>{invoice.marketplace}</td>
              <td>{invoice.storeName}</td>
              <td>
                <ActionButtons />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

InvoiceTable.propTypes = {
  invoices: PropTypes.array.isRequired,
};

export default InvoiceTable;
