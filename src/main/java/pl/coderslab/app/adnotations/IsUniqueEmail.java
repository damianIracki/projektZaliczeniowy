package pl.coderslab.app.adnotations;

import pl.coderslab.app.validators.IsUniqueEmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsUniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUniqueEmail {

    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
