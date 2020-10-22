package itis.Tyshenko.entity.users;

import lombok.*;

@Builder
@ToString
@Getter
@Setter
public class Hired extends User {
    private long id;
    private String name;
    private String surname;
    private String login;
    private String email;
    private String country;
    private String hashPassword;
    private boolean gender;
}
