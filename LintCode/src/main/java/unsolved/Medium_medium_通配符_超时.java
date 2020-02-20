package unsolved;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/wildcard-matching/
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 *
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 *
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 *
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 *
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 *
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/wildcard-matching
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author guya on 2019/6/25
 */
public class Medium_medium_通配符_超时 {
    static final Character NONE = '~';
    static final Character ALL = '.';
    static final Character STAR = '*';
    static final Character QUES = '?';

    static class State {

        /**
         * 状态机转换 map目前最多存两个项：具体字符（包括通配字符*）、NONE（用于连续两个星号的场景a*b*）
         *   以 "a*c?e" 为例
         *              .
         *            ----
         *       a   \    /  ~        c         .
         *  1 -------> 2 ------->2 ------> 3 ------> 4
         */
        private Map<Character, State> transfer = new HashMap<>();

        void setTransfer(Character ch, State state) {
            transfer.put(ch, state);
        }

        boolean eat(String s, int index) {

            State state;

            if (index == s.length()) {
                state = this;
                while (state.transfer.size() != 0) {
                    state = state.transfer.get(NONE);
                    if (state == null) {
                        // pattern 最后还有非NONE的没有消化
                        return false;
                    }
                }
                return true;
            }

            Character ch = s.charAt(index);

            state = transfer.get(ch);
            if (state != null && state.eat(s, index + 1)) {
                return true;
            }

            state = transfer.get(ALL);
            if (state != null && state.eat(s, index + 1)) {
                return true;
            }

            // 不消化字符，仅做状态转换
            state = transfer.get(NONE);
            if (state != null && state.eat(s, index)) {
                return true;
            }

            return false;
        }
    }

    public boolean isMatch(String s, String p) {
        State state = build(p.toCharArray());
        return state.eat(s, 0);
    }

    private State build(char[] chars) {
        State head = new State(), temp = new State(), current = head;
        char ch, last = NONE;
        for (int i = 0; i < chars.length; i++) {

            ch = chars[i];
            if (ch == last && ch == STAR) {
                continue;
            }

            if (STAR.equals(ch)) {
                current.setTransfer(ALL, current);
                current.setTransfer(NONE, temp);
            } if (QUES.equals(ch)) {
                current.setTransfer(ALL, temp);
            } else {
                current.setTransfer(ch, temp);
            }

            current = temp;
            temp = new State();
        }
        return head;
    }

    public static void main(String[] args) {
        Medium_medium_通配符_超时 solution = new Medium_medium_通配符_超时();
        System.out.println(solution.isMatch("adceb", "a*c?b"));
        System.out.println(solution.isMatch("adceb", "a*c*b"));
        System.out.println(solution.isMatch("adccb", "a*d*b"));
        System.out.println(solution.isMatch("acdcb", "a*c?b"));
    }
}
