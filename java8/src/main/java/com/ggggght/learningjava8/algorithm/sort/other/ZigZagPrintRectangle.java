package com.ggggght.learningjava8.algorithm.sort.other;

/**
 * @desc 之字型打印矩形 如： [ 1 2 3 4 ] [ 5 6 7 8 ] [ 9 0 1 2 ]
 * <p>
 * 1 2 5 9 6 3 4 7 0 1 8 2
 */
@SuppressWarnings("all")
public class ZigZagPrintRectangle {

	public static void main(String[] args) {
		int[][] source = new int[3][];
		source[0] = new int[] { 1, 2, 3, 4 };
		source[1] = new int[] { 5, 6, 7, 8 };
		source[2] = new int[] { 9, 0, 1, 2 };
		printZigzag(source);
	}

	private static void printZigzag(int[][] source) {
		// define tow point a(0,0) b(0,0), point a from left to right when arrive right
		// from up to down
		// and b from up to down when arrive down then from left to right
		// point a and point b is the line to print
		int al = 0;
		int ac = 0;
		int bl = 0;
		int bc = 0;
		// 是否从右上往左下打印
		boolean rightDown = false;
		int llen = source.length - 1;
		int clen = source[0].length - 1;
		// mean al not arrive the matrix right bottom point + 1
		while (al != llen + 1) {
			print(al, ac, bl, bc, rightDown, source);
			al = ac == clen ? al + 1 : al;
			ac = ac == clen ? ac : ac + 1;
			bc = bl == llen ? bc + 1 : bc;
			bl = bl == llen ? bl : bl + 1;
			rightDown = !rightDown;
		}
	}

	private static void print(int al, int ac, int bl, int bc, boolean rightDown, int[][] source) {
		if (rightDown) {
			while (al <= bl) {
				System.out.print(source[al++][ac--] + "  ");
			}
			return;
		}

		while (bl >= al) {
			System.out.print(source[bl--][bc++] + "  ");
		}
	}

}
