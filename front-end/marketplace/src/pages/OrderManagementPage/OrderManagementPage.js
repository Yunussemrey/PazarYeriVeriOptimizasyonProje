import React, { useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import OrderTable from "../../components/OrderTable/OrderTable";
import "./OrderManagementPage.css";

function OrderManagementPage() {
  const [orders, setOrders] = useState([
    {
      id: 1,
      productName: "Ürün 1",
      category: "Kategori A",
      features: "Renk: Kırmızı, Boyut: M",
      salesQuantity: 10,
      price: "100 TL",
      taxRate: "%18",
      orderStatus: "Stokta Var",
      orderAddress: "İstanbul",
      shippingStatus: "Kargoya Verildi",
      customerPhone: "0555 555 55 55",
    },
    {
      id: 2,
      productName: "Ürün 2",
      category: "Kategori B",
      features: "Renk: Mavi, Boyut: L",
      salesQuantity: 5,
      price: "200 TL",
      taxRate: "%8",
      orderStatus: "Hazırlanıyor",
      orderAddress: "Ankara",
      shippingStatus: "Kargoya Hazırlanıyor",
      customerPhone: "0555 555 55 56",
    },
    {
      id: 3,
      productName: "Ürün 3",
      category: "Kategori C",
      features: "Renk: Siyah, Boyut: S",
      salesQuantity: 3,
      price: "300 TL",
      taxRate: "%18",
      orderStatus: "Teslim Edildi",
      orderAddress: "İzmir",
      shippingStatus: "Teslim Edildi",
      customerPhone: "0555 555 55 57",
    },
    {
      id: 4,
      productName: "Ürün 4",
      category: "Kategori D",
      features: "Renk: Beyaz, Boyut: XL",
      salesQuantity: 8,
      price: "400 TL",
      taxRate: "%18",
      orderStatus: "İptal Edildi",
      orderAddress: "Bursa",
      shippingStatus: "İptal Edildi",
      customerPhone: "0555 555 55 58",
    },
    {
      id: 5,
      productName: "Ürün 5",
      category: "Kategori E",
      features: "Renk: Yeşil, Boyut: L",
      salesQuantity: 12,
      price: "120 TL",
      taxRate: "%8",
      orderStatus: "Beklemede",
      orderAddress: "Antalya",
      shippingStatus: "Hazırlanıyor",
      customerPhone: "0555 555 55 59",
    },
  ]);

  const handleCancel = (orderId) => {
    console.log(`Order ${orderId} cancelled.`);
  };

  const handleReturn = (orderId) => {
    console.log(`Order ${orderId} returned.`);
  };

  return (
    <div className="main-container">
      <Navbar />
      <div className="orderManagement-content-container">
        <OrderTable
          orders={orders}
          handleCancel={handleCancel}
          handleReturn={handleReturn}
        />
      </div>
      <Footer />
    </div>
  );
}

export default OrderManagementPage;
