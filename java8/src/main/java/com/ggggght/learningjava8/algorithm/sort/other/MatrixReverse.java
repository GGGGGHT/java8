package com.ggggght.learningjava8.algorithm.sort.other;

import javax.websocket.ClientEndpoint;
import java.util.Arrays;

/**
 * @Desc 将指定的正方型翻转90度
 * [ 1  2   3  4  ]
 * [ 5  6   7  8  ]
 * [ 9  10  11 12 ]
 * [ 13 14  15 16 ]
 * |
 * |
 * [ 13  9   5  1 ]
 * [ 14  10  6  2 ]
 * [ 15  11  7  3 ]
 * [ 16  12  8  4 ]
 */
public class MatrixReverse {

    /**
     * 将指定的矩阵进行翻转90度
     *
     * @param matrix
     * @param sl 开始点的行坐标
     * @param sc 开始点的列坐标
     * @param el 结束点的行坐标
     * @param ec 结束点的列坐标
     */
    private static void reverseEdge(int[][] matrix,int sl,int sc,int el,int ec) {
        for (int i = 0; i < ec - sc; i++) {
            int tmp = matrix[sl][sc + i];
            matrix[sl][sc + i] = matrix[el - i][sc];
            matrix[el - i][sc] = matrix[el][ec - i];
            matrix[el][ec - i] = matrix[sl + i][ec];
            matrix[sl + i][ec] = tmp;
        }
    }

    public static void rotate(int[][] matrix) {
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;
        while (a < c) {
            reverseEdge(matrix, a++, b++, c--, d--);
        }
    }



    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("=========");
        printMatrix(matrix);
    }
}
