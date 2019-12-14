package pl.coderslab.app.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.app.adnotations.IsConfirmPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IsConfirmPassword(message = "Password must be that same")
public class UserDto {

    @NotEmpty
    @Size(min = 5, max = 30, message = "userName is too short. Minimum length is 5 character")
    private String userName;

    @NotEmpty
    @Size(min = 5, max = 20)
    private String password;


    private String repeatedPassword;

    @NotEmpty
    @Email
    private String email;
}
