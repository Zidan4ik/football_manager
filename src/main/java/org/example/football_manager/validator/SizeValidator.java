package org.example.football_manager.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class SizeValidator implements ConstraintValidator<SizeValidation, Integer> {
    private int min;
    private int max;

    @Override
    public void initialize(SizeValidation constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value < min || value > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    context.getDefaultConstraintMessageTemplate()
                            .replace("{min}", String.valueOf(min))
                            .replace("{max}", String.valueOf(max))
            ).addConstraintViolation();
            return false;
        }
        return true;
    }
}
