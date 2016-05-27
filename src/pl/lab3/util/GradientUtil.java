package pl.lab3.util;

import java.awt.image.WritableRaster;

import pl.workspace.model.ImageModel;

public class GradientUtil {
	
	public static final int PROPORTIONAL = 1, THREE_VALUED = 2, CUTTING = 3, NONE = 4;
	
	public static void roberts(ImageModel model, int scaling, int levels){
		WritableRaster raster_out = model.getImage().getRaster();
		WritableRaster raster = model.getImage().copyData(null);
		int gx, gy;
		double new_value;
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
	
		//1  0		0 -1
		//0 -1		1  0
		
		for(int x = 0; x < raster.getWidth() - 1; ++x){
			for(int y = 0; y < raster.getHeight() - 1; ++y){
				gx = Math.abs(raster.getPixel(x, y, new int[1])[0] - raster.getPixel(x + 1, y + 1, new int[1])[0]);
				gy = Math.abs(raster.getPixel(x, y + 1, new int[1])[0] - raster.getPixel(x + 1, y, new int[1])[0]);
				//raster_out.setPixel(x, y, new int[]{gx + gy});
				new_value = gx + gy;
				
				switch(scaling){
					case NONE:{
						raster_out.setPixel(x, y, new double[]{new_value});
						break;
					}
					case THREE_VALUED:{
						if(new_value < 0)
							raster_out.setPixel(x, y, new int[]{0});
						else
						{
							//if(new_value >= levels)
							if(new_value > 0)
								raster_out.setPixel(x, y, new int[]{levels - 1});
							else
								raster_out.setPixel(x, y, new int[]{(levels - 1)/2});
						}
						break;
					}
					case CUTTING:{
						if(new_value < 0)
							raster_out.setPixel(x, y, new int[]{0});
						else
						{
							if(new_value > levels - 1)
								raster_out.setPixel(x, y, new int[]{levels - 1});
							else
								raster_out.setPixel(x, y, new double[]{new_value});
						}
						break;
					}
					case PROPORTIONAL:{
						if(min > new_value)
							min = new_value;
						if(max < new_value)
							max = new_value;
						break;
					}
				}
			}
		}
		
		if(scaling == PROPORTIONAL){
			for(int x = 0; x < raster.getWidth() - 1; ++x){
				for(int y = 0; y < raster.getHeight() - 1; ++y){
					gx = Math.abs(raster.getPixel(x, y, new int[1])[0] - raster.getPixel(x + 1, y + 1, new int[1])[0]);
					gy = Math.abs(raster.getPixel(x, y + 1, new int[1])[0] - raster.getPixel(x + 1, y, new int[1])[0]);
					new_value = gx + gy;
					raster_out.setPixel(x, y, new double[]{(levels - 1) * (new_value - min)/(max - min)});
				}
			}
		}
		
		model.modelChanged();
	}
	
	
	
	
	public static void sobel(ImageModel model, int scaling, int levels){
		WritableRaster raster_out = model.getImage().getRaster();
		WritableRaster raster = model.getImage().copyData(null);
		int sx, sy;
		double new_value;
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		
		//1 0 -1	-1 -2 -1
		//2 0 -2	 0  0  0
		//1 0 -1	 1  2  1
		
		for(int x = 1; x < raster.getWidth() - 1; ++x){
			for(int y = 1; y < raster.getHeight() - 1; ++y){
				sx = raster.getPixel(x - 1, y - 1, new int[1])[0] + 2*raster.getPixel(x - 1, y, new int[1])[0]
						+ raster.getPixel(x - 1, y + 1, new int[1])[0] - raster.getPixel(x + 1, y - 1, new int[1])[0]
						- 2*raster.getPixel(x + 1, y, new int[1])[0] - raster.getPixel(x + 1, y + 1, new int[1])[0];
				sy = raster.getPixel(x - 1, y + 1, new int[1])[0] + 2*raster.getPixel(x, y + 1, new int[1])[0]
						+ raster.getPixel(x + 1, y + 1, new int[1])[0] - raster.getPixel(x - 1, y - 1, new int[1])[0]
						- 2*raster.getPixel(x, y - 1, new int[1])[0] - raster.getPixel(x + 1, y - 1, new int[1])[0];
				
				new_value = Math.sqrt(Math.pow(sx, 2) + Math.pow(sy, 2));
				//raster_out.setPixel(x, y, new double[]{new_value});
				switch(scaling){
					case NONE:{
						raster_out.setPixel(x, y, new double[]{new_value});
						break;
					}
					case THREE_VALUED:{
						if(new_value < 0)
							raster_out.setPixel(x, y, new int[]{0});
						else
						{
							//if(new_value >= levels)
							if(new_value > 0)
								raster_out.setPixel(x, y, new int[]{levels - 1});
							else
								raster_out.setPixel(x, y, new int[]{(levels - 1)/2});
						}
						break;
					}
					case CUTTING:{
						if(new_value < 0)
							raster_out.setPixel(x, y, new int[]{0});
						else
						{
							if(new_value > levels - 1)
								raster_out.setPixel(x, y, new int[]{levels - 1});
							else
								raster_out.setPixel(x, y, new double[]{new_value});
						}
						break;
					}
					case PROPORTIONAL:{
						if(min > new_value)
							min = new_value;
						if(max < new_value)
							max = new_value;
						break;
					}
				}
			}
		}
		
		if(scaling == PROPORTIONAL){
			for(int x = 1; x < raster.getWidth() - 1; ++x){
				for(int y = 1; y < raster.getHeight() - 1; ++y){
					sx = raster.getPixel(x - 1, y - 1, new int[1])[0] + 2*raster.getPixel(x - 1, y, new int[1])[0]
							+ raster.getPixel(x - 1, y + 1, new int[1])[0] - raster.getPixel(x + 1, y - 1, new int[1])[0]
							- 2*raster.getPixel(x + 1, y, new int[1])[0] - raster.getPixel(x + 1, y + 1, new int[1])[0];
					sy = raster.getPixel(x - 1, y + 1, new int[1])[0] + 2*raster.getPixel(x, y + 1, new int[1])[0]
							+ raster.getPixel(x + 1, y + 1, new int[1])[0] - raster.getPixel(x - 1, y - 1, new int[1])[0]
							- 2*raster.getPixel(x, y - 1, new int[1])[0] - raster.getPixel(x + 1, y - 1, new int[1])[0];
					
					new_value = Math.sqrt(Math.pow(sx, 2) + Math.pow(sy, 2));
					raster_out.setPixel(x, y, new double[]{(levels - 1) * (new_value - min)/(max - min)});
				}
			}
		}
		
		model.modelChanged();
	}

}
