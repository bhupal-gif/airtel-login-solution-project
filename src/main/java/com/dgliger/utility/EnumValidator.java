package com.dgliger.utility;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EnumValidatorImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValidator {
    Class enumClass();


    String message() default "Value is not part of the enumeration";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
