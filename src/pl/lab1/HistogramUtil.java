package pl.lab1;

import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import pl.workspace.model.ImageModel;

public class HistogramUtil {
	
	public static final int SREDNIA = 1, LOSOWA = 2, SASIEDZTWA = 3, WLASNA = 4;
	
	public static void equalizeHistogram(ImageModel image_model, int opt){
		double[] histogram = image_model.getHistogram();
		double havg = histogramAverage(histogram);
		WritableRaster raster = image_model.getImage().getRaster();
		
		/*if(opt == WLASNA){
			int size = raster.getWidth()*raster.getHeight();
			double[] sk = new double[histogram.length];
			double[] indexes = new double[histogram.length];
			for(int i = 0; i < indexes.length; ++i){
				indexes[i] = (double)i/255;
			}
			histogram[0] = histogram[0]/size;
			sk[0] = histogram[0];
			for(int i = 1; i < histogram.length; ++i){
				histogram[i] = histogram[i]/size;
				sk[i] = sk[i-1] + histogram[i];
			}
			int[] perm = new int[sk.length];
			for(int i = 0; i < perm.length; ++i){
				perm[i] = giveIndex(sk[i], indexes);
			}
			
			int[] pixel;
			for(int x = 0; x < raster.getWidth(); ++x){
				for(int y = 0; y < raster.getHeight(); ++y){
					pixel = raster.getPixel(x, y, new int[1]);
					raster.setPixel(x, y, new double[]{perm[pixel[0]]});
				}
			}
			
			
		}
		else{*/
			double r = 0, hint = 0;
			double[] left = new double[256];
			double[] right = new double[256];
			double[] newz = new double[256];
			for(int z = 0; z < histogram.length; ++z){
				left[z] = r;
				hint += histogram[z];
				while(hint > havg){
					hint -= havg;
					if(r < 255)
						r++;
				}
				right[z] = r;
				switch(opt){
					case SREDNIA:{
						newz[z] = (left[z] + right[z])/2;
						break;
					}
					case LOSOWA:{
						newz[z] = right[z] - left[z];
						break;
					}
				}
			}
			int[] pixel;
			for(int x = 0; x < raster.getWidth(); ++x){
				for(int y = 0; y < raster.getHeight(); ++y){
					pixel = raster.getPixel(x, y, new int[1]);
					if(left[pixel[0]] == right[pixel[0]])
						raster.setPixel(x, y, new double[]{left[pixel[0]]});
					else{
						switch(opt){
							case SREDNIA:{
								raster.setPixel(x, y, new double[]{newz[pixel[0]]});
								break;
							}
							case LOSOWA:{
								Random rnd = new Random();
								raster.setPixel(x, y, new double[]{left[pixel[0]]+rnd.nextInt((int)newz[pixel[0]]+1)});
								break;
							}
							case SASIEDZTWA:{
								double neigh_avg = neighborhoodAverage(raster, x, y);
								if(neigh_avg > right[pixel[0]])
									raster.setPixel(x, y, new double[]{right[pixel[0]]});
								else if(neigh_avg < left[pixel[0]])
									raster.setPixel(x, y, new double[]{left[pixel[0]]});
								else
									raster.setPixel(x, y, new double[]{neigh_avg});
								break;
							}
							case WLASNA:{
								//medianowa z sasiedztwa 3x3
								double median = neighborhoodMedian(raster, x, y);
								if(median > right[pixel[0]])
									raster.setPixel(x, y, new double[]{right[pixel[0]]});
								else if(median < left[pixel[0]])
									raster.setPixel(x, y, new double[]{left[pixel[0]]});
								else
									raster.setPixel(x, y, new double[]{median});
								break;
							}
						}
					}
				}
			}
		//}
		image_model.modelChanged();
	}

	private static double histogramAverage(double[] histogram)
	{
		double avg = 0;
		for(double el : histogram){
			avg += el;
		}
		avg = avg/histogram.length;
		return avg;
	}
	
	private static double neighborhoodAverage(WritableRaster raster, int x, int y){
		double avg = 0;
		int count = 0;
		
		for(int i = x - 1; i < x + 2; ++i){
			for(int j = y - 1; j < y + 2; ++j){
				if(!(i < 0 || i > raster.getWidth() - 1 || j < 0 || j > raster.getHeight() - 1)){
					avg += raster.getPixel(i, j, new double[1])[0];
					count++;
				}
			}
		}
		avg -= raster.getPixel(x, y, new double[1])[0];
		avg = avg/count;
		return avg;
	}
	
	private static double neighborhoodMedian(WritableRaster raster, int x, int y){
		List<Double> neighbors = new ArrayList<Double>();
		for(int i = x - 1; i < x + 2; ++i){
			for(int j = y - 1; j < y + 2; ++j){
				if(!(i < 0 || i > raster.getWidth() - 1 || j < 0 || j > raster.getHeight() - 1)){
					if(x != i || y != j)
						neighbors.add(raster.getPixel(i, j, new double[1])[0]);
				}
			}
		}
		/*for(int i = x - 2; i < x + 3; ++i){
			for(int j = y - 2; j < y + 3; ++j){
				if(!(i < 0 || i > raster.getWidth() - 1 || j < 0 || j > raster.getHeight() - 1)){
					if(x != i || y != j)
						neighbors.add(raster.getPixel(i, j, new double[1])[0]);
				}
			}
		}*/
		Collections.sort(neighbors);
		/*for(Double a : neighbors) System.out.print(a + " ");
		System.out.println();
		System.out.println(neighbors.get((neighbors.size() - 1)/2));*/
		return neighbors.get((neighbors.size() - 1)/2);
	}
	
	private static int giveIndex(double el, double[] tab){
		//int index = begin;
		int index = 0;
		for(int i = 0; i < tab.length; ++i){
			if(el - tab[i] < 0) break;	
			index++;
		}
		return index;
	}
	
}
