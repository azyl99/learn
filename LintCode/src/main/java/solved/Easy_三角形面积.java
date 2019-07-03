package solved;

/**
 * https://leetcode-cn.com/problems/largest-triangle-area/
 *
 * @author guya on 2019/6/25
 */
public class Easy_三角形面积 {

    public double largestTriangleArea(int[][] points) {
        double res = 0;
        for (int i = 0; i < points.length; i++) {
            int[] p1 = points[i];
            for (int j = i + 1; j < points.length; j++) {
                int[] p2 = points[j];
                for (int k = j + 1; k < points.length; k++) {
                    int[] p3 = points[k];
                    double s = calArea(p1, p2, p3);
                    if (res < s) {
                        res = s;
                    }
                }
            }
        }
        return res;
    }

    private double calArea(int[] p1, int[] p2, int[] p3) {
        double res = p1[0] * p2[1] + p2[0] * p3[1] + p3[0] * p1[1]
                - p1[1] * p2[0] - p2[1] * p3[0] - p3[1] * p1[0];
        return Math.abs(res / 2);
    }

    public static void main(String[] args) {
        int[][] points = {{0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}};
        double res = (new Easy_三角形面积()).largestTriangleArea(points);
        System.out.println(res);
    }
}
