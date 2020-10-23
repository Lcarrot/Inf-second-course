package itis.Tyshenko.entity.users;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
public class Hired extends User {
    private long id;
    private String first_name;
    private String surname;
    private String login;
    private String email;
    private String country;
    private String hashPassword;
    private boolean gender;
}
