import React from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import "./CategoryBrandManagementPage.css";

const CategoryBrandManagementPage = () => {
  return (
    <>
      <Navbar />
      <div className="category-content-container">
        {/* Large Title */}
        <h1 className="page-title">Kategori ve Marka Yönetimi</h1>

        {/* Forms for adding category and brand */}
        <div className="category-form-container">
          <div className="form-box">
            <h4>Kategori Ekle</h4>
            <input
              type="text"
              placeholder="Üst Kategori Adı"
              className="category-form-control"
            />
            <input
              type="text"
              placeholder="Kategori Adı"
              className="category-form-control"
            />
            <button className="category-btn-primary category-btn">
              Kategori Ekle
            </button>
          </div>
          <div className="form-box">
            <h4>Marka Ekle</h4>
            <input
              type="text"
              placeholder="Marka Adı"
              className="category-form-control"
            />
            <button className="category-btn-primary category-btn">
              Marka Ekle
            </button>
          </div>
        </div>

        {/* Tables for displaying categories and brands */}
        <div className="category-table-container">
          <div className="table-box">
            <h4>Eklenen Kategoriler</h4>
            <div className="category-table-responsive">
              <table className="category-table-dark">
                <thead>
                  <tr>
                    <th>Üst Kategori</th>
                    <th>Alt Kategori</th>
                    <th>İşlemler</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Kategori A</td>
                    <td>Alt Kategori A1</td>
                    <td className="category-action-buttons">
                      <button className="category-btn-primary category-btn-sm">
                        Güncelle
                      </button>
                      <button className="category-btn-danger category-btn-sm">
                        Sil
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div className="table-box">
            <h4>Eklenen Markalar</h4>
            <div className="category-table-responsive">
              <table className="category-table-dark">
                <thead>
                  <tr>
                    <th>Marka Adı</th>
                    <th>İşlemler</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Marka 1</td>
                    <td className="category-action-buttons">
                      <button className="category-btn-primary category-btn-sm">
                        Güncelle
                      </button>
                      <button className="category-btn-danger category-btn-sm">
                        Sil
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
};

export default CategoryBrandManagementPage;
