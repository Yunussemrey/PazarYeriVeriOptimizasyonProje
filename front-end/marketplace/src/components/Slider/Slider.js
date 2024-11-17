// src/components/Slider/Slider.js
import React, { useState, useEffect } from "react";
import "./Slider.css";

// Import the images directly from the assets folder
import Slide1 from "../../assets/images/1.png";
import Slide2 from "../../assets/images/2.png";
import Slide3 from "../../assets/images/3.png";

const images = [Slide1, Slide2, Slide3];

function Slider() {
  const [currentSlide, setCurrentSlide] = useState(0);

  // Preload images
  useEffect(() => {
    images.forEach((src) => {
      const img = new Image();
      img.src = src;
    });
  }, []);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentSlide((prevSlide) => (prevSlide + 1) % images.length);
    }, 5000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="slider">
      <img
        src={images[currentSlide]}
        alt={`Slide ${currentSlide + 1}`}
        className="active"
      />
    </div>
  );
}

export default Slider;
