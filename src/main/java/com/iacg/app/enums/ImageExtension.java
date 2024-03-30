package com.iacg.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que contiene las extension de imagenes
 * 
 * @author IACG
 */
@Getter
@AllArgsConstructor
public enum ImageExtension {
	
	/**
	 * Variable JPEG
	 */
	JPEG(".jpeg"),
	/**
	 * Variable JPG
	 */
	JPG(".jpg"),
	/**
	 * Variable PNG
	 */
	PNG(".png"),
	/**
	 * Variable WEBP
	 */
	TIFF(".tiff");
	
	/**
	 * Variable extension
	 */
	private String value;
	
}//Fin de clase
