package com.ggggght.learningjava8.leetcode;
/**
 <p><a href="https://baike.baidu.com/item/%E5%A4%8D%E6%95%B0/254365?fr=aladdin" target="_blank">复数</a> 可以用字符串表示，遵循 <code>"<strong>实部</strong>+<strong>虚部</strong>i"</code> 的形式，并满足下述条件：</p>

 <ul>
 <li><code>实部</code> 是一个整数，取值范围是 <code>[-100, 100]</code></li>
 <li><code>虚部</code> 也是一个整数，取值范围是 <code>[-100, 100]</code></li>
 <li><code>i<sup>2</sup> == -1</code></li>
 </ul>

 <p>给你两个字符串表示的复数 <code>num1</code> 和 <code>num2</code> ，请你遵循复数表示形式，返回表示它们乘积的字符串。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>num1 = "1+1i", num2 = "1+1i"
 <strong>输出：</strong>"0+2i"
 <strong>解释：</strong>(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>num1 = "1+-1i", num2 = "1+-1i"
 <strong>输出：</strong>"0+-2i"
 <strong>解释：</strong>(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>num1</code> 和 <code>num2</code> 都是有效的复数表示。</li>
 </ul>
 <div><div>Related Topics</div><div><li>数学</li><li>字符串</li><li>模拟</li></div></div><br><div><li>👍 87</li><li>👎 0</li></div>
 */

public class Leetcode537 {
  public static void main(String[] args) {
    System.out.println(complexNumberMultiply("1+1i", "1+1i"));
    System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
    System.out.println(complexNumberMultiply("-3+5i",
        "4+-3i"));
  }

  public static String complexNumberMultiply(String num1, String num2) {
    String[] s1 = num1.split("i");
    String[] s2 = num2.split("i");
    var real1 = s1[0];
    var real2 = s2[0];
    String[] r1 = real1.split("\\+");
    String[] r2 = real2.split("\\+");
    Integer l1 = Integer.valueOf(r1[0]);
    Integer l2 = Integer.valueOf(r2[0]);

    Integer _1 = Integer.valueOf(r1[1]);
    Integer _2 = Integer.valueOf(r2[1]);
    int i = l1 * l2;
    int m = _1 * l2 + _2 * l1;
    int o = i + (_1 * _2 * -1);

    return o + "+" + m + "i";
  }
}
