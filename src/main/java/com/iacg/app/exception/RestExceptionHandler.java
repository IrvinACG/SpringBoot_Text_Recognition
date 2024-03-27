package com.iacg.app.exception;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.iacg.app.exception.catalog.GeneralCatalog;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase se encarga de servir como apoyo al controller, manejando 
 * las excepciones esperadas en la aplicacion
 * 
 * @author IACG
 */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

	/**
	 * Manejo de excepciones personalizadas
	 * @param e Excepciones de tipo GeneralException
	 * @param request Peticion
	 * @return ResponseEntity<ModelException> Error
	 */
	@ExceptionHandler(value = {GeneralException.class})
	public ResponseEntity<DefaultError> handlerCustomException (CustomException e, HttpServletRequest request){
	    DefaultError exception = new DefaultError(e.getStatus(), 
	    												e.getCode(), 
													 	e.getMessage(), 
													 	e.getLevel(), 
													 	e.getDescription(), 
													 	request.getRequestURL().toString());
	    log.error("Error handlerCustomException: {}",exception.toString());
	    return ResponseEntity.status(e.getStatus()).body(exception);
	}
	
	/**
	 * Manejo de excepcion generica
	 * @param e Excepcion  de tipo Exception
	 * @param request Peticion
	 * @return ResponseEntity<ModelException> Error
	 */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DefaultError> handleGenericException(Exception e, HttpServletRequest request){
    	
    	DefaultError exception = new DefaultError(GeneralCatalog.GRAL001.getHtttpStatus().value(), 
    												  GeneralCatalog.GRAL001.getCode(), 
    												  e.getMessage(), 
    												  GeneralCatalog.GRAL001.getLevelException().toString(), 
    												  GeneralCatalog.GRAL001.getMessage(), 
    												  request.getRequestURL().toString());
	    log.error("Error handleGenericException: {}, class: {}",exception.toString(), e.getClass());
    	return ResponseEntity.status(GeneralCatalog.GRAL001.getHtttpStatus()).body(exception);
    }

    /**
     * Manejo de excepcion de metodo HTTP no soportado
     * @param e Excepcion tipo HttpRequestMethodNotSupportedException 
     * @param request Peticion
     * @return ResponseEntity<ModelException> Error
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<DefaultError> handlerMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
    	
    	DefaultError exception = new DefaultError(GeneralCatalog.GRAL007.getHtttpStatus().value(), 
													  GeneralCatalog.GRAL007.getCode(), 
													  e.getMessage(), 
													  GeneralCatalog.GRAL007.getLevelException().toString(),
													  GeneralCatalog.GRAL007.getMessage(),
													  request.getRequestURL().toString());
	    log.error("Error handleGenericException: {}",exception.toString());
    	return ResponseEntity.status(GeneralCatalog.GRAL007.getHtttpStatus()).body(exception);
    }
    
    /**
     * Manejo de excepcion de validacion de parametros ennviados
     * @param e Excepcion tipo MissingServletRequestParameterException, NumberFormatException
     * @param request Peticion
     * @return ResponseEntity<ModelException> Error
     */
	@ExceptionHandler(value = {MissingServletRequestParameterException.class,
							   NumberFormatException.class})
	public ResponseEntity<DefaultError> handleValidationException(Exception e, HttpServletRequest request) {
			
		DefaultError exception = new DefaultError(GeneralCatalog.GRAL002.getHtttpStatus().value(), 
													  GeneralCatalog.GRAL002.getCode(), 
													  null, 
													  GeneralCatalog.GRAL002.getLevelException().toString(), 
													  GeneralCatalog.GRAL002.getMessage(), 
													  request.getRequestURL().toString());
	    log.error("Error handleValidationException: {}",exception.toString());
    	return ResponseEntity.status(GeneralCatalog.GRAL002.getHtttpStatus()).body(exception);
    }
	
	/**
	 * Menejo de excepcion de validacion de argumentos de entrada
	 * @param e Excepcion de tipo MethodArgumentNotValidException
	 * @param request Peticion
	 * @return ResponseEntity<ModelException> Error
	 */
	@ExceptionHandler(value = {MethodArgumentNotValidException.class})
	public ResponseEntity<DefaultError> handleValidationExceptionParameters(MethodArgumentNotValidException e, HttpServletRequest request) {
		
		//Obtener errores
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        // Convetir errores a String
        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append("Parameter '" + f.getField() + "': " + f.getDefaultMessage() + " "));
		
		DefaultError exception = new DefaultError(GeneralCatalog.GRAL002.getHtttpStatus().value(), 
													  GeneralCatalog.GRAL002.getCode(), 
													  errorMessage.toString(), 
													  GeneralCatalog.GRAL002.getLevelException().toString(), 
													  GeneralCatalog.GRAL002.getMessage(), 
													  request.getRequestURL().toString());
	    log.error("Error handleValidationExceptionParameters: {}",exception.toString());
    	return ResponseEntity.status(GeneralCatalog.GRAL002.getHtttpStatus()).body(exception);
	}
	
	/**
	 * Manejo de excepcion para el tamanio maximo de archivos
	 * @param e Excepcion de tipo MaxUploadSizeExceededException
	 * @param request Peticion
	 * @return ResponseEntity<DefaultException> Error
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<DefaultError> handleValidationSizeFile(MaxUploadSizeExceededException e, HttpServletRequest request) {
		DefaultError exception = new DefaultError(GeneralCatalog.GRAL008.getHtttpStatus().value(), 
													  GeneralCatalog.GRAL008.getCode(), 
													  e.getMessage(), 
													  GeneralCatalog.GRAL008.getLevelException().toString(), 
													  GeneralCatalog.GRAL008.getMessage(), 
													  request.getRequestURL().toString());
	    log.error("Error handleValidationSizeFile: {}",exception.toString());
    	return ResponseEntity.status(GeneralCatalog.GRAL008.getHtttpStatus()).body(exception);
	}
	
	/**
	 * Manejo de excepcion para el tamanio maximo de archivos
	 * @param e Excepcion de tipo MethodArgumentTypeMismatchException
	 * @param request Peticion
	 * @return ResponseEntity<DefaultException> Error
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<DefaultError> handleValidationTypeMismatch(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
		DefaultError exception = new DefaultError(GeneralCatalog.GRAL002.getHtttpStatus().value(), 
													  GeneralCatalog.GRAL002.getCode(), 
													  "Parametro invalido: " + e.getPropertyName(), 
													  GeneralCatalog.GRAL002.getLevelException().toString(), 
													  GeneralCatalog.GRAL002.getMessage(), 
													  request.getRequestURL().toString());
	    log.error("Error handleValidationTypeMismatch: {}",exception.toString());
    	return ResponseEntity.status(GeneralCatalog.GRAL002.getHtttpStatus()).body(exception);
	}
	
	/**
	 * Manejo de excepcion para el tamanio maximo de archivos
	 * @param e Excepcion de tipo ConstraintViolationException
	 * @param request Peticion
	 * @return ResponseEntity<DefaultException> Error
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<DefaultError> handleValidationConstrainViolation(ConstraintViolationException e, HttpServletRequest request) {
		String msgError = null;
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            // Obtén el valor del error
        	msgError = violation.getMessage();
        }
		DefaultError exception = new DefaultError(GeneralCatalog.GRAL002.getHtttpStatus().value(), 
													  GeneralCatalog.GRAL002.getCode(), 
													  msgError, 
													  GeneralCatalog.GRAL002.getLevelException().toString(), 
													  GeneralCatalog.GRAL002.getMessage(), 
													  request.getRequestURL().toString());
	    log.error("Error handleValidationTypeMismatch: {}",exception.toString());
    	return ResponseEntity.status(GeneralCatalog.GRAL002.getHtttpStatus()).body(exception);
	}
	
}//Fin de clase
