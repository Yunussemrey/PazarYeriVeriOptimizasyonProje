import React from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import AboutUsSection from "../../components/AboutUsSection/AboutUsSection";
import TeamSection from "../../components/TeamSection/TeamSection";

function AboutUsPage() {
  return (
    <div>
      <Navbar />
      <AboutUsSection />
      <TeamSection />
      <Footer />
    </div>
  );
}

export default AboutUsPage;
