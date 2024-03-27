package com.iacg.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * DTO que contiene los campos del texto
 * 
 * @author IACG
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TextDto implements Serializable{
	
	/**
	 * Variable serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable file
	 */
	private String file;
	
	/**
	 * Variable text
	 */
	private String text;
	
}//Fin de clase
