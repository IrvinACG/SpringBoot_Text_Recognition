package com.iacg.app.util;

import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

/**
 * Clase de Utilidad de Imagen, que permite procesar la imagen
 * y aplicar filtros
 * 
 * @author IACG
 */
@Slf4j
public class ImageUtil {
	
	/**
	 * Constructor privado que evita la instancia
	 */
	private ImageUtil () {}
	
	/**
	 * Metodo que crea una imagen, aplicando filtros a partir de un MultipartFile
	 * @param file Archivo de entrada
	 * @return File Respuesta
	 */
    public static File convertImage(MultipartFile file) {
    	File tempFile = null;
    	try {
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(file.getBytes()));

            // Aplicar filtro de suavizado para eliminar ruido
            float[] smoothMatrix = {
                    1.0f / 12, 2.0f / 12, 1.0f / 12,
                    2.0f / 12, 5.0f / 12, 2.0f / 12,
                    1.0f / 12, 2.0f / 12, 1.0f / 12
                };
            BufferedImage smoothedImage = applyKernel(originalImage, smoothMatrix);

            // Aplicar filtro de nitidez
            float[] sharpenMatrix = {
                0.0f, -1.0f, 0.0f,
                -1.0f, 5.0f, -1.0f,
                0.0f, -1.0f, 0.0f
            };
            BufferedImage sharpenedImage = applyKernel(smoothedImage, sharpenMatrix);

            // Convertir la imagen a escala de grises
            BufferedImage blackAndWhiteImage = new BufferedImage(sharpenedImage.getWidth(), sharpenedImage.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
            blackAndWhiteImage.getGraphics().drawImage(sharpenedImage, 0, 0, null);

            // Crear un archivo temporal para guardar la imagen en escala de grises, nítida y sin ruido
            String extension = FileUtil.getFileExtension(file);
            String extWithout = extension.replace(".", "");
            log.info("{}",extWithout);
            tempFile = File.createTempFile(file.getName(), extension);
            ImageIO.write(blackAndWhiteImage, extWithout, tempFile);
        
    	} catch (IOException e) {
			log.error("Error al convtir imagen a B/N, error: {}",e.getMessage());
		}

        return tempFile;
    }
    
    /**
     * Metodo auxiliar que aplica el filtro de convolucion
     * @param image Imagen
     * @param kernelData Matriz de convolucion a la que se aplicara el filtro
     * @return BufferedImage Respuesta
     */
    private static BufferedImage applyKernel(BufferedImage image, float[] kernelData) {
        BufferedImageOp op = new ConvolveOp(new Kernel(3, 3, kernelData), ConvolveOp.EDGE_NO_OP, null);
        return op.filter(image, null);
    }
    
}//Fin de clase
    
