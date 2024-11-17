// src/components/Features/Features.js
import React from "react";
import "./Features.css"; // Add custom styles

// Import images
import FeatureImage1 from "../../assets/images/pazaryeri1.png";
import FeatureImage2 from "../../assets/images/pazaryei2.png";
import FeatureImage3 from "../../assets/images/pazaryeri3.png";

function Features() {
  return (
    <section className="feature-section">
      <div className="feature-container">
        <div className="feature">
          <div className="feature-image-container">
            <img
              src={FeatureImage1}
              alt="Satış Yönetimi"
              className="feature-image"
            />
          </div>
          <div className="feature-text">
            <h3>Satışlarınızı Kolayca Yönetin</h3>
            <p>
              Farklı platformlardaki satışlarınızı tek bir panel üzerinden takip
              edin. Siparişlerinizi yönetin, müşteri bilgilerine hızlıca erişin
              ve satış performansınızı anlık olarak izleyin.
            </p>
          </div>
        </div>

        <div className="feature">
          <div className="feature-image-container">
            <img
              src={FeatureImage2}
              alt="Stok Yönetimi"
              className="feature-image"
            />
          </div>
          <div className="feature-text">
            <h3>Verimli Stok Yönetimi</h3>
            <p>
              Tüm ürünlerinizi ve stok durumlarınızı kolayca yönetin. Otomatik
              stok güncellemeleri sayesinde stok sorunlarını ortadan kaldırın ve
              müşteri memnuniyetini artırın.
            </p>
          </div>
        </div>

        <div className="feature">
          <div className="feature-image-container">
            <img
              src={FeatureImage3}
              alt="Analiz ve Raporlama"
              className="feature-image"
            />
          </div>
          <div className="feature-text">
            <h3>Detaylı Analiz ve Raporlama</h3>
            <p>
              Satış verilerinizi analiz edin ve iş stratejilerinizi geliştirin.
              Detaylı raporlar sayesinde pazar trendlerini takip edin ve
              rekabette öne geçin.
            </p>
          </div>
        </div>
      </div>
    </section>
  );
}

export default Features;
