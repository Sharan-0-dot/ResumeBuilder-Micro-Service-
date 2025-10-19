package com.Sharan.Resume_Service.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInformation {

    @NotBlank(message = "Full name cannot be empty")
    private String fullName;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "please provide a valid email address")
    private String email;

    @NotBlank(message = "Phone number cannot be empty.")
    @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
    private String phone;
    private String linkedin;
    private String github;
    private String portfolio;
    private String location;
}
