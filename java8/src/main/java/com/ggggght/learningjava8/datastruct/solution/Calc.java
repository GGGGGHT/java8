package com.ggggght.learningjava8.datastruct.solution;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


/**
 * @author ght
 * @Desc 使用栈来解决实际问题
 * 即传入一条计算指令如 7+3-2*5+4/4 直接给出结果
 * 给定一个中缀表达式 将其转为前缀(波兰表达式) 或后缀(逆波兰表达式)
 * 4 * 5 - 8 + 6 + 8 / 2
 * 4 5 * 8 - 6 + 8 2 / +
 * 4 5 * 8 - 6 + 8 2 / +
 * 1 + ((2 +3) * 4) / 5 = 5
 * 314+5/2-28*+-
 * 355/2-28*+-
 * 312-28*+-
 * 3(-1)28*+-
 * 3(-1)16+-
 * 315-
 * -12
 * <p>
 * 314+5/-2-28*+
 * 355/-2-28*+
 * 31-2-28*+
 * 22-28*+
 * 028*+
 * 016+
 * 16
 * 1 2 3 + 4 * 5 / +
 * <p>
 * 3 - (1 + 4) / 5 - 2 + (2 * 8) = 16
 * @date 2020-01-05 2:24 PM
 */

@SuppressWarnings("all")
public class Calc {
    public static void main(String[] args) {
        List<String> list = PolishExpression.toInfixExpression("4*3-4/2+(6/2)-1");
        List<String> polishExpression = PolishExpression.parseSuffixExpression(list);
        PolishExpression.calculate(polishExpression);
        System.out.println();
        System.out.println(PolishExpression.calc(PolishExpression.convert("4*3-4/2+(6/2)-1")));
        System.out.println(PolishExpression.calc("43*42/-62/+1-"));
    }

}

class PolishExpression {

    public static List<String> toInfixExpression(String s) {

        List<String> ls = new ArrayList<>();//存储中序表达式
        int i = 0;
        String str;
        char c;
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;
            } else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48
                        && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                ls.add(str);
            }

        } while (i < s.length());
        return ls;
    }

    /**
     * 转换成逆波兰表达式
     *
     * @param ls
     * @return
     */
    public static List<String> parseSuffixExpression(List<String> ls) {
        Stack<String> s1 = new Stack<String>();
        List<String> lss = new ArrayList<String>();
        for (String ss : ls) {
            if (ss.matches("\\d+")) {
                lss.add(ss);
            } else if (ss.equals("(")) {
                s1.push(ss);
            } else if (ss.equals(")")) {
                // 削除括号
                while (!s1.peek().equals("(")) {
                    lss.add(s1.pop());
                }
                s1.pop();
            } else {
                // 当前运算符的优先级小于或等于栈顶运算符的优先级 则将栈顶的运算符弹出 将当前运算符加入到栈中
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(ss)) {
                    lss.add(s1.pop());
                }
                s1.push(ss);
            }
        }
        while (s1.size() != 0) {
            lss.add(s1.pop());
        }
        return lss;
    }

    /**
     * 通过逆波兰表达式计算结果
     *
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls) {
        Stack<String> s = new Stack<String>();
        for (String str : ls) {
            if (str.matches("\\d+")) {
                s.push(str);
            } else {
                int b = Integer.parseInt(s.pop());
                int a = Integer.parseInt(s.pop());
                int result = 0;
                if (str.equals("+")) {
                    result = a + b;
                } else if (str.equals("-")) {
                    result = a - b;
                } else if (str.equals("*")) {
                    result = a * b;
                } else if (str.equals("\\")) {
                    result = a / b;
                }
                s.push("" + result);
            }
        }
        System.out.println(s.peek());
        return Integer.parseInt(s.pop());
    }


    /**
     * 计算逆波兰表达式
     *
     * @param s
     * @return
     */
    public static int calc(String s) {
        s = s.replaceAll(" ", "");
        Stack<Integer> num = new Stack<>();
        int res;
        for (int i = 0, j = s.length(); i < j; i++) {
            char c;
            if ((c = s.charAt(i)) > 47 && c < 58) {
                num.push(c - 48);
            } else if (c < 48 || c > 57) {
                int num1 = num.pop();
                int num2 = num.pop();
                switch (c) {
                    case '+':
                        res = num1 + num2;
                        num.push(res);
                        continue;
                    case '-':
                        res = num2 - num1;
                        num.push(res);
                        continue;
                    case '*':
                        res = num2 * num1;
                        num.push(res);
                        continue;
                    case '/':
                        res = num2 / num1;
                        num.push(res);
                        continue;
                }
            }
        }
        return num.pop();
    }

    /**
     * 将中缀字符串转换为逆波兰表达式
     */
    @SuppressWarnings("all")
    public static String convert(String s) {
        s = s.replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        Stack<String> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        for (int i = 0, j = s.length(); i < j; i++) {
            char c;
            // 扫描到的是数字
            if ((c = s.charAt(i)) > 47 && c < 58) {
                numStack.push("" + (c - 48));
            } else { // 扫描到的不是数字
                // 1.第一种情况 操作栈为空
                if (opStack.isEmpty()) {
                    opStack.push(c);
                } else if ((c == '+' || c == '-') && (opStack.peek() == '/' || opStack.peek() == '*')) {
                    // 2.操作栈不为空 当前表达式低或平于栈顶的表达式时 先将栈顶元素压入数字栈中
                    while (!opStack.isEmpty() && (opStack.peek() == '/' || opStack.peek() == '*' || opStack.peek() == '+' || opStack.peek() == '-') ) {
                        numStack.push("" + opStack.pop());
                    }
                    opStack.push(c);
                    continue;
                } else if ((c == '*' || c == '/') && (opStack.peek() == '/' || opStack.peek() == '*')) {
                    numStack.push("" + opStack.pop());// 2 2
                    while (!opStack.isEmpty() && (opStack.peek() == '/' || opStack.peek() == '*')) {
                        numStack.push("" + opStack.pop()); // 1 2
                    }
                    opStack.push(c);
                    continue;
                } else if ((c == '+' || c == '-') && (opStack.peek() == '+' || opStack.peek() == '-')) {
                    numStack.push("" + opStack.pop()); // 1 1
                    while (!opStack.isEmpty() && (opStack.peek() == '+' || opStack.peek() == '-')) {
                        numStack.push("" + opStack.pop()); // 1 2
                    }
                    opStack.push(c);
                    continue;
                } else if ((c == '*' || c == '/') && (opStack.peek() == '+' || opStack.peek() == '-')) { // 2 1
                    opStack.push(c);
                    continue;
                } else if (c == '(') {
                    opStack.push(c);
                    continue;
                } else if (c == ')') {
                    while ((c = opStack.pop()) != '(') {
                        numStack.push(c + "");
                    }
                } else if (opStack.peek() == '(') {
                    opStack.push(c);
                    continue;
                }
            }
        }
        while (!opStack.isEmpty()) {
            numStack.push("" + opStack.pop());
        }
        while (!numStack.empty()) {
            sb.append(numStack.pop());
        }
        return sb.reverse().toString();
    }
}


/**
 * 操作符类
 */
class Operation {
    private static int ADDITION = 1;
    private static int SUBTRACTION = 1;
    private static int MULTIPLICATION = 2;
    private static int DIVISION = 2;

    public static int getValue(String operation) {
        int result;
        switch (operation) {
            case "+":
                result = ADDITION;
                break;
            case "-":
                result = SUBTRACTION;
                break;
            case "*":
                result = MULTIPLICATION;
                break;
            case "/":
                result = DIVISION;
                break;
            default:
                //System.out.println("不存在该运算符");
                result = 0;
        }
        return result;
    }
}
