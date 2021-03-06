package pl.coderslab.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.app.adnotations.IsConfirmPassword;
import pl.coderslab.app.adnotations.IsUniqueEmail;
import pl.coderslab.app.adnotations.IsUniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IsConfirmPassword(message = "Password must be that same")
public class UserDto {

    @IsUniqueUsername(message = "Username already exist")
    @Size(min = 5, max = 30, message = "Username is too short. Minimum length is 5 character")
    private String userName;

    @Size(min = 5, max = 20, message = "Password is to short. Minimum length is 5 character")
    private String password;


    private String repeatedPassword;

    @NotEmpty
    @Email(message = "Email form is not correct")
    @IsUniqueEmail(message = "Email is already used")
    private String email;
}
