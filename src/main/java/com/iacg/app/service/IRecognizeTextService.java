package com.iacg.app.service;

import org.springframework.web.multipart.MultipartFile;

import com.iacg.app.dto.TextDto;
import com.iacg.app.enums.Language;

/**
 * Interface que contiene los metodos necesarios para procesar la logica del servicio
 * sobre el reconocimiento del texto
 * 
 * @author IACG
 */
public interface IRecognizeTextService {

	/**
	 * Metodo que reconoce el texto, a partir de un archivo tipo imagen
	 * @param image archivo
	 * @param language idioma del texto
	 * @return TextDto respuesta
	 */
	TextDto recognize(MultipartFile image, Language language);
	
}//Fin de clase
