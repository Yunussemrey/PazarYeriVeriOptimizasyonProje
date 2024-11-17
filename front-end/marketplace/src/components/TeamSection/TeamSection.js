import React from "react";
import "./TeamSection.css";
import TeamMember from "../TeamMember/TeamMember";

// Import images
import yunusEmre from "../../assets/images/yunus emre.png";
import erenDuzova from "../../assets/images/eren.png";
import seymaAkkus from "../../assets/images/seyma.png";
import baharAslan from "../../assets/images/bahar.png";
import sinanKosker from "../../assets/images/sinan.png";
import emineFistik from "../../assets/images/emine.png";
import arzuBudak from "../../assets/images/arzu.png";

function TeamSection() {
  const teamMembers = [
    { name: "Yunus Emre Akgül", role: "Junior Developer", image: yunusEmre },
    { name: "Eren Düzova", role: "Junior Developer", image: erenDuzova },
    { name: "Şeyma Akkuş", role: "Junior Developer", image: seymaAkkus },
    { name: "Bahar Aslan", role: "Junior Developer", image: baharAslan },
    { name: "Sinan Köşker", role: "Junior Developer", image: sinanKosker },
    { name: "Emine Fıstık", role: "Junior Developer", image: emineFistik },
    { name: "Arzu Budak", role: "Junior Developer", image: arzuBudak },
  ];

  return (
    <div className="team_section">
      <div className="container">
        <h1 className="team_title">Geliştirici Ekibimiz</h1>
        <p className="team_text">
          7 kişilik uzman geliştirici ekibimiz, platformumuzun sorunsuz
          çalışması için sürekli olarak çalışmaktadır. İşte ekibimizin üyeleri:
        </p>
        <div className="row">
          {teamMembers.map((member, index) => (
            <div key={index} className="col-lg-4 col-sm-6">
              <TeamMember
                name={member.name}
                role={member.role}
                image={member.image}
              />
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default TeamSection;
