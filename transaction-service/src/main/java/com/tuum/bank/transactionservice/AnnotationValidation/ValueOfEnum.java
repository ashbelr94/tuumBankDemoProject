/**
 * 
 */
package com.tuum.bank.transactionservice.AnnotationValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, METHOD, PARAMETER, CONSTRUCTOR, ANNOTATION_TYPE, TYPE_USE })
@NotNull(message = "Value cannot be null")
@Constraint(validatedBy = ValueOfEnumValidator.class)
@ReportAsSingleViolation
/**
 * @author Ashbel Reinhard
 *
 */
public @interface ValueOfEnum {
	  Class<? extends Enum<?>> enumClazz();

	  String message() default "Value is not valid";

	  Class<?>[] groups() default {};

	  Class<? extends Payload>[] payload() default {};
}
