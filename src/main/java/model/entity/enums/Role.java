package model.entity.enums;

public enum Role {
    ADMIN, USER, GUEST;

    public String getAuthority() {
        return name();
    }
}
