package com.iacg.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.iacg.app.dto.TextDto;
import com.iacg.app.dto.res.ResponseDto;
import com.iacg.app.dto.res.ResponseGenericDto;
import com.iacg.app.enums.Language;
import com.iacg.app.service.IRecognizeTextService;
import com.iacg.app.util.InfoApi;
import com.iacg.app.validatos.ValidImage;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

/**
 * Clase de Controller que contiene los metodos, para el funcionamiento
 * de la API Text
 * 
 * @author IACG
 */
@Tag(name = "Text")
@Slf4j
@Validated
@RestController
@RequestMapping("/api/v1/text")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class TextController {

	/**
	 * Variable service
	 */
	@Autowired
	private IRecognizeTextService service;
	
	/**
	 * Endpoint que permite obtener el texto a traves de una imagen
	 * @param image Archivo de imagen
	 * @param language Idioma del texto
	 * @return ResponseEntity<ResponseGenericDto<TextDto>> Respuesta
	 */
	@Operation(summary = InfoApi.TEXT_RECOG_SUM,
			   description = InfoApi.TEXT_RECOG_DESC,
			   responses = @ApiResponse(responseCode = "201", description = InfoApi.TEXT_RECOG_RES))
	@PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<ResponseGenericDto<TextDto>> uploadImage(@Parameter(description = "Imagen de tipo: jpeg, jpg, png, tiff") 
																		@RequestParam(name = "image", required = true) @ValidImage MultipartFile image,
																	@Parameter(description = "Idioma del texto") 
																		@RequestParam(name = "language", required = true) Language language){
		log.info("Metodo: POST, Operacion: uploadImage, datos: {}, {}", image.getOriginalFilename(), language.name());
		//Llama a servicio
		TextDto textDto = this.service.recognize(image, language);
		//Crear respuesta
		ResponseGenericDto<TextDto> response = new ResponseGenericDto<>();
		response.setData(textDto);
		response.setResponse(new ResponseDto(HttpStatus.CREATED.value(), InfoApi.TEXT_RECOG_RES));
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}//Fin de clase
