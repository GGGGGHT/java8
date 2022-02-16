package com.ggggght.learningjava8.leetcode;
/**
 <p>给你两个字符串 <code>word1</code> 和 <code>word2</code> 。你需要按下述方式构造一个新字符串 <code>merge</code> ：如果 <code>word1</code> 或 <code>word2</code> 非空，选择 <strong>下面选项之一</strong> 继续操作：</p>

 <ul>
 <li>如果 <code>word1</code> 非空，将 <code>word1</code> 中的第一个字符附加到 <code>merge</code> 的末尾，并将其从 <code>word1</code> 中移除。

 <ul>
 <li>例如，<code>word1 = "abc" </code>且 <code>merge = "dv"</code> ，在执行此选项操作之后，<code>word1 = "bc"</code> ，同时 <code>merge = "dva"</code> 。</li>
 </ul>
 </li>
 <li>如果 <code>word2</code> 非空，将 <code>word2</code> 中的第一个字符附加到 <code>merge</code> 的末尾，并将其从 <code>word2</code> 中移除。
 <ul>
 <li>例如，<code>word2 = "abc" </code>且 <code>merge = ""</code> ，在执行此选项操作之后，<code>word2 = "bc"</code> ，同时 <code>merge = "a"</code> 。</li>
 </ul>
 </li>
 </ul>

 <p>返回你可以构造的字典序 <strong>最大</strong> 的合并字符串<em> </em><code>merge</code><em> 。</em></p>

 <p>长度相同的两个字符串 <code>a</code> 和 <code>b</code> 比较字典序大小，如果在 <code>a</code> 和 <code>b</code> 出现不同的第一个位置，<code>a</code> 中字符在字母表中的出现顺序位于 <code>b</code> 中相应字符之后，就认为字符串 <code>a</code> 按字典序比字符串 <code>b</code> 更大。例如，<code>"abcd"</code> 按字典序比 <code>"abcc"</code> 更大，因为两个字符串出现不同的第一个位置是第四个字符，而 <code>d</code> 在字母表中的出现顺序位于 <code>c</code> 之后。</p>

 <p> </p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>word1 = "cabaa", word2 = "bcaaa"
 <strong>输出：</strong>"cbcabaaaaa"
 <strong>解释：</strong>构造字典序最大的合并字符串，可行的一种方法如下所示：
 - 从 word1 中取第一个字符：merge = "c"，word1 = "abaa"，word2 = "bcaaa"
 - 从 word2 中取第一个字符：merge = "cb"，word1 = "abaa"，word2 = "caaa"
 - 从 word2 中取第一个字符：merge = "cbc"，word1 = "abaa"，word2 = "aaa"
 - 从 word1 中取第一个字符：merge = "cbca"，word1 = "baa"，word2 = "aaa"
 - 从 word1 中取第一个字符：merge = "cbcab"，word1 = "aa"，word2 = "aaa"
 - 将 word1 和 word2 中剩下的 5 个 a 附加到 merge 的末尾。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>word1 = "abcabc", word2 = "abdcaba"
 <strong>输出：</strong>"abdcabcabcaba"
 </pre>

 <p> </p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>1 <= word1.length, word2.length <= 3000</code></li>
 <li><code>word1</code> 和 <code>word2</code> 仅由小写英文组成</li>
 </ul>
 <div><div>Related Topics</div><div><li>贪心</li><li>双指针</li><li>字符串</li></div></div><br><div><li>👍 24</li><li>👎 0</li></div>
 */

public class Leetcodee1754 {
  public static void main(String[] args) {
    // cbcabaaaaa
    // cbcabaaaaa
    // abdcabcabcaba
    // abdcabcabcaba
    // System.out.println(largestMerge("cabaa", "bcaaa"));
    // cabaa
    // bcaaa
    // cbcaba
    // System.out.println(largestMerge("abcabc", "abdcaba"));

    System.out.println(largestMerge("nnnnpnnennenpnnnnneenpnn",
        "nnnennnnnnpnnennnnennnnee"));
    // nnnnp nn ennenpnnnnneenpnn
    // nnn ennnnnn pnnennnnennnn ee
    // nnnnp nnn nn ennnnnnpnnennnnennnnennenpnnnnneenpnnee
    // nnnnp nnn nn ennnnnnpnnennnnennnnennenpnnnnneenpnnee
  }

  public static String largestMerge(String word1, String word2) {
    int i = 0, j = 0;
    StringBuffer merge = new StringBuffer();
    int len1 = word1.length(), len2 = word2.length();
    while (i < len1 || j < len2) {
      if (word1.substring(i).compareTo(word2.substring(j)) < 0)
        merge.append(word2.charAt(j++));
      else merge.append(word1.charAt(i++));
    }
    return merge.toString();

  }
}
