package solved;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * @author guya on 2019/6/25
 */
public class Hard_medium_简单正则表达式 {
    static final Character NONE = '~';
    static final Character ALL = '.';
    static final Character STAR = '*';

    static class State {

        /**
         * 状态机转换 map目前最多存两个项：具体字符（包括通配字符*）、NONE（用于连续两个星号的场景a*b*）
         *   以 "ac*.*c" 为例
         *              c          .
         *            ----       ----
         *        a  \    /  ~  \    /  c
         *  1 -------> 2 -------> 3 -------> 4
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
        Character ch, ahead;
        for (int i = 0; i < chars.length; i++) {

            ch = chars[i];
            if (i + 1 < chars.length) {
                ahead = chars[i + 1];
            } else {
                ahead = null;
            }

            if (STAR.equals(ahead)) {
                current.setTransfer(ch, current);
                current.setTransfer(NONE, temp);
                i++;
            } else {
                current.setTransfer(ch, temp);
            }

            current = temp;
            temp = new State();
        }
        return head;
    }


    public static void main(String[] args) {
        String s, p;
        s = "ab";
        p = ".*c";
        System.out.println((new Hard_medium_简单正则表达式()).isMatch(s, p));
        s = "accbbc";
        p = "ac*b*c";
        System.out.println((new Hard_medium_简单正则表达式()).isMatch(s, p));
        s = "acbc";
        p = "a.*c";
        System.out.println((new Hard_medium_简单正则表达式()).isMatch(s, p));
        s = "aab";
        p = "c*a*b";
        System.out.println((new Hard_medium_简单正则表达式()).isMatch(s, p));
        s = "mississippi";
        p = "mis*is*p*.";
        System.out.println((new Hard_medium_简单正则表达式()).isMatch(s, p));
        s = "mississippi";
        p = "mis*is*ip*.";
        System.out.println((new Hard_medium_简单正则表达式()).isMatch(s, p));
    }
}
