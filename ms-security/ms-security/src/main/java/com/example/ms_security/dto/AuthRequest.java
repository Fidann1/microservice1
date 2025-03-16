package com.example.ms_security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
   // @Email(message = "Not valid email format")
    private String email;
   // @NotBlank(message= "Password can not be blank")
    private String password;
}
