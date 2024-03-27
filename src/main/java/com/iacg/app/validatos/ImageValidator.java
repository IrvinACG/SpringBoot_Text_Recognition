package com.iacg.app.validatos;

import org.springframework.web.multipart.MultipartFile;

import com.iacg.app.enums.ImageExtension;
import com.iacg.app.util.FileUtil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/**
 * Clase que valida si un archivo de tipo Imagen
 * 
 * @author IACG
 */
public class ImageValidator implements ConstraintValidator<ValidImage, MultipartFile>{

	/**
	 * Metodo que valida si un archivo MultiparFile, es de tipo imagen
	 * @param value Archivo
	 * @param context Contexto
	 * @return boolean respuesta
	 */
	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		//Recuperar extension de archivo
		String extension = FileUtil.getFileExtension(value);
		//Iterar para saber si archivo es valido
		for(ImageExtension imgExt : ImageExtension.values()) {
			if(extension.equalsIgnoreCase(imgExt.getValue())) {
				return true; //Archivo valido
			}
		}
		//Archivo invalido
		return false;
	}

}//Fin de clase