package com.yazilimciAkademisi.marketplace.dto.response;

import com.yazilimciAkademisi.marketplace.entity.enums.Role;

public class UserResponseDTO {

    private Integer id;
    private String username;
    private String password;
    private String email;
    private Role role;
    private StoreResponseDTO store;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public StoreResponseDTO getStore() {
        return store;
    }

    public void setStore(StoreResponseDTO store) {
        this.store = store;
    }
}
