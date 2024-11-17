import React from "react";

function TeamMember({ name, role, image }) {
  return (
    <div className="team_member">
      <img src={image} alt={name} className="img-fluid" />
      <h4 className="team_member_name">{name}</h4>
      <p className="team_member_role">{role}</p>
    </div>
  );
}

export default TeamMember;
