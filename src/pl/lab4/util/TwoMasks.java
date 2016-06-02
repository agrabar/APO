package pl.lab4.util;

import java.util.Arrays;

public class TwoMasks {
	
	//dwie maski 3x3 przyjmuje
	public static int[][] getMask(int[][] mask1, int [][] mask2){
		int[][] out = new int[5][5];
		int[][] pom = new int[7][7];
		
		for(int[] row : pom){
			Arrays.fill(row, 0);
		}
		pom[2][2] = mask1[0][0];
		pom[2][3] = mask1[0][1];
		pom[2][4] = mask1[0][2];
		pom[3][2] = mask1[1][0];
		pom[3][3] = mask1[1][1];
		pom[3][4] = mask1[1][2];
		pom[4][2] = mask1[2][0];
		pom[4][3] = mask1[2][1];
		pom[4][4] = mask1[2][2];
		
		for(int i = 0; i < out.length; ++i){
			for(int j = 0; j < out[i].length; ++j){
				out[i][j] = pom[1+i-1][1+j-1] * mask2[0][0] + pom[1+i-1][1+j] * mask2[0][1] + pom[1+i-1][1+j+1] * mask2[0][2] +
						pom[1+i][1+j-1] * mask2[1][0] + pom[1+i][1+j] * mask2[1][1] + pom[1+i][1+j+1] * mask2[1][2] +
						pom[1+i+1][1+j-1] * mask2[2][0] + pom[1+i+1][1+j] * mask2[2][1] + pom[1+i+1][1+j+1] * mask2[2][2];
			}
		}
		
		return out;
	}

}
