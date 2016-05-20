package pl.lab3.util;

import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.workspace.model.ImageModel;

public class FiltracjaUtil {
	
	public static final int PROPORTIONAL = 1, THREE_VALUED = 2, CUTTING = 4, NONE = 8;
	
	public static void filter(ImageModel model, int[][] mask, int scaling, int levels){
		WritableRaster raster_out = model.getImage().getRaster();
		WritableRaster raster = model.getImage().copyData(null);
		
		
		//WritableRaster raster = model.duplicate().getImage().getRaster();
		int w = 0;
		
		OUTER_LOOP:
		for(int i = 0; i < mask.length; ++i){
			for(int j = 0; j < mask[i].length; ++j){
				w = w + mask[i][j];
				//System.out.print(mask[i][j] + " ");
				/*if(mask[i][j] < 0){
					w = 1;
					break OUTER_LOOP;
				}*/
			}
			//System.out.println();
		}
		//System.out.println(w);
		if(Math.abs(w) < 2)
			w = 1;
		//System.out.println(w);
		int rows = mask.length;
		int cols = mask[0].length;
		double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
		double new_value = 0;
		for(int y = rows/2; y < raster.getHeight() - rows/2; ++y){
			for(int x = cols/2; x < raster.getWidth() - cols/2; ++x){
				for(int i = -rows/2; i <= rows/2; ++i){
					for(int j = -cols/2; j <= cols/2; ++j){
						new_value += raster.getPixel(x + j, y + i, new double[1])[0] * mask[rows/2 + i][cols/2 + j];
					}
				}
				new_value = new_value/w;
				//System.out.println(new_value);
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
							if(new_value >= levels)
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
							if(new_value >= levels)
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
				new_value = 0;
			}
		}
		
		if(scaling == PROPORTIONAL){
			/*for(int x = 0; x < raster.getWidth(); ++x){
				for(int y = 0; y < raster.getHeight(); ++y){
					new_value = (levels - 1) * (raster.getPixel(x, y, new double[1])[0] - min)/(max - min);
					//System.out.println(raster.getPixel(x, y, new double[1])[0] - new_value);
					raster_out.setPixel(x, y, new double[]{new_value});
				}
			}*/
			for(int y = rows/2; y < raster.getHeight() - rows/2; ++y){
				for(int x = cols/2; x < raster.getWidth() - cols/2; ++x){
					for(int i = -rows/2; i <= rows/2; ++i){
						for(int j = -cols/2; j <= cols/2; ++j){
							new_value += raster.getPixel(x + j, y + i, new double[1])[0] * mask[rows/2 + i][cols/2 + j];
						}
					}
					new_value = new_value/w;
					raster_out.setPixel(x, y, new double[]{(levels - 1) * (new_value - min)/(max - min)});
					new_value = 0;
				}
			}
		}
		
		model.modelChanged();
	}
	
	public static void median(ImageModel model, int rows, int cols){
		WritableRaster raster_out = model.getImage().getRaster();
		WritableRaster raster = model.getImage().copyData(null);
		List<Integer> list = new ArrayList<Integer>();
		int new_value;
		for(int y = rows/2; y < raster.getHeight() - rows/2; ++y){
			for(int x = cols/2; x < raster.getWidth() - cols/2; ++x){
				for(int i = -rows/2; i <= rows/2; ++i){
					for(int j = -cols/2; j <= cols/2; ++j){
						list.add(raster.getPixel(x + j, y + i, new int[1])[0]);
					}
				}
				Collections.sort(list);
				new_value = list.get((list.size() - 1)/2);
				list.clear();
				raster_out.setPixel(x, y, new int[]{new_value});
			}
		}
		model.modelChanged();
	}
	
	/*public static void rescale(ImageModel image_model, int method, int levels){
		WritableRaster raster = image_model.getImage().getRaster();
		double pixel;
		switch(method){
			case PROPORTIONAL:{
				double max = Double.MIN_VALUE, min = Double.MAX_VALUE;
				double new_value;
				for(int x = 0; x < raster.getWidth(); ++x){
					for(int y = 0; y < raster.getHeight(); ++y){
						if(max < (pixel = raster.getPixel(x, y, new double[1])[0]))
							max = pixel;
						if(min > (pixel = raster.getPixel(x, y, new double[1])[0]))
							min = pixel;
					}
				}
				System.out.println(min + " " + max);
				for(int x = 0; x < raster.getWidth(); ++x){
					for(int y = 0; y < raster.getHeight(); ++y){
						new_value = (levels - 1) * (raster.getPixel(x, y, new double[1])[0] - min)/(max - min);
						//System.out.println(raster.getPixel(x, y, new double[1])[0] - new_value);
						raster.setPixel(x, y, new double[]{new_value});
					}
				}
				break;
			}
			case THREE_VALUED:{
				for(int x = 0; x < raster.getWidth(); ++x){
					for(int y = 0; y < raster.getHeight(); ++y){
						pixel = raster.getPixel(x, y, new double[1])[0];
						if(pixel < 0)
							raster.setPixel(x, y, new int[]{0});
						else
						{
							if(pixel >= levels)
								raster.setPixel(x, y, new int[]{levels - 1});
							else
								raster.setPixel(x, y, new int[]{(levels - 1)/2});
						}
					}
				}
				break;
			}
			case CUTTING:{
				
				break;
			}
		}
		image_model.modelChanged();
	}*/

}
