package pl.lab4.util;

import java.awt.Point;
import java.awt.image.WritableRaster;

import pl.workspace.model.ImageModel;

public class MorphologicUtil {
	
	public static final int ROMB = 1, KWADRAT = 2;
	
	public static void erosion(ImageModel model, int neighbor, int levels){
		WritableRaster raster_out = model.getImage().getRaster();
		WritableRaster raster = model.getImage().copyData(null);
		
		Point[] kwadrat = new Point[] { 
			new Point(1, 0), 
        	new Point(-1, 0), 
        	new Point(0, 1), 
        	new Point(0, -1), 
        	new Point(1, 1), 
        	new Point(-1, -1), 
        	new Point(-1, 1), 
        	new Point(1, -1),
        	new Point(0, 0)
		};
		
		Point[] romb = new Point[] { 
        	new Point(1, 0), 
        	new Point(-1, 0), 
        	new Point(0, 1), 
        	new Point(0, -1),
        	new Point(0, 0)
        };
		
		int min = levels;
		int pixel;
		/*for(int y = 1; y < raster.getHeight() - 1; ++y){
			for(int x = 1; x < raster.getWidth() - 1; ++x){
				switch(neighbor){
					case ROMB:{
						for(Point offset : romb){
							pixel = raster.getPixel(x + offset.x, y + offset.y, new int[1])[0];
							if(pixel < min)
								min = pixel;
						}
						break;
					}
					case KWADRAT:{
						for(Point offset : kwadrat){
							pixel = raster.getPixel(x + offset.x, y + offset.y, new int[1])[0];
							if(pixel < min)
								min = pixel;
						}
						break;
					}
				}
				raster_out.setPixel(x, y, new int[]{min});
				min = levels;
			}
		}
		
		for(int x = 1; x < raster.getWidth() - 1; ++x){
			switch(neighbor){
				case ROMB:{
					
					break;
				}
				case KWADRAT:{
					
					break;
				}
			}
		}*/
		
		for(int y = 0; y < raster.getHeight(); ++y){
			for(int x = 0; x < raster.getWidth(); ++x){
				switch(neighbor){
					case ROMB:{
						for(Point offset : romb){
							if(x + offset.x >= 0 && x + offset.x < raster.getWidth() && y + offset.y >= 0 && y + offset.y < raster.getHeight()){
								pixel = raster.getPixel(x + offset.x, y + offset.y, new int[1])[0];
								if(pixel < min)
									min = pixel;
							}
						}
						break;
					}
					case KWADRAT:{
						for(Point offset : kwadrat){
							if(x + offset.x >= 0 && x + offset.x < raster.getWidth() && y + offset.y >= 0 && y + offset.y < raster.getHeight()){
								pixel = raster.getPixel(x + offset.x, y + offset.y, new int[1])[0];
								if(pixel < min)
									min = pixel;
							}
						}
						break;
					}
				}
				raster_out.setPixel(x, y, new int[]{min});
				min = levels;
			}
		}
		
		model.modelChanged();
	}
	
	
	public static void dilation(ImageModel model, int neighbor){
		WritableRaster raster_out = model.getImage().getRaster();
		WritableRaster raster = model.getImage().copyData(null);
		
		Point[] kwadrat = new Point[] { 
			new Point(1, 0), 
        	new Point(-1, 0), 
        	new Point(0, 1), 
        	new Point(0, -1), 
        	new Point(1, 1), 
        	new Point(-1, -1), 
        	new Point(-1, 1), 
        	new Point(1, -1),
        	new Point(0, 0)
		};
		
		Point[] romb = new Point[] { 
        	new Point(1, 0), 
        	new Point(-1, 0), 
        	new Point(0, 1), 
        	new Point(0, -1),
        	new Point(0, 0)
        };
		
		int max = -1;
		int pixel;
		
		for(int y = 0; y < raster.getHeight(); ++y){
			for(int x = 0; x < raster.getWidth(); ++x){
				switch(neighbor){
					case ROMB:{
						for(Point offset : romb){
							if(x + offset.x >= 0 && x + offset.x < raster.getWidth() && y + offset.y >= 0 && y + offset.y < raster.getHeight()){
								pixel = raster.getPixel(x + offset.x, y + offset.y, new int[1])[0];
								if(pixel > max)
									max = pixel;
							}
						}
						break;
					}
					case KWADRAT:{
						for(Point offset : kwadrat){
							if(x + offset.x >= 0 && x + offset.x < raster.getWidth() && y + offset.y >= 0 && y + offset.y < raster.getHeight()){
								pixel = raster.getPixel(x + offset.x, y + offset.y, new int[1])[0];
								if(pixel > max)
									max = pixel;
							}
						}
						break;
					}
				}
				raster_out.setPixel(x, y, new int[]{max});
				max = -1;
			}
		}
		
		model.modelChanged();
	}
	
	public static void opening(ImageModel model, int neighbor, int levels){
		erosion(model, neighbor, levels);
		dilation(model, neighbor);
	}
	
	public static void closing(ImageModel model, int neighbor, int levels){
		dilation(model, neighbor);
		erosion(model, neighbor, levels);
	}

}
