package pl.lab5.util;

import java.awt.image.WritableRaster;

import pl.workspace.model.ImageModel;

public class CompressionUtil {
	
	public static int RLE(ImageModel model){
		WritableRaster raster = model.getImage().getRaster();
		int after_size = 0;
		int previous;
		int counter = 0;
		for(int y = 0; y < raster.getHeight(); ++y){
			for(int x = 1; x < raster.getWidth(); ++x){
				previous = raster.getPixel(x - 1, y, new int[1])[0];
				if(raster.getPixel(x, y, new int[1])[0] != previous || counter >= 127){
					after_size++;
					counter = 0;
				}
				else{
					counter++;
				}
			}
			after_size++;
			counter = 0;
		}
		return after_size;
	}
	
	public static int READ(ImageModel model){
		WritableRaster raster = model.getImage().getRaster();
        int PIXELLEN = 1; //4 bajty - 32 bity
        int WORDLEN = 1; //4 bajty - 32 bity

        int newColor = 255, lastColor = 0;
        int repeatCount = 0;
        int total = 0;

        int width = raster.getWidth();
        int height = raster.getHeight();
        int fld = width * height;
        int before, after;

        before = fld * PIXELLEN;

        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                newColor = raster.getPixel(x, y, new int[1])[0];

                if (newColor == lastColor) {
                    repeatCount++;
                }
                else {

                    if (repeatCount > 0) {
                        total += WORDLEN + PIXELLEN;
                        repeatCount = 0;
                    }

                    total += WORDLEN + PIXELLEN;
                }

                lastColor = raster.getPixel(x, y, new int[1])[0];
            }

        if (repeatCount > 0) {
            total += WORDLEN + PIXELLEN;
            repeatCount = 0;
        }

        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                newColor = raster.getPixel(x, y, new int[1])[0];

                if (newColor == lastColor) {
                    repeatCount++;
                }
                else {

                    if (repeatCount > 0) {
                        total += WORDLEN + PIXELLEN;
                        repeatCount = 0;
                    }

                    total += WORDLEN + PIXELLEN;
                }

                lastColor = raster.getPixel(x, y, new int[1])[0];
            }

        if (repeatCount > 0) {
            total += WORDLEN + PIXELLEN;
            repeatCount = 0;
        }

        after = total / 2;
        return after;
	}
	
	public static int huffman(ImageModel model){
		WritableRaster raster = model.getImage().getRaster();
        int x = 2;
        int licznik = 0;
        int przed;
        int po = 0;
        int moc = 2;
        int ilosc = 0;

        double[] phist = model.getHistogram();
        przed = raster.getWidth() * raster.getHeight();

        for (int i = 0; i < 256; i++) {
            if (phist[i] != 0) {
                licznik++;
                po += (licznik * phist[i]);

                if (licznik >= Math.pow(x, moc)) {
                    licznik = 0;
                    moc++;
                }
                ilosc++;
            }

            if (licznik == 1) {
                po = przed + 1;
            }
        }

        po /= 8;
        po += (ilosc * 2);

        return po;
	}
	
	public static boolean blocks(ImageModel model, int blocksize){
		if(model.getImage().getWidth() % 32 != 0 || model.getImage().getHeight() % 32 != 0)	//tylko dla obrazkow o wymiarach podzielnych bez reszty przez 32
			return false;

		double avg = 0;
		double avg_top = 0, avg_bot = 0;
		int top_count = 0, bot_count = 0;
		int pixel;
		WritableRaster raster = model.getImage().getRaster();
		for(int y = 0; y < raster.getHeight(); y = y + blocksize){
			for(int x = 0; x < raster.getWidth(); x = x + blocksize){
				for(int i = 0; i < blocksize; ++i){
					for(int j = 0; j < blocksize; ++j){
						avg += raster.getPixel(x+j, y+i, new int[1])[0];
					}
				}
				avg = avg/(blocksize*blocksize);
				
				for(int i = 0; i < blocksize; ++i){
					for(int j = 0; j < blocksize; ++j){
						pixel = raster.getPixel(x+j, y+i, new int[1])[0];
						if(pixel < avg){
							avg_bot += pixel;
							bot_count++;
						}
						else{
							avg_top += pixel;
							top_count++;
						}
					}
				}
				int top_value = (int) Math.round(avg_top/top_count);
				int bot_value = (int) Math.round(avg_bot/bot_count);
				
				for(int i = 0; i < blocksize; ++i){
					for(int j = 0; j < blocksize; ++j){
						pixel = raster.getPixel(x+j, y+i, new int[1])[0];
						if(pixel < avg)
							raster.setPixel(x+j, y+i, new int[]{bot_value});
						else
							raster.setPixel(x+j, y+i, new int[]{top_value});
					}
				}
				
				avg = 0;
				avg_top = 0;
				avg_bot = 0;
				bot_count = 0;
				top_count = 0;
			}
		}
		model.modelChanged();
		return true;
	}
	
	public static void tree(){
		
	}

}
