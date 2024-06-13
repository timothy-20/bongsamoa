package com.timothy.bongsamoa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class TKUser {
    @Id
    private String id;
    @Column(name = "password")
    private String password;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;

    public TKUser() {
        this.id = "";
        this.password = "";
        this.username = "";
        this.email = "";
    }

    public TKUser(String id, String password, String username, String email) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }
}
