package com.ggggght.learningjava8.datastruct;

import java.util.Arrays;

/**
 * @author ght
 * @date 2019-12-31 9:01 AM
 * @Desc  稀疏数组的使用 包括创建及还原
 * 稀疏数组可以看做是普通数组的压缩 这里说的普通数组是指无效数据量远大于有效数据量的数组
 * 使用场景:当需要将一个二维数组存入磁盘，而二维数组中有大量无用数据时，将之转换成稀疏数组再存储，可以节约磁盘空间。
 * 例如: 当我们需要存储一个x*y的二维数组时,只有n个(非常小)有效数据 此时要将该数组全部存储就比较消耗性能
 * 所以我们就需要用到稀疏数组将该二维数组压缩来达到节省资源的目的
 *
 * 存储前
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 * [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
 *
 * 压缩后
 * row  column data
 * [11,   11,    3]
 * [1,   2,     1]
 * [2,   3,     2]
 * [8,   8,     3]
 */

@SuppressWarnings("all")
public class SparseArray {
    public static void main(String[] args) throws Exception {
        // 创建一个原始的二维数据 11 * 11
        // 0表示没有数据棋子1代表第一个棋子2代表第2个棋子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[8][8] = 3;

        // 先遍历一下二维数组取出所有非0的值的个数
        int count = 0;
        for (int[] ints : chessArr1) {
            for (int i = 0, j = ints.length; i < j; i++) {
                if (ints[i] != 0) {
                    count++;
                }
            }
        }
        // 创建一个稀疏数组 第1行表示原二维数组的大小 [0][0]表示有多少行[0][1]表示有多少列 [0][2]表示有多少个有效数字
        int [][] sparseArr = new int[count + 1][3];
        // 给稀疏赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = count;
        // cur表示当前该写入的行数
        int cur = 1;
        // 遍历二维数组 将非0的值存入稀疏数组中去
        for (int i = 0, l = chessArr1.length; i < l; i++) {
            for (int j = 0, t = chessArr1[i].length; j < t; j++) {
                if (chessArr1[i][j] != 0) {
                    sparseArr[cur][0] = i;
                    sparseArr[cur][1] = j;
                    sparseArr[cur][2] = chessArr1[i][j];
                    cur++;
                }
            }
        }
        System.out.println("遍历稀疏数组:");
        Arrays.stream(sparseArr).forEach(a -> System.out.println(Arrays.toString(a)));
        // 将稀疏数组恢复为原来的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1, j = sparseArr.length; i < j; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        // for (int[] ints : chessArr2) {
        //     System.out.println(Arrays.toString(ints));
        // }
        // 遍历还原的二维数组
        System.out.println("还原后的二维数组:");
        Arrays.stream(chessArr2).forEach(i -> System.out.println(Arrays.toString(i)));

    }
}
