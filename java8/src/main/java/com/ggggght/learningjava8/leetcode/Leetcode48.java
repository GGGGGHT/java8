package com.ggggght.learningjava8.leetcode;
/**
 <p>给定一个 <em>n&nbsp;</em>×&nbsp;<em>n</em> 的二维矩阵&nbsp;<code>matrix</code> 表示一个图像。请你将图像顺时针旋转 90 度。</p>

 <p>你必须在<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a></strong> 旋转图像，这意味着你需要直接修改输入的二维矩阵。<strong>请不要 </strong>使用另一个矩阵来旋转图像。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg" style="height: 188px; width: 500px;" />
 <pre>
 <strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
 <strong>输出：</strong>[[7,4,1],[8,5,2],[9,6,3]]
 </pre>

 <p><strong>示例 2：</strong></p>
 <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg" style="height: 201px; width: 500px;" />
 <pre>
 <strong>输入：</strong>matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 <strong>输出：</strong>[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>n == matrix.length == matrix[i].length</code></li>
 <li><code>1 &lt;= n &lt;= 20</code></li>
 <li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
 </ul>

 <p>&nbsp;</p>
 <div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>矩阵</li></div></div><br><div><li>👍 1214</li><li>👎 0</li></div>
 */
public class Leetcode48 {
  /**
   * 先沿右上 - 左下的对角线翻转（270° +270°+ 一次镜像），再沿水平中线上下翻转（-180° +−180°+ 一次镜像），可以实现顺时针 9090 度的旋转效果
   *
   * @param matrix
   */
  public void rotate(int[][] matrix) {
    if(matrix.length == 0 || matrix.length != matrix[0].length) {
      return;
    }
    int nums = matrix.length;
    for (int i = 0; i < nums; ++i){
      for (int j = 0; j < nums - i; ++j){
        int temp = matrix[i][j];
        matrix[i][j] = matrix[nums - 1 - j][nums - 1 - i];
        matrix[nums - 1 - j][nums - 1 - i] = temp;
      }
    }

    for (int i = 0; i < (nums >> 1); ++i){
      for (int j = 0; j < nums; ++j){
        int temp = matrix[i][j];
        matrix[i][j] = matrix[nums - 1 - i][j];
        matrix[nums - 1 - i][j] = temp;
      }
    }
  }
}
