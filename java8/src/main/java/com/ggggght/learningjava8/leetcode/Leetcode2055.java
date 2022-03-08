public class Leetcode2055 {
  // 在给定 s 的前提下，每个位置其左边和右边最近的蜡烛唯一确定。我们可以在预处理前缀和的同时，预处理每个位置左右最近的蜡烛下标，从而省去每次二分。
  public static int[] platesBetweenCandles(String s, int[][] qs) {
    char[] cs = s.toCharArray();
    int n = cs.length, m = qs.length;
    int[] l = new int[n], r = new int[n];
    int[] sum = new int[n + 1];
    for (int i = 0, j = n - 1, p = -1, q = -1; i < n; i++, j--) {
      if (cs[i] == '|') p = i;
      if (cs[j] == '|') q = j;
      l[i] = p; r[j] = q;
      sum[i + 1] = sum[i] + (cs[i] == '*' ? 1 : 0);
    }
    int[] ans = new int[m];
    for (int i = 0; i < m; i++) {
      int a = qs[i][0], b = qs[i][1];
      int c = r[a], d = l[b];
      if (c != -1 && c <= d) ans[i] = sum[d + 1] - sum[c];
    }
    return ans;
  }
}
