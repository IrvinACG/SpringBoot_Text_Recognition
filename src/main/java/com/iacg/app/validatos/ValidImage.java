package com.iacg.app.validatos;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Validador que comprueba que el archivo sea de tipo imagen
 * 
 * @author IACG
 */
@Documented
@Constraint(validatedBy = ImageValidator.class)
@Retention(RUNTIME)
@Target({PARAMETER, FIELD, ElementType.TYPE})
public @interface ValidImage {

	/**
	 * Variable message default
	 * @return String message
	 */
    String message() default "{com.iacg.app.validation.messages.validImage}";

    /**
     * Metodo groups default
     * @return Class<?>[] respuesta
     */
    Class<?>[] groups() default {};

    /**
     * Metodo payload default
     * @return Class<? extends Payload>[] respuesta
     */
    Class<? extends Payload>[] payload() default {};
	
}//Fin de clase
