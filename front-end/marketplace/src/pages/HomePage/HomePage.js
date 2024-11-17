// src/pages/HomePage/HomePage.js
import React from "react";
import Navbar from "../../components/Navbar/Navbar";
import Slider from "../../components/Slider/Slider";
import About from "../../components/About/About";
import Features from "../../components/Features/Features";
import Footer from "../../components/Footer/Footer";

function HomePage() {
  return (
    <div>
      <Navbar />
      <Slider />
      <About />
      <Features />
      <Footer />
    </div>
  );
}

export default HomePage;
