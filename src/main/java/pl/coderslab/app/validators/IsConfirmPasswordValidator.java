package pl.coderslab.app.validators;

import pl.coderslab.app.adnotations.IsConfirmPassword;
import pl.coderslab.app.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsConfirmPasswordValidator implements ConstraintValidator<IsConfirmPassword, UserDto> {

    @Override
    public void initialize(IsConfirmPassword constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserDto userDTO, ConstraintValidatorContext constraintValidatorContext) {
        return userDTO.getPassword().equals(userDTO.getRepeatedPassword());
    }
}
