import React, { useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import InvoiceSearchForm from "../../components/InvoiceSearchForm/InvoiceSearchForm";
import InvoiceTable from "../../components/InvoiceTable/InvoiceTable";
import "./EfaturaPage.css";

const EfaturaPage = () => {
  const [formData, setFormData] = useState({
    ettin: "",
    receiverName: "",
    invoiceDate: "",
    status: "all",
    invoiceType: "all",
    marketplace: "all",
    storeName: "store1",
  });

  // Extended sample invoice data for a fuller table
  const [invoices, setInvoices] = useState([
    {
      id: 1,
      ettin: "INV202409261",
      invoiceDate: "26/09/2024",
      receiverName: "John Doe",
      amount: "500.00",
      vat: "90.00",
      total: "590.00",
      status: "Onaylı",
      invoiceType: "E-Fatura",
      transactionType: "Satış",
      marketplace: "Trendyol",
      storeName: "Mağaza 1",
    },
    {
      id: 2,
      ettin: "INV202409262",
      invoiceDate: "27/09/2024",
      receiverName: "Jane Smith",
      amount: "750.00",
      vat: "135.00",
      total: "885.00",
      status: "Beklemede",
      invoiceType: "E-Arşiv",
      transactionType: "Satış",
      marketplace: "Hepsiburada",
      storeName: "Mağaza 2",
    },
    {
      id: 3,
      ettin: "INV202409263",
      invoiceDate: "28/09/2024",
      receiverName: "Alice Johnson",
      amount: "300.00",
      vat: "54.00",
      total: "354.00",
      status: "Reddedildi",
      invoiceType: "E-Fatura",
      transactionType: "İade",
      marketplace: "N11",
      storeName: "Mağaza 3",
    },
    {
      id: 4,
      ettin: "INV202409264",
      invoiceDate: "29/09/2024",
      receiverName: "Michael Brown",
      amount: "1200.00",
      vat: "216.00",
      total: "1416.00",
      status: "Onaylı",
      invoiceType: "E-Arşiv",
      transactionType: "Satış",
      marketplace: "Trendyol",
      storeName: "Mağaza 1",
    },
    {
      id: 5,
      ettin: "INV202409265",
      invoiceDate: "30/09/2024",
      receiverName: "Patricia Wilson",
      amount: "450.00",
      vat: "81.00",
      total: "531.00",
      status: "Beklemede",
      invoiceType: "E-Fatura",
      transactionType: "İade",
      marketplace: "Hepsiburada",
      storeName: "Mağaza 2",
    },
    {
      id: 6,
      ettin: "INV202409266",
      invoiceDate: "01/10/2024",
      receiverName: "Thomas Anderson",
      amount: "680.00",
      vat: "122.40",
      total: "802.40",
      status: "Onaylı",
      invoiceType: "E-Fatura",
      transactionType: "Satış",
      marketplace: "N11",
      storeName: "Mağaza 3",
    },
    {
      id: 7,
      ettin: "INV202409267",
      invoiceDate: "02/10/2024",
      receiverName: "Nancy Roberts",
      amount: "1500.00",
      vat: "270.00",
      total: "1770.00",
      status: "Beklemede",
      invoiceType: "E-Arşiv",
      transactionType: "Satış",
      marketplace: "Trendyol",
      storeName: "Mağaza 1",
    },
    {
      id: 8,
      ettin: "INV202409268",
      invoiceDate: "03/10/2024",
      receiverName: "William Clark",
      amount: "1100.00",
      vat: "198.00",
      total: "1298.00",
      status: "Reddedildi",
      invoiceType: "E-Fatura",
      transactionType: "İade",
      marketplace: "Hepsiburada",
      storeName: "Mağaza 2",
    },
    {
      id: 9,
      ettin: "INV202409269",
      invoiceDate: "04/10/2024",
      receiverName: "Elizabeth Lewis",
      amount: "900.00",
      vat: "162.00",
      total: "1062.00",
      status: "Onaylı",
      invoiceType: "E-Fatura",
      transactionType: "Satış",
      marketplace: "N11",
      storeName: "Mağaza 3",
    },
    {
      id: 10,
      ettin: "INV202409270",
      invoiceDate: "05/10/2024",
      receiverName: "James King",
      amount: "640.00",
      vat: "115.20",
      total: "755.20",
      status: "Beklemede",
      invoiceType: "E-Arşiv",
      transactionType: "Satış",
      marketplace: "Trendyol",
      storeName: "Mağaza 1",
    },
  ]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevState) => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Search Data:", formData);
    // Implement search functionality here
  };

  return (
    <div className="main-container">
      <Navbar />
      <div className="efatura-content-container">
        <h2 className="efatura-title">E-Fatura Yönetimi</h2>
        <InvoiceSearchForm
          formData={formData}
          handleChange={handleChange}
          handleSubmit={handleSubmit}
        />
        <InvoiceTable invoices={invoices} />
      </div>
      <Footer />
    </div>
  );
};

export default EfaturaPage;
