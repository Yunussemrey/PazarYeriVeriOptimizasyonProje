// src/components/About/About.js
import React from "react";
import "./About.css"; // Add custom styles
import AboutImage from "../../assets/images/logo3.png"; // Ensure the correct path for the image

function About() {
  return (
    <section className="about-section">
      <div className="about-container">
        <h2>Pazar Yeri Platformu</h2>
        <p>
          Tüm satış platformlarınızı tek bir yerde toplayın! Trendyol,
          Hepsiburada, N11 gibi büyük pazar yerlerine entegre olarak, tüm
          siparişlerinizi ve ürünlerinizi kolayca yönetin. İş süreçlerinizi
          optimize edin ve satışlarınızı artırın.
        </p>
        <img
          src={AboutImage}
          alt="Pazar Yeri Hakkında"
          className="about-image"
        />
      </div>
    </section>
  );
}

export default About;
