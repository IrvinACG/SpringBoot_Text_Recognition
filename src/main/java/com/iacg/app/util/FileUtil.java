package com.iacg.app.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase de utilidad para archivos
 * 
 * @author IACG
 */
@Slf4j
public class FileUtil {
	
	/**
	 * Constructor privado que evita la instancia
	 */
	private FileUtil() {}

	/**
	 * Metodo que permite convertir un archivo MutipartFile a File
	 * @param multipartFile Archivo a convertir
	 * @return File respuesta
	 */
	public static File convert(MultipartFile multipartFile) {
		File tempFile = null;
        try{
            // Crear un archivo temporal
            Path tempFilePath = Files.createTempFile("tempfile", getFileExtension(multipartFile));

            // Convertir el archivo temporal a un objeto File
            tempFile = tempFilePath.toFile();

            // Escribir los bytes del MultipartFile al archivo temporal
            multipartFile.transferTo(tempFile);

        } catch (IOException e) {
			log.error("Error al convetir MultipartFile a File, error: {}",e.getMessage());
		}
		return tempFile;
	}
	
	/**
	 * Metodo que permite saber la extension de un archivo MultipartFile
	 * @param multipartFile Aarchivo
	 * @return String respuesta
	 */
    public static String getFileExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return null; // Si no se puede obtener la extensión del archivo
    }
    
}//Fin de clase
