package ru.course.server.persistence.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrUserDto {

    private String username;

    private String email;

    private String password;

    private String firstName;

    private String middleName;

    @Override
    public String toString() {
        return "{email=" + email  + ", password= ******}";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
