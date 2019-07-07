package solved;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * @author guya on 2019/6/25
 */
public class Hard_medium_最长有效括号子串 {

    /**
     * 提前发现错误1：'('也会致错： ()(()
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        boolean[] arr = new boolean[s.length()];

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case '(':
                    stack.push(i);
                    break;
                case ')':
                    if (!stack.empty()) {
                        int left = stack.pop();
                        arr[left] = true;
                        arr[i] = true;
                    }
                    break;
                default:
            }
        }

        // 这个解法不太优雅，进行了两次遍历，不过空间占用少。。。。
        int maxLen = 0;
        int curLen = 0;
        for (boolean b : arr) {
            if (b) {
                curLen++;
            } else {
                maxLen = maxLen > curLen ? maxLen : curLen;
                curLen = 0;
            }
        }
        maxLen = maxLen > curLen ? maxLen : curLen;
        return maxLen;
    }

    public static void main(String[] args) {
        Hard_medium_最长有效括号子串 solution = new Hard_medium_最长有效括号子串();
        System.out.println(solution.longestValidParentheses(")()())"));
        System.out.println(solution.longestValidParentheses("()(()()"));
        System.out.println(solution.longestValidParentheses("()())()"));
        System.out.println(solution.longestValidParentheses("(((()())(((()))()"));
    }
}
