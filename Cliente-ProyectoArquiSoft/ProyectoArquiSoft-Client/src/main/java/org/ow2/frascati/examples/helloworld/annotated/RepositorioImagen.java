/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ProcesadorImagen.java 1207 2019-05-05 07:00:03Z s.n $
 * Universidad ICESI (Cali - Colombia)
 * Licenciado bajo el esquema Academic Free License version 2.1 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package org.ow2.frascati.examples.helloworld.annotated;

import java.awt.image.BufferedImage;

import javax.imageio.ImageReader;
/**
 * Representación de los servicios que deben implementar los repositorios de imagen.
 *
 */
public interface RepositorioImagen {
	/**
	 * Representa el servicio con el cual se carga una imagen a la aplicación.
	 * @param ruta la dirección de la imagen a cargar.
	 * @param numeroProcesadores 
	 */
	public ImageReader cargarImagen(String ruta);
	/**
	 * Representa el servicio con el cual se obtiene la imagen cargada para su manipulación.
	 * @return la imagen cargada en el repositorio.
	 */
	public BufferedImage darImagen();
	/**
	 * Representa el servicio con el cual se guarda los cambios de una imagen.
	 * @param image imagen a guardar.
	 * @param ruta dirección en donde se guardará la imagen.
	 */
	public void guardarImagen(BufferedImage image, String ruta);
	/**
	 * Representa el servicio con el cual se liberan los recursos que se han utilizados en el repositorio.
	 */
	public void liberarRecursos();
}
