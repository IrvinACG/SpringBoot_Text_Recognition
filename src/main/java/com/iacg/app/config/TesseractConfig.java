package com.iacg.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.ITessAPI.TessOcrEngineMode;
import net.sourceforge.tess4j.ITessAPI.TessPageSegMode;
import net.sourceforge.tess4j.Tesseract;

/**
 * Configuracion del objeto Tesseract
 * 
 * @author IACG
 */
@Configuration
public class TesseractConfig {

	/**
	 * Variable tessDataPath
	 */
	@Value("${tesseract.datapath}")
    private String tessDataPath;
	
	/**
	 * Bean que crea el objeto Tesseract
	 * @return Tesseract bean
	 */
	@Bean
	Tesseract tesseractCreate() {
		Tesseract tesseract = new Tesseract();
		tesseract.setDatapath(tessDataPath);
		// Establecer el modo de segmentacion
		tesseract.setPageSegMode(TessPageSegMode.PSM_AUTO_OSD); 
		//Establecer el modo del motor de OCR
		tesseract.setOcrEngineMode(TessOcrEngineMode.OEM_TESSERACT_LSTM_COMBINED); 
		return tesseract;
	}
	
}//Fin de clase
