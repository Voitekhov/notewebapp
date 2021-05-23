package net.ru.voitekhov.notewebapp.model;

public enum Permission {
    COMMON_ACCESS("common:access"),
    EXTENDED_ACCESS("extended:access");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
