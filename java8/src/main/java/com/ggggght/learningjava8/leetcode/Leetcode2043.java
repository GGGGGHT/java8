package com.ggggght.learningjava8.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 <p>你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。银行共有 <code>n</code> 个账户，编号从 <code>1</code> 到 <code>n</code> 。每个账号的初始余额存储在一个下标从 <strong>0</strong> 开始的整数数组 <code>balance</code>&nbsp;中，其中第 <code>(i + 1)</code> 个账户的初始余额是 <code>balance[i]</code> 。</p>

 <p>请你执行所有 <strong>有效的</strong> 交易。如果满足下面全部条件，则交易 <strong>有效</strong> ：</p>

 <ul>
 <li>指定的账户数量在 <code>1</code> 和 <code>n</code> 之间，且</li>
 <li>取款或者转账需要的钱的总数 <strong>小于或者等于</strong> 账户余额。</li>
 </ul>

 <p>实现 <code>Bank</code> 类：</p>

 <ul>
 <li><code>Bank(long[] balance)</code> 使用下标从 <strong>0</strong> 开始的整数数组 <code>balance</code> 初始化该对象。</li>
 <li><code>boolean transfer(int account1, int account2, long money)</code> 从编号为&nbsp;<code>account1</code> 的账户向编号为 <code>account2</code> 的账户转帐 <code>money</code> 美元。如果交易成功，返回 <code>true</code> ，否则，返回 <code>false</code> 。</li>
 <li><code>boolean deposit(int account, long money)</code> 向编号为&nbsp;<code>account</code> 的账户存款 <code>money</code> 美元。如果交易成功，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
 <li><code>boolean withdraw(int account, long money)</code> 从编号为 <code>account</code> 的账户取款 <code>money</code> 美元。如果交易成功，返回 <code>true</code> ；否则，返回 <code>false</code> 。</li>
 </ul>

 <p>&nbsp;</p>

 <p><strong>示例：</strong></p>

 <pre>
 <strong>输入</strong>：
 ["Bank", "withdraw", "transfer", "deposit", "transfer", "withdraw"]
 [[[10, 100, 20, 50, 30]], [3, 10], [5, 1, 20], [5, 20], [3, 4, 15], [10, 50]]
 <strong>输出：</strong>
 [null, true, true, true, false, false]

 <strong>解释：</strong>
 Bank bank = new Bank([10, 100, 20, 50, 30]);
 bank.withdraw(3, 10);    // 返回 true ，账户 3 的余额是 $20 ，所以可以取款 $10 。
 // 账户 3 余额为 $20 - $10 = $10 。
 bank.transfer(5, 1, 20); // 返回 true ，账户 5 的余额是 $30 ，所以可以转账 $20 。
 // 账户 5 的余额为 $30 - $20 = $10 ，账户 1 的余额为 $10 + $20 = $30 。
 bank.deposit(5, 20);     // 返回 true ，可以向账户 5 存款 $20 。
 // 账户 5 的余额为 $10 + $20 = $30 。
 bank.transfer(3, 4, 15); // 返回 false ，账户 3 的当前余额是 $10 。
 // 所以无法转账 $15 。
 bank.withdraw(10, 50);   // 返回 false ，交易无效，因为账户 10 并不存在。
 </pre>

 <p>&nbsp;</p>

 <p><strong>提示：</strong></p>

 <ul>
 <li><code>n == balance.length</code></li>
 <li><code>1 &lt;= n, account, account1, account2 &lt;= 10<sup>5</sup></code></li>
 <li><code>0 &lt;= balance[i], money &lt;= 10<sup>12</sup></code></li>
 <li><code>transfer</code>, <code>deposit</code>, <code>withdraw</code> 三个函数，<strong>每个</strong> 最多调用 <code>10<sup>4</sup></code> 次</li>
 </ul>
 <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>模拟</li></div></div><br><div><li>👍 39</li><li>👎 0</li></div>
 */
public class Leetcode2043 {

}
class Bank {

  Map<Integer,Long> balances;
  public Bank(long[] balance) {
    balances = new HashMap<>();
    for(int i=0,j=balance.length;i<j;i++){
      balances.put(i+1,balance[i]);
    }
  }

  public boolean transfer(int account1, int account2, long money) {
    if(!balances.containsKey(account1) || !balances.containsKey(account2) || balances.get(account1) < money) return false;
    balances.put(account1,balances.get(account1) - money);
    balances.put(account2,balances.get(account2) + money);
    return true;
  }

  public boolean deposit(int account, long money) {
    if(!balances.containsKey(account)) {
      return false;
    }

    balances.put(account,balances.get(account)+money);
    return true;
  }

  public boolean withdraw(int account, long money) {
    if(!balances.containsKey(account) || balances.get(account) < money) {
      return false;
    }

    balances.put(account,balances.get(account)-money);
    return true;
  }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */