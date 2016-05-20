package pl.workspace.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Observable;

import javax.imageio.ImageIO;

public class ImageModel extends Observable{
	
	private BufferedImage image;
	private String name;
	
	public ImageModel(File file) throws IOException{
		name = file.getName();
		BufferedImage imrgb = ImageIO.read(file);
		image = new BufferedImage(imrgb.getWidth(), imrgb.getHeight(), BufferedImage.TYPE_BYTE_GRAY);  
		Graphics g = image.getGraphics();  
		g.drawImage(imrgb, 0, 0, null);  
		g.dispose();  
	}
	
	private ImageModel(BufferedImage image){
		this.image = image;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public String getName(){
		return name;
	}
	
	public ImageModel duplicate(){	//bez observerow
		ImageModel cp = new ImageModel(new BufferedImage(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null));
		cp.name = name + " - Kopia";
		return cp;
	}
	
	public double[] getHistogram(){
		double[] histogram = new double[256];
		Arrays.fill(histogram, 0);
		WritableRaster raster = image.getRaster();
		int[] pixel;
		for(int x = 0; x < raster.getWidth(); ++x){
			for(int y = 0; y < raster.getHeight(); ++y){
				pixel = raster.getPixel(x, y, new int[1]);
				histogram[pixel[0]]++;
			}
		}
		return histogram;
	}
	
	public void modelChanged(){
		setChanged();
		notifyObservers();
	}

}
