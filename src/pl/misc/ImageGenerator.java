package pl.misc;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageGenerator {

	public static void generate(){
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();
		for(int y = 0; y < raster.getHeight(); y++){
			for(int x = 0; x < raster.getWidth(); x++){
				if(y % 2 == 0)
					raster.setPixel(x, y, new int[]{0});
				else
					raster.setPixel(x, y, new int[]{128});
			}
		}
		try {
			ImageIO.write(image, "BMP", new File("pelne_linie.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generate2(){
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();
		for(int y = 0; y < raster.getHeight(); y++){
			for(int x = 0; x < raster.getWidth(); x++){
				if((x/10) % 2 == 0)
					raster.setPixel(x, y, new int[]{0});
				else
					raster.setPixel(x, y, new int[]{128});
			}
		}
		try {
			ImageIO.write(image, "BMP", new File("linie_segmenty.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void generate3(){
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster raster = image.getRaster();
		for(int x = 0; x < raster.getWidth(); x++){
			for(int y = 0; y < raster.getHeight(); y++){
				if(x % 2 == 0)
					raster.setPixel(x, y, new int[]{0});
				else
					raster.setPixel(x, y, new int[]{128});
			}
		}
		try {
			ImageIO.write(image, "BMP", new File("kolumny_pelne.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
