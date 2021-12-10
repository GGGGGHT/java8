package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>给你一个字符串 <code>licensePlate</code> 和一个字符串数组 <code>words</code> ，请你找出并返回 <code>words</code> 中的 <strong>最短补全词</strong> 。</p>
 *
 * <p><strong>补全词 </strong>是一个包含 <code>licensePlate</code> 中所有的字母的单词。在所有补全词中，最短的那个就是 <strong>最短补全词</strong> 。</p>
 *
 * <p>在匹配 <code>licensePlate</code> 中的字母时：</p>
 *
 * <ul>
 * <li><strong>忽略</strong>&nbsp;<code>licensePlate</code> 中的 <strong>数字和空格 </strong>。</li>
 * <li><strong>不区分大小写</strong>。</li>
 * <li>如果某个字母在 <code>licensePlate</code> 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。</li>
 * </ul>
 *
 * <p>例如：<code>licensePlate</code><code> = "aBc 12c"</code>，那么它的补全词应当包含字母 <code>'a'</code>、<code>'b'</code> （忽略大写）和两个 <code>'c'</code> 。可能的 <strong>补全词</strong> 有 <code>"abccdef"</code>、<code>"caaacab"</code> 以及 <code>"cbca"</code> 。</p>
 *
 * <p>请你找出并返回 <code>words</code> 中的 <strong>最短补全词</strong> 。题目数据保证一定存在一个最短补全词。当有多个单词都符合最短补全词的匹配条件时取 <code>words</code> 中 <strong>最靠前的</strong> 那个。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 * <strong>输出：</strong>"steps"
 * <strong>解释：</strong>最短补全词应该包括 "s"、"p"、"s"（忽略大小写） 以及 "t"。
 * "step" 包含 "t"、"p"，但只包含一个 "s"，所以它不符合条件。
 * "steps" 包含 "t"、"p" 和两个 "s"。
 * "stripe" 缺一个 "s"。
 * "stepple" 缺一个 "s"。
 * 因此，"steps" 是唯一一个包含所有字母的单词，也是本例的答案。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 * <strong>输出：</strong>"pest"
 * <strong>解释：</strong>licensePlate 只包含字母 "s" 。所有的单词都包含字母 "s" ，其中 "pest"、"stew"、和 "show" 三者最短。答案是 "pest" ，因为它是三个单词中在 words 里最靠前的那个。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>licensePlate = "Ah71752", words = ["suggest","letter","of","husband","easy","education","drug","prevent","writer","old"]
 * <strong>输出：</strong>"husband"
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>licensePlate = "OgEu755", words = ["enough","these","play","wide","wonder","box","arrive","money","tax","thus"]
 * <strong>输出：</strong>"enough"
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>licensePlate = "iMSlpe4", words = ["claim","consumer","student","camera","public","never","wonder","simple","thought","use"]
 * <strong>输出：</strong>"simple"
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= licensePlate.length &lt;= 7</code></li>
 * <li><code>licensePlate</code> 由数字、大小写字母或空格 <code>' '</code> 组成</li>
 * <li><code>1 &lt;= words.length &lt;= 1000</code></li>
 * <li><code>1 &lt;= words[i].length &lt;= 15</code></li>
 * <li><code>words[i]</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 69</li><li>👎 0</li></div>
 */

public class Leetcode748 {
  public static void main(String[] args) {
    System.out.println(
        shortestCompletingWord("GrC8950",
            new String[] {"measure", "other", "every", "base", "according", "level", "meeting",
                "none", "marriage", "rest"}));
  }

  public static String shortestCompletingWord(String licensePlate, String[] words) {
    // 将所有的字符映射到数组中
    int[] chars = new int[26];
    for (int i = 0; i < licensePlate.length(); i++) {
      char c1 = licensePlate.charAt(i);
      if ((c1 >= 'a' && c1 <= 'z')) {
        chars[c1 - 'a']++;
      } else if (c1 >= 'A' && c1 <= 'Z') {
        chars[c1 + 32 - 'a']++;
      }
    }

    // System.out.println(Arrays.toString(chars));
    int[] dp = new int[words.length];
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      int[] ints = new int[26];
      for (int i1 = 0; i1 < word.length(); i1++) {
        ints[word.charAt(i1) - 'a']++;
      }
      int idx = 0;
      // System.out.println(Arrays.toString(ints) + " ," + word);
      while (idx <= 25) {
        if (chars[idx] != 0) {
          if (chars[idx] > ints[idx]) {
            break;
          }
        }
        idx++;
      }
      if (idx == 26) {
        dp[i] = 1;
      }
    }

    int minIndex = -1;
    int min = 1001;
    for (int i = 0; i < dp.length; i++) {
      if (dp[i] == 1 && words[i].length() < min) {
        min = words[i].length();
        minIndex = i;
      }
    }

    // System.out.println(Arrays.toString(dp));
    return minIndex == -1 ? "" : words[minIndex];
  }
}
