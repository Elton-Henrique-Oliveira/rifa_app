package br.com.gogoplay.app.core.user.domain.entities;

import lombok.Data;

public enum UserRole {

    SUPER(0, "super"),

    OWNER(1, "owner"),

    ADMIN(2, "admin"),

    EMPLOYER(3, "employer");

    private int code;
    private String role;

    UserRole(int code, String role) {
        this.code = code;
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }

    public int getCode() {
        return this.code;
    }

    public static int getCodeByRole(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRole().equals(role)) {
                return userRole.getCode();
            }
        }
        throw new IllegalArgumentException("Role not found: " + role);
    }

    public static UserRole getByRole(String role) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getRole().equals(role)) {
                return userRole;
            }
        }
        throw new IllegalArgumentException("Role not found: " + role);
    }
}