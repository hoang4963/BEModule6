package com.codegym.webthuenha.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {

    @Size(min = 6, max = 18, message = "Username must be between 6 and 18 characters")
    private String userName;

    @Size(min = 6, max = 18, message = "Username must be between 6 and 18 characters")
    private String password;

//    @Email( message = "Please enter the correct email format")
    private String email;
    @Pattern(regexp = "((09|03|07|08|05)+([0-9]{8})\\b)", message = "Please enter the correct phone number format")
    private String phone;
    private String confirmPassword;
}
