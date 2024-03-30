package com.iacg.app.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * Clase de utilidad para archivos
 * 
 * @author IACG
 */
public class FileUtil {
	
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
