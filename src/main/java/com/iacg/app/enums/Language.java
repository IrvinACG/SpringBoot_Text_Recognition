package com.iacg.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum que contiene los idiomas disponibles
 * 
 * @author IACG
 */
@Getter
@AllArgsConstructor
public enum Language {
	
	/**
	 * Variable CHI: Chino
	 */
	CHI("chi_sim"),
	/**
	 * Variable DEU: Aleman
	 */
	DEU("deu"),
	/**
	 * Variable ENG: Ingles
	 */
	ENG("eng"),
	/**
	 * Variable FRA: Frances
	 */
	FRA("fra"),
	/**
	 * Variable JPN: Japones
	 */
	JPN("jpn"),
	/**
	 * Variable KOR: Koreano
	 */
	KOR("kor"),
	/**
	 * Variable POR: Portugues
	 */
	POR("por"),
	/**
	 * Variable RUS: Ruso
	 */
	RUS("rus"),
	/**
	 * Variable SPA: Espanol
	 */
	SPA("spa");
	
	/**
	 * Variable value
	 */
	private String value;
}//Fin de clase
