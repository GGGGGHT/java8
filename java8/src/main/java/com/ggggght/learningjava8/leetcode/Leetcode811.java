package com.ggggght.learningjava8.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 一个网站域名，如"discuss.leetcode.com"，包含了多个子域名。作为顶级域名，常用的有"com"，下一级则有"leetcode.com"，最
 * 低的一级为"discuss.leetcode.com"。当我们访问域名"discuss.leetcode.com"时，也同时访问了其父域名"leetcode.
 * com"以及顶级域名 "com"。
 * <p>
 * 给定一个带访问次数和域名的组合，要求分别计算每个域名被访问的次数。其格式为访问次数+空格+地址，例如："9001 discuss.leetcode.
 * com"。
 * <p>
 * 接下来会给出一组访问次数和域名组合的列表cpdomains 。要求解析出所有域名的访问次数，输出格式和输入格式相同，不限定先后顺序。
 * <p>
 * <p>
 * 示例 1:
 * 输入:
 * ["9001 discuss.leetcode.com"]
 * 输出:
 * ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
 * 说明:
 * 例子中仅包含一个网站域名："discuss.leetcode.com"。按照前文假设，子域名"leetcode.com"和"com"都会被访问，所以它们都被
 * 访问了9001次。
 * <p>
 * <p>
 * 示例 2
 * 输入:
 * ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
 * 输出:
 * ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1
 * intel.mail.com","951 com"]
 * 说明:
 * 按照假设，会访问"google.mail.com" 900次，"yahoo.com" 50次，"intel.mail.com" 1次，"wiki.org"
 * 5次。
 * 而对于父域名，会访问"mail.com" 900+1 = 901次，"com" 900 + 50 + 1 = 951次，和 "org" 5 次。
 * <p>
 * 注意事项：
 * <p>
 * <p>
 * cpdomains 的长度小于 100。
 * 每个域名的长度小于100。
 * 每个域名地址包含一个或两个"."符号。
 * 输入中任意一个域名的访问次数都小于10000。
 * <p>
 * Related Topics 数组 哈希表 字符串 👍 101 👎 0
 */
public class Leetcode811 {
  public static void main(String[] args) {
    Leetcode811 leetcode811 = new Leetcode811();
    var str = new String[] {"2777 nak.mkw.co", "654 yaw.lmm.ca", "3528 jyx.arz.us",
        "3215 bll.hoh.network", "408 tdt.zfz.network", "3322 xhe.team", "8342 srp.team",
        "9259 bfo.net", "3875 brk.ato.network", "2531 mce.ser.com", "2471 czb.us",
        "4806 xss.dfa.co", "654 yls.yjt.co", "767 irh.epf.us", "1501 ara.ca", "243 qay.network",
        "9103 vbo.org", "6890 bfo.network", "4296 xtb.jre.us", "2329 vva.qay.network",
        "9846 fuw.org", "4681 lwf.ytn.network", "1781 lbk.ksc.co", "7464 jpd.fff.co",
        "2740 xhe.org", "4602 weq.buf.team", "3055 fdy.jkx.com", "5705 mqa.wsv.net",
        "6629 vdu.bfo.team", "9897 lra.uyy.org", "8167 ahm.jre.team", "9374 jep.ato.co",
        "3624 vmv.epf.network", "6865 thk.net", "6920 tlc.dfa.us", "9372 hci.jia.network",
        "7968 gjf.network", "7292 zbl.ksc.net", "2862 coh.sci.net", "3855 yjt.network",
        "1387 hju.gbq.org", "817 sgp.htq.co", "2406 hkb.ocf.co", "622 wmt.cwn.net", "9172 zfz.net",
        "1523 suq.bhp.co", "9581 gqi.team", "2160 hsj.epf.org", "32 ulz.com", "1225 lmm.ca",
        "1137 ujs.qzi.co", "5041 cdf.hwu.us", "4126 lir.ajl.team", "3111 gdw.team", "8928 arz.org",
        "9531 hoh.co", "7344 czb.com", "2715 ubt.okv.us", "1110 kdd.znq.us", "5729 srp.com",
        "6122 nqk.srp.org", "7193 xth.fzx.ca", "3496 ytn.com", "3950 xuf.network",
        "4521 weh.bfo.us", "3262 mor.ixi.us", "4975 okv.com", "7089 ske.yls.com", "4198 xfs.okv.co",
        "2444 vks.gxz.team", "1789 xcf.zqy.ca", "7392 uyy.net", "3449 tfm.us", "5907 zfz.us",
        "9530 omu.network", "3314 ytd.hkt.net", "9523 wyv.cgl.network", "4439 gtu.us",
        "8390 zqk.network", "1627 bhp.ca", "3609 bhp.team", "8557 uai.lfn.net", "2913 ret.ych.ca",
        "2447 ksc.com", "7476 cze.yvr.net", "8544 xrj.bhp.com", "6129 hkt.com", "8274 ulz.co",
        "9219 tfm.ca", "5016 zwv.gqi.co", "5738 lar.bfo.team", "3377 jkx.network", "2979 bhp.org",
        "8176 ujm.gqs.ca", "84 lmm.network", "3090 ycc.yjt.us", "7042 btv.com", "2965 gxj.org",
        "8263 cwn.org", "1873 kse.gjf.ca"};
    String[] strings = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
    System.out.println(leetcode811.subdomainVisits(str));
    System.out.println(leetcode811.subdomainVisits2(strings));
  }

  public List<String> subdomainVisits(String[] cpdomains) {
    Map<String, Integer> map = new HashMap<>();
    for (String cpdomain : cpdomains) {
      var split = cpdomain.split(" ");
      var count = Integer.valueOf(split[0]);
      var domain = split[1];
      // 统计.出现的次数
      map.put(domain, map.getOrDefault(domain, 0) + count);
      var dotCount = domain.length() - domain.replaceAll("\\.", "").length();
      var fromIndex = 0;
      while (dotCount-- > 0) {
        int i = domain.indexOf('.', fromIndex) + 1;
        fromIndex = i;
        String tmpDomain = domain.substring(i);
        map.put(tmpDomain, map.getOrDefault(tmpDomain, 0) + count);
      }
    }

    return map.entrySet()
        .stream()
        .map(e -> e.getValue() + " " + e.getKey())
        .collect(Collectors.toList());
  }

  /**
   * 使用正则
   * @param cpdomains
   * @return
   */
  public List<String> subdomainVisits2(String[] cpdomains) {
    Map<String, Integer> counts = new HashMap();
    for (String domain : cpdomains) {
      String[] cpinfo = domain.split("\\s+");
      String[] frags = cpinfo[1].split("\\.");
      int count = Integer.parseInt(cpinfo[0]);
      String cur = "";
      for (int i = frags.length - 1; i >= 0; --i) {
        cur = frags[i] + (i < frags.length - 1 ? "." : "") + cur;
        counts.put(cur, counts.getOrDefault(cur, 0) + count);
      }
    }

    return counts.entrySet().stream().map(e -> e.getValue() + " " + e.getKey()).collect(Collectors.toList());
  }
}
