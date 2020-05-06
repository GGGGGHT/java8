package com.ggggght.learningjava8.datastruct.recurision;

import java.util.Arrays;

/**
 * @author ght
 * @Desc
 * @date 2020-01-12 11:39 AM
 * 使用递归来求解迷宫问题
 * 迷宫地图
 * 1表示围墙,0表示小球可以走的位置
 * 小球从(1,1)位置出发 如果可以走到(6,5)的位置则证明有通路
 * [1, 1, 1, 1, 1, 1, 1]
 * [1, 0, 0, 0, 0, 0, 1]
 * [1, 0, 0, 0, 0, 0, 1]
 * [1, 1, 1, 0, 0, 0, 1]
 * [1, 0, 0, 0, 0, 0, 1]
 * [1, 0, 0, 0, 0, 0, 1]
 * [1, 0, 0, 0, 0, 0, 1]
 * [1, 1, 1, 1, 1, 1, 1]
 */

@SuppressWarnings("all")
public class Maze {
    static int[][] map = new int[8][7];

    static {
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        /*map[1][2] = 1;
        map[2][2] = 1;*/
    }

    /**
     * @param map 传入的地图
     * @param i,j 用来表示小球的起始位置
     * @return 如果成功找到通路则返回ture否则返回false
     * 当map[i][j] == 0 时,则证明该点小球还未走过 当为2时则证明是通路 3表示已经走过该位置但此路不通
     * 在走迷宫时,需要确定一个策略 下-> 右 -> 上 -> 左 如果该点走不通 则回溯
     * @Desc 使用递归回溯来找到通路
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) { // 此时需要按照策略来进行
                // 先假设该点可以走通
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) { // 先向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { // 向左走
                    return true;
                } else { // 如果都没有走通 说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) { // 此时需要按照策略来进行
                // 先假设该点可以走通
                map[i][j] = 2;
                if (setWay2(map, i, j + 1)) { // 先向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) { // 向下走
                    return true;
                } else if (setWay2(map, i, j + 1)) { // 向左走
                    return true;
                } else if (setWay2(map, i - 1, j)) { // 向上走
                    return true;
                } else { // 如果都没有走通 说明该点走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        Arrays.stream(map).forEach(i -> System.out.println(Arrays.toString(i)));
        setWay2(map, 1, 1);
        System.out.println();
        Arrays.stream(map).forEach(i -> System.out.println(Arrays.toString(i)));
    }
}
