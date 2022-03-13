package com.ggggght.learningjava8.leetcode;
/**
 <p>给定一个表示数据的整数数组&nbsp;<code>data</code>&nbsp;，返回它是否为有效的 <strong>UTF-8</strong> 编码。</p>

 <p><strong>UTF-8</strong> 中的一个字符可能的长度为 <strong>1 到 4 字节</strong>，遵循以下的规则：</p>

 <ol>
 <li>对于 <strong>1 字节</strong>&nbsp;的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。</li>
 <li>对于 <strong>n 字节</strong>&nbsp;的字符 (n &gt; 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。</li>
 </ol>

 <p>这是 UTF-8 编码的工作方式：</p>

 <pre>
 <code>   Char. number range  |        UTF-8 octet sequence
 (hexadecimal)    |              (binary)
 --------------------+---------------------------------------------
 0000 0000-0000 007F | 0xxxxxxx
 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 </code></pre>

 <p><strong>注意：</strong>输入是整数数组。只有每个整数的 <strong>最低 8 个有效位</strong> 用来存储数据。这意味着每个整数只表示 1 字节的数据。</p>

 <p>&nbsp;</p>

 <p><strong>示例 1：</strong></p>

 <pre>
 <strong>输入：</strong>data = [197,130,1]
 <strong>输出：</strong>true
 <strong>解释：</strong>数据表示字节序列:<strong>11000101 10000010 00000001</strong>。
 这是有效的 utf-8 编码，为一个 2 字节字符，跟着一个 1 字节字符。
 </pre>

 <p><strong>示例 2：</strong></p>

 <pre>
 <strong>输入：</strong>data = [235,140,4]
 <strong>输出：</strong>false
 <strong>解释：</strong>数据表示 8 位的序列: <strong>11101011 10001100 00000100</strong>.
 前 3 位都是 1 ，第 4 位为 0 表示它是一个 3 字节字符。
 下一个字节是开头为 10 的延续字节，这是正确的。
 但第二个延续字节不以 10 开头，所以是不符合规则的。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示:</strong></p>

 <ul>
 <li><code>1 &lt;= data.length &lt;= 2 * 10<sup>4</sup></code></li>
 <li><code>0 &lt;= data[i] &lt;= 255</code></li>
 </ul>
 <div><div>Related Topics</div><div><li>位运算</li><li>数组</li></div></div><br><div><li>👍 156</li><li>👎 0</li></div>
 */
public class Leetcode393 {
  public static void main(String[] args) {
    // System.out.println(toBinaryString(4));
    // System.out.println(validUtf8(new int[] {197, 130, 1}));
    // System.out.println(validUtf8(new int[] {235,140,4}));
    System.out.println(validUtf8(new int[] {237}));
  }

  public static  boolean validUtf8(int[] data) {
    var len = data.length;
    for (int i = 0; i < len; i++) {
      String a = toBinaryString(data[i]);
      if (a.startsWith("0")) {
      } else if (a.startsWith("110")) {
        if (++i >= len || !toBinaryString(data[i]).startsWith("10")) {
          return false;
        }
      } else if (a.startsWith("1110")) {
        for (int i1 = 0; i1 < 2; i1++) {
          if (++i >= len || !toBinaryString(data[i]).startsWith("10")) {
            return false;
          }
        }
      } else if (a.startsWith("11110")) {
        for (int i1 = 0; i1 < 3; i1++) {
          if (++i >= len || !toBinaryString(data[i]).startsWith("10")) {
            return false;
          }
        }
      } else {
        return false;
      }
    }

    return true;
  }

  public static String toBinaryString(int i) {
    String s = Integer.toBinaryString(i);
    if(s.length() == 8) return s;

    int len = s.length();
    StringBuilder t = new StringBuilder();
    for (int i1 = 0; i1 < 8 - len; i1++) {
      t.append('0');
    }
    return t.append(s).toString();
  }
}
