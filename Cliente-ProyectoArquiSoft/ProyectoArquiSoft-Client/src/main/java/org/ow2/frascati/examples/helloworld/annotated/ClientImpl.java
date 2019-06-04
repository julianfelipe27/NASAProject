/**
 * OW2 FraSCAti Examples: HelloWorld RMI
 * Copyright (C) 2008-2010 INRIA, University of Lille 1
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Contact: frascati@ow2.org
 *
 * Author: Damien Fournier
 * 
 * Contributor(s): Nicolas Dolet
 *                 Philippe Merle
 *
 */
package org.ow2.frascati.examples.helloworld.annotated;

import javax.imageio.ImageReader;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class ClientImpl implements Runnable
{
  //--------------------------------------------------------------------------
  // SCA Reference
  // --------------------------------------------------------------------------

  private PrintService s;
  private RepositorioImagen imag;

  @Reference
  public final void setPrintService(PrintService service)
  {
    this.s = service;
  }

  //--------------------------------------------------------------------------
  // Default constructor
  // --------------------------------------------------------------------------

  public ClientImpl()
  {
    System.out.println("CLIENT created");
    imag = new Imagenes();
  }

  @Init
  public final void init()
  {
    System.out.println("CLIENT initialized");
  }

  //--------------------------------------------------------------------------
  // Implementation of the Runnable interface
  // --------------------------------------------------------------------------

  public final void run()
  {
    System.out.println("Call the service...");
    
   String ruta = "/Users/Juan Camilo Cubillos/Documents/Imagenes/imagenDos.jpeg";
   double phi =45;
    
    try 
	{
		int numeroProcesadores = 3;
	
		ImageReader readerPrincipal = imag.cargarImagen(ruta);
		
		//Aqui tomamos el reader para calcular las coordenadas a dividir
		int height = readerPrincipal.getHeight(0);
		int sizeToPluss = height/numeroProcesadores;
		
		int[] cordenadas = new int[numeroProcesadores];
		int aux = 0;
		
		for (int i = 0; i < cordenadas.length; i++) 
		{
			cordenadas[i] = aux+sizeToPluss;
			aux += sizeToPluss;
		}
		cordenadas[cordenadas.length-1] = height;
		
		
		
		s.cargarImagen(ruta,1,0,0,cordenadas[0],phi);
		for (int i = 1; i < cordenadas.length; i++)
		{
			s.cargarImagen(ruta,i+1,0,cordenadas[i-1]+1,cordenadas[i-1],phi);
		}
		
		
	} 
	catch (Exception e) 
	{
		// TODO: handle exception
	}
	
	
//	
//	BufferedImage imagenArotar = repositorioImagen.darImagen();
//	
//	
//	BufferedImage imagenUnida = new BufferedImage(imagenArotar.getWidth(),imagenArotar.getHeight(), BufferedImage.TYPE_INT_RGB);


//	repositorioImagen.guardarImagen(imagenUnida,"/Users/Juan Camilo Cubillos/Documents/Imagenes/resultadoRotacion.tif");
//	
//	
//	repositorioImagen.liberarRecursos();
  }
}
