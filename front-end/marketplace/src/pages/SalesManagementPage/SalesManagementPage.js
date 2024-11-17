// src/pages/SalesManagementPage/SalesManagementPage.js
import React from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import SalesTable from "../../components/SalesTable/SalesTable";
import "./SalesManagementPage.css";

const SalesManagementPage = () => {
  const salesData = [
    {
      productName: "Ürün 1",
      category: "Kategori A",
      salesCount: 50,
      unitPrice: 200,
      vatRate: 18,
      totalAmount: 11800,
      profitLoss: "Kar",
      returnRate: 5,
      marketplace: "Trendyol",
      salesTrend: "En çok satılan zaman: Ağustos 2024",
      campaignSuggestion: true,
    },
    {
      productName: "Ürün 2",
      category: "Kategori B",
      salesCount: 20,
      unitPrice: 150,
      vatRate: 18,
      totalAmount: 3540,
      profitLoss: "Zarar",
      returnRate: 12,
      marketplace: "Trendyol",
      salesTrend: "En az satılan zaman: Temmuz 2024",
      campaignSuggestion: true,
    },
    {
      productName: "Ürün 3",
      category: "Kategori C",
      salesCount: 35,
      unitPrice: 180,
      vatRate: 18,
      totalAmount: 7434,
      profitLoss: "Kar",
      returnRate: 7,
      marketplace: "Hepsiburada",
      salesTrend: "En çok satılan zaman: Eylül 2024",
      campaignSuggestion: false,
    },
    {
      productName: "Ürün 4",
      category: "Kategori D",
      salesCount: 15,
      unitPrice: 220,
      vatRate: 18,
      totalAmount: 3894,
      profitLoss: "Zarar",
      returnRate: 15,
      marketplace: "N11",
      salesTrend: "En az satılan zaman: Haziran 2024",
      campaignSuggestion: true,
    },
    // Add more sample data as needed
  ];

  return (
    <div className="main-container">
      <Navbar />
      <div className="sales-content-container">
        <h3 className="sales-title">Satış Yönetimi</h3>
        <SalesTable salesData={salesData} />
      </div>
      <Footer />
    </div>
  );
};

export default SalesManagementPage;
