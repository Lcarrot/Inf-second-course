package itis.Tyshenko.entity.users;

import lombok.Getter;

@Getter
public abstract class User {

    private String id;
    private String first_name;
    private String surname;
    private String login;
    private String email;
    private String country;
    private String hashPassword;
    private Boolean gender;
    private String role;
    private String organization;
}
