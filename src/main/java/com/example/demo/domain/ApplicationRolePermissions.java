package com.example.demo.domain;

public enum ApplicationRolePermissions {

    STUDENTS_READ("students:read"),
    STUDENTS_WRITE("students:write");

    private final String permission;

    ApplicationRolePermissions(final String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
