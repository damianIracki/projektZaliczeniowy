package pl.coderslab.app.adnotations;

import pl.coderslab.app.validators.IsUniqueUsernameValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsUniqueUsernameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUniqueUsername {

    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[]payload() default {};
}
