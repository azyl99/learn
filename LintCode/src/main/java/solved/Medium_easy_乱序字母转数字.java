package solved;

/**
 * https://leetcode-cn.com/problems/reconstruct-original-digits-from-english/
 *
 * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
 *
 * 注意:
 *
 * 输入只包含小写英文字母。
 * 输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
 * 输入字符串的长度小于 50,000。
 * 示例 1:
 *
 * 输入: "owoztneoer"
 *
 * 输出: "012" (zeroonetwo)
 * 示例 2:
 *
 * 输入: "fviefuro"
 *
 * 输出: "45" (fourfive)
 *
 * @author guya on 2019/6/25
 */
public class Medium_easy_乱序字母转数字 {
    String[] sa = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    int[] na = {0, 8, 6, 4, 3, 5, 7, 9, 1, 2};
    // 可以代表数字唯一性的字符
    char[] chs = {'z', 'g', 'x', 'u', 'h', 'f', 'v', 'i', 'e', 't'};

    public String originalDigits(String s) {
        int[] charCounts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCounts[s.charAt(i) - 'a']++;
        }

        // 按顺序遍历所有数字
        StringBuilder sb = new StringBuilder();
        int[] numCounts = new int[10];
        for (int i = 0; i < na.length; i++) {
            int n = na[i];
            String ns = sa[n];
            int cnt = charCounts[chs[i] - 'a'];
            numCounts[n] = cnt;
            for (int j = 0; j < ns.length(); j++) {
                char ch = ns.charAt(j);
                charCounts[ch - 'a'] -= cnt;
            }
        }

        // 数字数组转字符串
        for (int i = 0; i < numCounts.length; i++) {
            for (int j = 0; j < numCounts[i]; j++) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Medium_easy_乱序字母转数字 solution = new Medium_easy_乱序字母转数字();
        System.out.println(solution.originalDigits("owoztneoer"));
        System.out.println(solution.originalDigits("fviefuro"));
    }
}
