package com.yazilimciAkademisi.marketplace.dto.response;

import com.yazilimciAkademisi.marketplace.entity.enums.Role;

public class UserResponseDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private StoreResponseDTO store;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
