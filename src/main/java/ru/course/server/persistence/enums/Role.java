package ru.course.server.persistence.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    ADMIN("ADMIN"),


    USER("USER");

    private String caption;

    Role(String caption) {
        this.caption = caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String getAuthority() {
        return caption;
    }
}
