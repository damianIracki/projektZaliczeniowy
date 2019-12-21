package pl.coderslab.app.validators;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.app.adnotations.IsUniqueEmail;
import pl.coderslab.app.adnotations.IsUniqueUsername;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IsUniqueEmailValidator implements ConstraintValidator<IsUniqueEmail, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(IsUniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }
}