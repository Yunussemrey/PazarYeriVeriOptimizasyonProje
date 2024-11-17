// src/pages/MarketplacePage/MarketplacePage.js
import React from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import MarketplaceSection from "../../components/MarketplaceSection/MarketplaceSection";
import "./MarketplacePage.css";

function MarketplacePage() {
  const trendyolProducts = [
    {
      name: "Trendyol Ürün 1",
      category: "Kadın Giyim",
      features: "Renk: Siyah, Beden: M",
      sales: 120,
      price: "99 TL",
      taxRate: "%18",
      orderStatus: "Onaylandı",
      remainingStock: 30,
    },
    {
      name: "Trendyol Ürün 2",
      category: "Erkek Ayakkabı",
      features: "Renk: Beyaz, Numara: 42",
      sales: 45,
      price: "149 TL",
      taxRate: "%18",
      orderStatus: "Kargoya Verildi",
      remainingStock: 12,
    },
  ];

  const hepsiburadaProducts = [
    {
      name: "Hepsiburada Ürün 1",
      category: "Elektronik",
      features: "Model: X, Renk: Siyah",
      sales: 85,
      price: "850 TL",
      taxRate: "%18",
      orderStatus: "Teslim Edildi",
      remainingStock: 5,
    },
    {
      name: "Hepsiburada Ürün 2",
      category: "Ev Dekorasyonu",
      features: "Malzeme: Ahşap, Renk: Kahverengi",
      sales: 20,
      price: "120 TL",
      taxRate: "%8",
      orderStatus: "Onay Bekliyor",
      remainingStock: 15,
    },
  ];

  const n11Products = [
    {
      name: "N11 Ürün 1",
      category: "Oyun & Hobi",
      features: "Yaş: 3+, Parça Sayısı: 1000",
      sales: 200,
      price: "45 TL",
      taxRate: "%18",
      orderStatus: "Kargoya Verildi",
      remainingStock: 100,
    },
    {
      name: "N11 Ürün 2",
      category: "Kozmetik",
      features: "Hacim: 50ml, Tür: Parfüm",
      sales: 75,
      price: "250 TL",
      taxRate: "%18",
      orderStatus: "Onaylandı",
      remainingStock: 8,
    },
  ];

  return (
    <>
      <Navbar />
      <div className="marketplace-page">
        <section className="marketplace-page-section">
          <MarketplaceSection
            title="Trendyol Ürün Yönetimi"
            products={trendyolProducts}
            link="https://www.trendyol.com"
          />
          <MarketplaceSection
            title="Hepsiburada Ürün Yönetimi"
            products={hepsiburadaProducts}
            link="https://www.hepsiburada.com"
          />
          <MarketplaceSection
            title="N11 Ürün Yönetimi"
            products={n11Products}
            link="https://www.n11.com"
          />
        </section>
      </div>
      <Footer />
    </>
  );
}

export default MarketplacePage;
