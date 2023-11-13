package br.com.gogoplay.app.core.user.domain.entities;

import lombok.Data;

public enum UserRole{
    ADMIN("admin"),

    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return this.role;
    }
}