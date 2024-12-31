package org.example.football_manager.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = SizeValidator.class)
public @interface SizeValidation {
    public String message() default "Invalid input values!";

    int min() default 0;

    int max() default 10;

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
