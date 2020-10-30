package itis.Tyshenko.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {

    private String login;
    private String email;
    private String country;
    private String password;
    private String gender;
}
