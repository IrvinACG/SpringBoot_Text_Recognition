package com.iacg.app.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iacg.app.dto.TextDto;
import com.iacg.app.enums.Language;
import com.iacg.app.service.IRecognizeTextService;
import com.iacg.app.util.FileUtil;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * Service que contiene los metodos necesarios para procesar la logica del servicio
 * sobre el reconocimiento del texto
 * 
 * @author IACG
 */
@Slf4j
@Service
public class RecognizeTextService implements IRecognizeTextService{

	/**
	 * Variable tesseract
	 */
	@Autowired
	private Tesseract tesseract;
	
	/**
	 * Metodo que reconoce el texto, a partir de un archivo tipo imagen
	 * @param image archivo
	 * @param language idioma del texto
	 * @return TextDto respuesta
	 */
	@Override
	public TextDto recognize(MultipartFile image, Language language) {
		//Se crea instancia de DTO
		TextDto textDto = new TextDto();
		textDto.setFile(image.getOriginalFilename());
		
		//Se realiza conversion de archivos
		File imageFile = FileUtil.convert(image);
		//Variable donde se guarda texto
		String text = null;
		try {
			//Se agrega idioma
			tesseract.setLanguage(language.getValue());
			//Realza deteccion de texto en imagen
			text = tesseract.doOCR(imageFile);
			//Realiza filtro
			text = text.replace("\n", " ");
		} catch (TesseractException e) {
			log.error("Error al reconocer texto, error: {}",e.getMessage());
		}		
		textDto.setText(text);
		return textDto;
	}

}//Fin de clase
