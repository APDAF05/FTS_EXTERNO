/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ve.org.bcv.fts.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * @author aandrade
 * @date 08/03/2016
 *
 */
public class PropertiesLoader implements Serializable {

	private static PropertiesLoader pl;

	private static Logger log = LogManager.getLogger(PropertiesLoader.class.getName());

	public static PropertiesLoader getInstance() {
		if (pl == null) {
			pl = new PropertiesLoader();
		}
		return pl;
	}

	/**
	 * Metodo que retorna un Properties con el contenido del Archivo indicado
	 * 
	 * @param propertyName,
	 *            ruta del archivo a ser cargado
	 * @return String, contenido del archivo indicado
	 * @throws Exception
	 */
	public static Properties loadConfig(String propertyName) throws Exception {
		Properties property = new Properties();
//		System.out.println("Entro al loadConfig");
		if (pl == null)
			pl = new PropertiesLoader();
		String path = new File(".").getCanonicalPath();
		InputStream inputStream = new FileInputStream(path + propertyName);

		try {
			if (inputStream == null) {
				throw new Exception("Archivo'" + propertyName + "' no puede ser encontrado o no existe");
			}
			property.load(inputStream);
		} catch (IOException e) {
			log.error("HA OCURRIDO UNA EXCEPCION ");
			log.error("MENSAJE : " + e.getMessage());
			log.error("CAUSA DE LA EXCEPCION : " + e.getCause());
			e.printStackTrace();
			throw new Exception(e.getMessage(), e.getCause());
		} catch (Exception e) {
			log.error("HA OCURRIDO UNA EXCEPCION ");
			log.error("MENSAJE : " + e.getMessage());
			log.error("CAUSA DE LA EXCEPCION : " + e.getCause());
			e.printStackTrace();
			throw new Exception(e.getMessage(), e.getCause());
		} finally {
			inputStream.close();
		}

		// log.info("ARCHIVO CARGADO");
		// log.debug(property);

		return property;
	}

	/**
	 * Metodo que retorna un String con el contenido del Archivo indicado
	 * 
	 * @param fileName,
	 *            ruta del archivo a ser cargado
	 * @return String, contenido del archivo indicado
	 * @throws Exception
	 */
	public static String loadFile(String fileName) throws Exception {
		String contenido = "";

		StringBuffer out = new StringBuffer();

		try {
			InputStream inputStream = (PropertiesLoader.class).getClassLoader().getResourceAsStream(fileName);

			if (inputStream == null) {
				throw new Exception("Archivo'" + fileName + "' no puede ser encontrado o no existe");
			}

			InputStreamReader inread = new InputStreamReader(inputStream);

			char[] b = new char[4096];

			for (int n; (n = inread.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}

			contenido = out.toString();
		} catch (IOException e) {
			log.error("HA OCURRIDO UNA EXCEPCION ");
			log.error("MENSAJE : " + e.getMessage());
			log.error("CAUSA DE LA EXCEPCION : " + e.getCause());
			e.printStackTrace();
			throw new Exception(e.getMessage(), e.getCause());
		} catch (Exception e) {
			log.error("HA OCURRIDO UNA EXCEPCION ");
			log.error("MENSAJE : " + e.getMessage());
			log.error("CAUSA DE LA EXCEPCION : " + e.getCause());
			e.printStackTrace();
			throw new Exception(e.getMessage(), e.getCause());
		}

		// log.info("ARCHIVO CARGADO");
		// log.debug(property);

		return contenido;
	}
}
