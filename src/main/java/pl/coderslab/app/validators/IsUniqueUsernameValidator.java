package pl.coderslab.app.validators;

import org.springframework.beans.factory.annotation.Autowired;
import pl.coderslab.app.adnotations.IsUniqueUsername;
import pl.coderslab.app.entities.User;
import pl.coderslab.app.repositories.UserRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;


public class IsUniqueUsernameValidator implements ConstraintValidator<IsUniqueUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(IsUniqueUsername constraintAnnotation) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext constraintValidatorContext) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(user.getUserName().equals(userName)){
                return false;
            }
        }
        return true;

    }
}
