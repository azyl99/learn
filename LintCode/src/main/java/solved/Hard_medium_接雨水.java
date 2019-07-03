package solved;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author guya on 2019/6/25
 */
public class Hard_medium_接雨水 {


    /**
     * 失败
     *
     * @param height
     * @return
     */
//    public int trap(int[] height) {
//
//        int[] pole = new int[height.length];
//        int p = 0;
//
//        int da, db = 0, i;
//        int ha = 0, hb = 0, hc, maxh = 0;
//        for (i = 0; i < height.length; i++) {
//            da = db;
//            // 错误点1：最后一根柱子要兼容
//            db = i == height.length - 1 ? 0 : height[i] - height[i + 1];
//
//            if (da < 0 && db >= 0 || da <= 0 && db > 0) {
//                hc = height[i];
//
//                // 错误点3：处于中间的极大值无效 [5, 2, 1, 2, 1, 5]
//                // 错误点4：处于中间的所有极大值都无效 [8,1,6,2,5,3,9]
//                if (p >= 2 && ha >= hb && hb <= hc) {
//                    pole[p - 1] = i;
//                } else {
//                    ha = hb;
//                    pole[p++] = i;
//                }
//
//                hb = hc;
//            }
//        }
//
//        int sum = 0;
//        for (i = 0; i < p; i++) {
//            int left = pole[i];
//            int right = pole[i + 1];
//
//            int hl = height[left];
//            int hr = height[right];
//
//            int min = hl < hr ? hl : hr;
//            for (int j = left + 1; j < right; j++) {
//                int diff = min - height[j];
//                // 错误点2：[5,4,1,2]  次高点太高
//                sum += diff > 0 ? diff : 0;
//            }
//        }
//
//        return sum;
//    }
    public int trap(int[] height) {
        return trap(height, 0, height.length - 1);
    }

    private int trap(int[] height, int l, int r) {
        if (l + 1 >= r) {
            return 0;
        }

        // find max
        int mh = 0, mi = 0, sum = 0;
        int hl = height[l], hr = height[r];
        int min = hl < hr ? hl : hr;

        for (int i = l + 1; i < r; i++) {
            int h = height[i];
            sum += min - h;
            if (mh < h) {
                mh = h;
                mi = i;
            }
        }

        if (mh <= min) {
            return sum;
        }

        // recursion
        return trap(height, l, mi) + trap(height, mi, r);
    }

    public static void main(String[] args) {
        Hard_medium_接雨水 solution = new Hard_medium_接雨水();
//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        int[] height = {5, 2, 1, 2, 1, 5};
//        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
//        int[] height = {8, 8, 1, 5, 6, 2, 5, 3, 3, 9};
        int[] height = {8, 5, 4, 1, 8, 9, 3, 0, 0};
        System.out.println(solution.trap(height));
    }
}
