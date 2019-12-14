package pl.coderslab.app.adnotations;

import pl.coderslab.app.validators.IsConfirmPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsConfirmPasswordValidator.class)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsConfirmPassword {

    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[]payload() default {};


}
