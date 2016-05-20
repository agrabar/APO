package pl.lab3.filtracja_liniowa;

public class PredefinedMasks {
	
	public static final int[][] wygladzanie1 = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
	public static final int[][] wygladzanie2 = new int[][]{{1, 1, 1}, {1, 2, 1}, {1, 1, 1}};
	public static final int[][] wygladzanie3 = new int[][]{{1, 2, 1}, {2, 4, 2}, {1, 2, 1}};
	public static final int[][] wygladzanie4 = new int[][]{{1, 1, 1}, {1, 8, 1}, {1, 1, 1}};
	
	public static final int[][] wyostrzanie1 = new int[][]{{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
	public static final int[][] wyostrzanie2 = new int[][]{{0, -1, 0}, {-1, 4, -1}, {0, -1, 0}};
	public static final int[][] wyostrzanie3 = new int[][]{{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};
	public static final int[][] wyostrzanie4 = new int[][]{{1, -2, 1}, {-2, 4, -2}, {1, -2, 1}};
	
	public static final int[][] detekcja1 = new int[][]{{1, -2, 1}, {-2, 5, -2}, {1, -2, 1}};
	public static final int[][] detekcja2 = new int[][]{{-1, -1, -1}, {-1, 9, -1}, {-1, -1, -1}};
	public static final int[][] detekcja3 = new int[][]{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
	public static final int[][] detekcja4 = new int[][]{{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
	
	public static final int[][] sobel1 = new int[][]{{-1,0,1},{-2,0,2},{-1,0,1}};
	public static final int[][] sobel2 = new int[][]{{-1,-2,-1},{0,0,0},{1,2,1}};
	
	public static final int[][] roberts1 = new int[][]{{1,0},{0,-1}};
	public static final int[][] roberts2 = new int[][]{{0,-1},{1,0}};
}
