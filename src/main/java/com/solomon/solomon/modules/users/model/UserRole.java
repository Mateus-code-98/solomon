package com.solomon.solomon.modules.users.model;

public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UserRole(String string) {
        this.role = string;
    }

    public String getRole() {
        return role;
    }
}
