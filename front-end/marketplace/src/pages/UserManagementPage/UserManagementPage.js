import React, { useEffect, useState } from "react";
import Navbar from "../../components/Navbar/Navbar";
import Footer from "../../components/Footer/Footer";
import UserCard from "../../components/UserCard/UserCard";
import "./UserManagementPage.css";

function UserManagementPage() {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    fetchAllUsers();
  }, []);

  // Function to fetch all users
  const fetchAllUsers = () => {
    const token = localStorage.getItem("token");

    if (!token) {
      alert("Lütfen giriş yapın.");
      window.location.href = "/login";
      return;
    }

    fetch("http://localhost:8080/api/users", {
      method: "GET",
      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Kullanıcıları alırken bir hata oluştu.");
        }
        return response.json();
      })
      .then((users) => {
        setUsers(users);
      })
      .catch((error) => {
        console.error("Error fetching users:", error);
        setError(error.message);
      });
  };

  // Functions for actions
  const handleMakeAdmin = (userId) => {
    console.log("Admin yap:", userId);
  };

  const handleRemoveAdmin = (userId) => {
    const token = localStorage.getItem("token");

    if (!token) {
      alert("Lütfen giriş yapın.");
      window.location.href = "/login";
      return;
    }

    if (
      window.confirm(
        "Kullanıcının adminliğini kaldırmak istediğinize emin misiniz?"
      )
    ) {
      fetch(`http://localhost:8080/api/users/${userId}/remove-admin`, {
        method: "PUT",
        headers: {
          Authorization: "Bearer " + token,
          "Content-Type": "application/json",
        },
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error(
              "Adminlikten çıkarma işlemi sırasında bir hata oluştu."
            );
          }
          return response.text();
        })
        .then((updatedUser) => {
          // Kullanıcı listesini güncelle
          setUsers((prevUsers) =>
            prevUsers.map((user) =>
              user.id === updatedUser.id ? updatedUser : user
            )
          );
          alert("Kullanıcı başarıyla adminlikten çıkarıldı.");
        })
        .catch((error) => {
          console.error("Error removing admin:", error);
          setError(error.message);
        });
    }
  };

  const handleDeleteUser = (userId) => {
    const token = localStorage.getItem("token");

    if (!token) {
      alert("Lütfen giriş yapın.");
      window.location.href = "/login";
      return;
    }

    if (window.confirm("Kullanıcıyı silmek istediğinize emin misiniz?")) {
      fetch(`http://localhost:8080/api/users/${userId}`, {
        method: "DELETE",
        headers: {
          Authorization: "Bearer " + token,
          "Content-Type": "application/json",
        },
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("Kullanıcıyı silerken bir hata oluştu.");
          }
          return response.text();
        })
        .then(() => {
          // Kullanıcıyı sildikten sonra kullanıcı listesini güncelle
          setUsers((prevUsers) =>
            prevUsers.filter((user) => user.id !== userId)
          );
          alert("Kullanıcı başarıyla silindi.");
        })
        .catch((error) => {
          console.error("Error deleting user:", error);
          setError(error.message);
        });
    }
  };

  return (
    <div className="main-container">
      <Navbar />
      <div className="user-management-page">
        <h3 className="user-management-title">Kullanıcı Yönetimi</h3>
        {error && <p className="error-message">{error}</p>}
        <div className="user-card-container">
          {users.map((user) => (
            <UserCard
              key={user.id}
              user={user}
              onMakeAdmin={handleMakeAdmin}
              onRemoveAdmin={handleRemoveAdmin}
              onDelete={handleDeleteUser}
            />
          ))}
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default UserManagementPage;
