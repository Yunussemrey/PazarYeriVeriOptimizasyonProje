import React from "react";
import PropTypes from "prop-types";
import "./UserCard.css";

function UserCard({ user, onMakeAdmin, onRemoveAdmin, onDelete }) {
  const storeName = user.store ? user.store.name : "";

  return (
    <div className="user-card">
      <h5 className="user-card-name">
        {user.firstName} {user.lastName}
      </h5>
      <p className="user-card-info">
        <strong>Email:</strong> {user.email}
      </p>
      <p className="user-card-info">
        <strong>Rol:</strong> {user.role}
      </p>
      <p className="user-card-info">
        <strong>Mağaza:</strong> {storeName}
      </p>
      <div className="user-actions">
        <button
          className="user-card-btn user-card-btn-admin"
          onClick={() => onMakeAdmin(user.id)}
          aria-label="Make Admin"
        >
          Admin Yap
        </button>
        <button
          className="user-card-btn user-card-btn-remove-admin"
          onClick={() => onRemoveAdmin(user.id)}
          aria-label="Remove Admin"
        >
          Adminlikten Çıkar
        </button>
        <button
          className="user-card-btn user-card-btn-delete"
          onClick={() => onDelete(user.id)}
          aria-label="Delete User"
        >
          Sil
        </button>
      </div>
    </div>
  );
}

UserCard.propTypes = {
  user: PropTypes.object.isRequired,
  onMakeAdmin: PropTypes.func.isRequired,
  onRemoveAdmin: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
};

export default UserCard;
