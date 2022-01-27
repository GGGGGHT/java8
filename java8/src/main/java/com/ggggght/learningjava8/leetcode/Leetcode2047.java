package com.ggggght.learningjava8.leetcode;

/**
 * <p>句子仅由小写字母（<code>'a'</code> 到 <code>'z'</code>）、数字（<code>'0'</code> 到 <code>'9'</code>）、连字符（<code>'-'</code>）、标点符号（<code>'!'</code>、<code>'.'</code> 和 <code>','</code>）以及空格（<code>' '</code>）组成。每个句子可以根据空格分解成 <strong>一个或者多个 token</strong> ，这些 token 之间由一个或者多个空格 <code>' '</code> 分隔。</p>
 *
 * <p>如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：</p>
 *
 * <ul>
 * <li>仅由小写字母、连字符和/或标点（不含数字）。</li>
 * <li><strong>至多一个</strong> 连字符 <code>'-'</code> 。如果存在，连字符两侧应当都存在小写字母（<code>"a-b"</code> 是一个有效单词，但 <code>"-ab"</code> 和 <code>"ab-"</code> 不是有效单词）。</li>
 * <li><strong>至多一个 </strong>标点符号。如果存在，标点符号应当位于 token 的 <strong>末尾</strong> 。</li>
 * </ul>
 *
 * <p>这里给出几个有效单词的例子：<code>"a-b."</code>、<code>"afad"</code>、<code>"ba-c"</code>、<code>"a!"</code> 和 <code>"!"</code> 。</p>
 *
 * <p>给你一个字符串 <code>sentence</code> ，请你找出并返回<em> </em><code>sentence</code> 中<strong> 有效单词的数目</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>sentence = "<em><strong>cat</strong></em> <em><strong>and</strong></em>  <em><strong>dog</strong></em>"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>句子中的有效单词是 "cat"、"and" 和 "dog"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>sentence = "!this  1-s b8d!"
 * <strong>输出：</strong>0
 * <strong>解释：</strong>句子中没有有效单词
 * "!this" 不是有效单词，因为它以一个标点开头
 * "1-s" 和 "b8d" 也不是有效单词，因为它们都包含数字
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>sentence = "<em><strong>alice</strong></em> <em><strong>and</strong></em>  <em><strong>bob</strong></em> <em><strong>are</strong></em> <em><strong>playing</strong></em> stone-game10"
 * <strong>输出：</strong>5
 * <strong>解释：</strong>句子中的有效单词是 "alice"、"and"、"bob"、"are" 和 "playing"
 * "stone-game10" 不是有效单词，因为它含有数字
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre><strong>输入：</strong>sentence = "<em><strong>he</strong></em> <em><strong>bought</strong></em> 2 <em><strong>pencils,</strong></em> 3 <em><strong>erasers,</strong></em> <em><strong>and</strong></em> 1  <em><strong>pencil-sharpener.</strong></em>"
 * <strong>输出：</strong>6
 * <strong>解释：</strong>句子中的有效单词是 "he"、"bought"、"pencils,"、"erasers,"、"and" 和 "pencil-sharpener."
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= sentence.length &lt;= 1000</code></li>
 * <li><code>sentence</code> 由小写英文字母、数字（<code>0-9</code>）、以及字符（<code>' '</code>、<code>'-'</code>、<code>'!'</code>、<code>'.'</code> 和 <code>','</code>）组成</li>
 * <li>句子中至少有 <code>1</code> 个 token</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li></div></div><br><div><li>👍 29</li><li>👎 0</li></div>
 */

public class Leetcode2047 {
  public static void main(String[] args) {
    System.out.println(countValidWords("q-,"));
    // System.out.println(countValidWords(
    //     " 62   nvtk0wr4f  8 qt3r! w1ph 1l ,e0d 0n 2v 7c.  n06huu2n9 s9   ui4 nsr!d7olr  q-, vqdo!btpmtmui.bb83lf g .!v9-lg 2fyoykex uy5a 8v whvu8 .y sc5 -0n4 zo pfgju 5u 4 3x,3!wl  fv4   s  aig cf j1 a i  8m5o1  !u n!.1tz87d3 .9    n a3  .xb1p9f  b1i a j8s2 cugf l494cx1! hisceovf3 8d93 sg 4r.f1z9w   4- cb r97jo hln3s h2 o .  8dx08as7l!mcmc isa49afk i1 fk,s e !1 ln rt2vhu 4ks4zq c w  o- 6  5!.n8ten0 6mk 2k2y3e335,yj  h p3 5 -0  5g1c  tr49, ,qp9 -v p  7p4v110926wwr h x wklq u zo 16. !8  u63n0c l3 yckifu 1cgz t.i   lh w xa l,jt   hpi ng-gvtk8 9 j u9qfcd!2  kyu42v dmv.cst6i5fo rxhw4wvp2 1 okc8!  z aribcam0  cp-zp,!e x  agj-gb3 !om3934 k vnuo056h g7 t-6j! 8w8fncebuj-lq    inzqhw v39,  f e 9. 50 , ru3r  mbuab  6  wz dw79.av2xp . gbmy gc s6pi pra4fo9fwq k   j-ppy -3vpf   o k4hy3 -!..5s ,2 k5 j p38dtd   !i   b!fgj,nx qgif "));
  }

  /**
   * 暴力解
   * @param sentence
   * @return
   */
  public static int countValidWords(String sentence) {
    var res = 0;
    String[] s = sentence.split("\\s+");
    for (String s1 : s) {
      if (isValid(s1)) {
        System.out.println(s1);
        res++;
      }
    }
    return res;
  }

  private static boolean isValid(String s) {
    if(s.length() == 0) return false;
    char[] chars = s.toCharArray();
    int _size = 0;
    for (int i = 0; i < chars.length; i++) {
      // 仅由小写字母、连字符和/或标点（不含数字）。
      if (Character.isDigit(chars[i])) return false;

      // 至多一个 连字符 '-'
      if ('-' == chars[i]) {
        if (_size >= 1) return false;
        if (i == 0 || i == chars.length -1 || !(Character.isLetter(chars[i - 1]) && Character.isLetter(chars[i + 1]))) {
          return false;
        }
        _size++;
      }

      if ((chars[i] == '!' || chars[i] == '.' || chars[i] == ',') && i != chars.length - 1) {
        return false;
      }
    }
    return true;
  }
}
