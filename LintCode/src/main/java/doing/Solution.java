package doing;

/**
 * https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/
 * <p>
 * 返回 A 的最短的非空连续子数组的长度，该子数组的和至少为 K 。
 * <p>
 * 如果没有和至少为 K 的非空子数组，返回 -1 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1], K = 1
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：A = [1,2], K = 4
 * 输出：-1
 * 示例 3：
 * <p>
 * 输入：A = [2,-1,2], K = 3
 * 输出：3
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 50000
 * -10 ^ 5 <= A[i] <= 10 ^ 5
 * 1 <= K <= 10 ^ 9
 *
 * @author guya on 2019/6/25
 */
public class Solution {

    /**
     * 太慢了：超出时间限制
     *
     * @param A
     * @param K
     * @return
     */
    public int shortestSubarray(int[] A, int K) {
        int minLen = -1;
        int[] S = new int[A.length + 1];
        S[0] = 0;
        for (int i = 0; i < A.length; i++) {
            S[i + 1] = S[i] + A[i];
            for (int j = minLen == -1 ? 0 : i - minLen + 2; j <= i; j++) {
                int diff = S[i + 1] - S[j];
                if (diff >= K) {
                    minLen = i + 1 - j;
                    if (minLen == 1) {
                        return 1;
                    }
                }
            }
        }

        return minLen;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shortestSubarray(new int[]{2, -1, 2, -1, 2}, 3));
    }
}
