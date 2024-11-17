import React, { useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import ProductTable from "../../components/ProductTable/ProductTable";
import ProductModal from "../../components/ProductModal/ProductModal";
import "./ProductsSectionPage.css";

const ProductsSectionPage = () => {
  const [products, setProducts] = useState([
    {
      name: "Samsung Galaxy S21",
      code: "SGS21",
      stock: 15,
      price: 10000,
      description: "Yeni nesil Samsung akıllı telefon.",
      brand: "Samsung",
      category: "Elektronik",
    },
    {
      name: "Apple MacBook Pro 16",
      code: "MBP16",
      stock: 8,
      price: 25000,
      description: "Yüksek performanslı Apple dizüstü bilgisayar.",
      brand: "Apple",
      category: "Bilgisayar",
    },
    {
      name: "Sony WH-1000XM4",
      code: "WH1000XM4",
      stock: 20,
      price: 3000,
      description: "Kablosuz kulak üstü gürültü engelleyici kulaklık.",
      brand: "Sony",
      category: "Elektronik",
    },
    {
      name: "Nike Air Zoom Pegasus",
      code: "NAZP",
      stock: 50,
      price: 800,
      description: "Ergonomik koşu ayakkabısı.",
      brand: "Nike",
      category: "Giyim",
    },
    {
      name: "Philips Hue LED Ampul",
      code: "PHLED",
      stock: 100,
      price: 150,
      description: "Akıllı ışık kontrollü LED ampul.",
      brand: "Philips",
      category: "Ev & Bahçe",
    },
    {
      name: "Logitech MX Master 3",
      code: "MXM3",
      stock: 35,
      price: 800,
      description: "Yüksek hassasiyetli kablosuz fare.",
      brand: "Logitech",
      category: "Bilgisayar",
    },
  ]);

  const [showModal, setShowModal] = useState(false);
  const [newProduct, setNewProduct] = useState({
    name: "",
    code: "",
    stock: "",
    price: "",
    description: "",
    brand: "",
    category: "Elektronik",
  });

  // State for Add Stock Modal
  const [showAddStockModal, setShowAddStockModal] = useState(false);
  const [selectedProductIndex, setSelectedProductIndex] = useState(null);
  const [stockToAdd, setStockToAdd] = useState(0);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewProduct({ ...newProduct, [name]: value });
  };

  const handleAddProduct = () => {
    setProducts([...products, newProduct]);
    setShowModal(false);
    setNewProduct({
      name: "",
      code: "",
      stock: "",
      price: "",
      description: "",
      brand: "",
      category: "Elektronik",
    });
  };

  // Add Stock Handlers
  const handleAddStockClick = (index) => {
    setSelectedProductIndex(index);
    setShowAddStockModal(true);
  };

  const handleAddStock = () => {
    if (selectedProductIndex !== null) {
      const updatedProducts = [...products];
      updatedProducts[selectedProductIndex].stock =
        parseInt(updatedProducts[selectedProductIndex].stock) +
        parseInt(stockToAdd);
      setProducts(updatedProducts);
      setShowAddStockModal(false);
      setStockToAdd(0);
    }
  };

  const handleDeleteProduct = (index) => {
    const updatedProducts = [...products];
    updatedProducts.splice(index, 1);
    setProducts(updatedProducts);
  };

  const handleEditProduct = (index) => {
    // Implement edit functionality if needed
    console.log("Edit product at index:", index);
  };
  return (
    <div className="main-container">
      <Navbar />
      <section className="product-section">
        <div className="product-container">
          <h2 className="product-title">Depodaki Ürünleriniz</h2>
          <button
            className="product-btn-add"
            onClick={() => setShowModal(true)}
          >
            Ürün Ekle
          </button>
          <ProductTable
            products={products}
            onEdit={handleEditProduct}
            onDelete={handleDeleteProduct}
            onAddStock={handleAddStockClick}
          />
        </div>
        {/* Existing ProductModal for adding new product */}
        <ProductModal
          showModal={showModal}
          onClose={() => setShowModal(false)}
          onSubmit={handleAddProduct}
          productData={newProduct}
          handleInputChange={handleInputChange}
        />
        {/* Add Stock Modal */}
        {showAddStockModal && (
          <div className="product-modal-overlay">
            <div className="product-modal-dialog">
              <div className="product-modal-content">
                <div className="product-modal-header">
                  <h5 className="product-modal-title">Stok Ekle</h5>
                  <button
                    type="button"
                    className="product-btn-close"
                    onClick={() => setShowAddStockModal(false)}
                  >
                    &times;
                  </button>
                </div>
                <div className="product-modal-body">
                  <p>
                    <strong>{products[selectedProductIndex]?.name}</strong>{" "}
                    ürününe eklenecek stok miktarını giriniz:
                  </p>
                  <input
                    type="number"
                    className="product-form-control"
                    value={stockToAdd}
                    onChange={(e) => setStockToAdd(e.target.value)}
                    min="0"
                  />
                </div>
                <div className="product-modal-footer">
                  <button
                    type="button"
                    className="product-btn-secondary"
                    onClick={() => setShowAddStockModal(false)}
                  >
                    İptal
                  </button>
                  <button
                    type="button"
                    className="product-btn-primary"
                    onClick={handleAddStock}
                  >
                    Stok Ekle
                  </button>
                </div>
              </div>
            </div>
          </div>
        )}
      </section>
      <Footer />
    </div>
  );
};

export default ProductsSectionPage;
