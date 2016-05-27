package pl.lab3.util;

import java.awt.image.WritableRaster;

import pl.workspace.model.ImageModel;

public class LogicznaUtil {
	
	public static final int DIRECTION0 = 1, DIRECTION1 = 2, DIRECTION2 = 3, DIRECTION3 = 4;
	
	public static void logical(ImageModel model, int direction){
		WritableRaster raster_out = model.getImage().getRaster();
		WritableRaster raster = model.getImage().copyData(null);
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
		int pixel1, pixel2;
		
		switch(direction){
			case DIRECTION0:{
				dx1 = 0;
				dx2 = 0;
				dy1 = 1;
				dy2 = -1;
				break;
			}
			case DIRECTION1:{
				dx1 = 1;
				dx2 = -1;
				dy1 = -1;
				dy2 = 1;
				break;
			}
			case DIRECTION2:{
				dx1 = 1;
				dx2 = -1;
				dy1 = 0;
				dy2 = 0;
				break;
			}
			case DIRECTION3:{
				dx1 = -1;
				dx2 = 1;
				dy1 = -1;
				dy2 = 1;
				break;
			}
		}
		
		for(int x = 1; x < raster.getWidth() - 1; ++x){
			for(int y = 1; y < raster.getHeight() - 1; ++y){
				pixel1 = raster.getPixel(x+dx1, y+dy1, new int[1])[0];
				pixel2 = raster.getPixel(x+dx2, y+dy2, new int[1])[0];
				if(pixel1 == pixel2)
					raster_out.setPixel(x, y, new int[]{pixel1});
			}
		}
		model.modelChanged();
	}

}
