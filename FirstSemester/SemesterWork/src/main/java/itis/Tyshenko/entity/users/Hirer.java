package itis.Tyshenko.entity.users;

import lombok.*;

@Getter
public class Hirer extends User {
    private long id;
    private String first_name;
    private String surname;
    private String login;
    private String email;
    private String country;
    private String hashPassword;
    private boolean gender;
    private String org;
}
