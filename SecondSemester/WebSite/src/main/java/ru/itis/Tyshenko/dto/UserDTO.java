package ru.itis.Tyshenko.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    public Long id;
    public String login;
    public String email;
    public String country;
    public String password;
    public String gender;
}
