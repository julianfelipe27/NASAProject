/**
 * OW2 FraSCAti Examples: HelloWorld RMI
 * Copyright (C) 2009 INRIA, University of Lille 1
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
 * Author: Damien Fournier
 *         
 * Contributor(s): Christophe Demarey
 *                 Nicolas Dolet
 *                 Philippe Merle
 *
 */
package org.ow2.frascati.examples.helloworld.annotated;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.osoa.sca.annotations.Property;


/**
 * The print service implementation.
 */
public class Server implements PrintService
{
    @Property
    private String header = "->";

    private int count = 1;

    /**
     * Default constructor.
     */
    public Server()
    {
        System.out.println("SERVER created.");
    }
    
    /**
     * PrintService implementation.
     */
    public final void print(final String msg)
    {
        System.out.println("SERVER: begin printing...");
        for (int i = 0; i < count; ++i) 
        {
            System.out.println(header + msg);
        }
        System.out.println("SERVER: print done.");
    }
    
    
	public ImageReader cargarImagen(String ruta, int numeroProcesador, int x, int y,int cordenada, double phi) {
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
	    		transformer(reader, numeroProcesador, x, y, cordenada, phi);
	   
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return reader;
	}
	
	
	public void transformer(ImageReader reader, int numeroProcesador, int x, int y,int cordenada, double phi) 
	{
		try {
			ImageReadParam param = reader.getDefaultReadParam();
			param.setDestinationType(reader.getRawImageType(0));
			param.setSourceRegion(new Rectangle(x, y, reader.getWidth(0), cordenada));
			
			BufferedImage imagenPartidaRotada = rotateMatrizByAngle(reader.read(0,param), phi, cordenada);
			saveImage(imagenPartidaRotada,"/Users/Juan Camilo Cubillos/Documents/Imagenes/particion" +numeroProcesador + ".tif");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	
	}
	
	
	public BufferedImage rotateMatrizByAngle(BufferedImage image, double phi, int cordenada) 
	{
		int width = image.getWidth();
		int height = image.getHeight();
		int midW = Math.round(width/2);
		int midH = Math.round(height/2);
		
		BufferedImage rotatedImage = new BufferedImage(width, height,
		        BufferedImage.TYPE_INT_RGB);
		
		double[][] rotateMatrix = calculateRotateMatrix(phi);
		
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				int cI = i-midW;
				int cJ = j-midH;
				
				int x1 = (int) Math.round(cI*rotateMatrix[0][0] + cJ*rotateMatrix[0][1]) + midW; 
				int y1 = (int) Math.round(cI*rotateMatrix[1][0] + cJ*rotateMatrix[1][1]) + midH;
								
				if(x1 > -1 && y1 > -1 && x1 < width && y1 < height) {
					rotatedImage.setRGB(i, j, image.getRGB(x1, y1));
				}
			
			}
		}
		return rotatedImage;
	}
	/**
	 * Calcula la matriz de rotaciÃ³n con un Ã¡ngulo phi.
	 * @param phi Ã¡ngulo de rotaciÃ³n.
	 * @return matriz de rotaciÃ³n.
	 */
	private double[][] calculateRotateMatrix(double phi) {
		double cosPhi = Math.cos(Math.toRadians(phi));
		double sinPhi = Math.sin(Math.toRadians(phi));
		return new double[][] {{cosPhi, -sinPhi},
			{sinPhi, cosPhi}};
	}
	
	private void saveImage(BufferedImage image, String ruta) 
	{
		
		File output = new File(ruta);
	    try {
			ImageIO.write(image, "jpg", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
