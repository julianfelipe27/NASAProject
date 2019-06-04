/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ProcesadorImagen.java 1207 2019-05-05 07:00:03Z s.n $
 * Universidad ICESI (Cali - Colombia)
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package org.ow2.frascati.examples.helloworld.annotated;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import co.edu.icesi.ProcesadorImagen;
/**
 * Representa el patron repositorio donde se manejará las representaciones de dominio de la data.
 */
public class Imagenes implements RepositorioImagen{
	
	/**
	 * Representación de la imagen con un buffer para acceder a la data de esta.
	 */
	private BufferedImage nasaImage;
	
	@Override
	public ImageReader cargarImagen(String ruta) {
		ImageReader reader = null;
		try {
			ImageInputStream input = ImageIO.createImageInputStream(new File(ruta));
			Iterator<ImageReader> readers = ImageIO.getImageReaders(input);
			if (!readers.hasNext()) {
		        System.out.println("Error images");
		    }else {
		    	
		    	
		    	//1.545.785.280 (pixels)
		    	//4.637.355.840 (channels)(3 RGB)
		    	//2.147.483.648 (Max Integer)
		    		
	    		reader = readers.next();
	    		reader.setInput(input);
	    		
	   
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return reader;
	}
	
	@Override
	public BufferedImage darImagen() {
		return nasaImage;
	}

	@Override
	public void guardarImagen(BufferedImage image, String ruta) {
		long startTime = System.nanoTime();
		File output = new File(ruta);
	    try {
			ImageIO.write(image, "jpg", output);
			long endTime = System.nanoTime();
			ProcesadorImagen.addString((endTime-startTime)+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void liberarRecursos() {
		nasaImage.flush();
		nasaImage = null;
	}
}