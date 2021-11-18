package com.ggggght.learningjava8.algorithm.dp;

import java.util.Arrays;

/**
 * 假设有排成一行的N个位置，记为1-N，N一定大于或等于2
 * 开始时机器人在期中的M的位置上(M一定在1-N之间),机器人可以往左走或者往右走，如果机器人在1位置上，则下一步一定是2 如果在N位置上，则下一步只能在N-1
 * 规定机器人必须走K步，最终能在来P位置上(P也一定是1-N之间)的方法有多少种 给定四个参数 N M K P 返回方法数
 * <p>
 * eg: N=5 M=2 K=3 P=3
 * <p>
 * 2->1->2->3 2->3->2->3 2->3->4->3
 * <p>
 * 所以返回3
 * <p>
 * 【后效性】是指一个递归状态的返回值与怎么到达这个状态的路径无关
 */
public class Robot {

	public static void main(String[] args) {
		// System.out.println(new Robot().walk(5, 2, 3, 3));
		// System.out.println(new Robot().dpWith2(5, 2, 3, 3));
		System.out.println(new Robot().dpWith1(5, 2, 3, 3));
	}

	public int walk1(int N, int M, int K, int P) {
		return walk(N, M, K, P);
	}

	public int walk(int N, int cur, int rest, int P) {
		if (rest == 0) {
			return cur == P ? 1 : 0;
		}

		if (cur == 1) {
			walk(N, 2, rest - 1, P);
		}

		if (cur == P) {
			walk(N, P - 1, rest - 1, P);
		}

		return walk(N, cur + 1, rest - 1, P) + walk(N, cur - 1, rest - 1, P);
	}

	public int dpWith2(int N, int M, int K, int P) {
		int[][] dp = new int[K + 1][N + 1];
		dp[0][P] = 1;
		for (int i = 1; i <= K; i++) {
			for (int j = 1; j <= N; j++) {
				if (j == 1) {
					dp[i][j] = dp[i - 1][2];
				}
				else if (j == N) {
					dp[i][j] = dp[i - 1][N - 1];
				}
				else {
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
				}
			}
		}
		return dp[K][M];
	}

	// (5, 2, 3, 3)
	//
	public int dpWith1(int N, int M, int K, int P) {
		int[] dp = new int[N + 1];
		dp[P] = 1;
		System.out.println("第0次数组的结果: " + Arrays.toString(dp));
		for (int i = 1; i <= K; i++) {
			int leftUp = dp[1];
			for (int j = 1; j <= N; j++) {
				int tmp = dp[j];
				if (j == 1) {
					dp[j] = dp[j + 1];
				}
				else if (j == N) {
					dp[j] = leftUp;
				}
				else {
					dp[j] = leftUp + dp[j + 1];
				}
				leftUp = tmp;
			}

			System.out.printf("第%d次数组的结果: %s\n", i, Arrays.toString(dp));
		}
		return dp[M];
	}

}
