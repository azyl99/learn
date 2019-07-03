package solved;

import utils.PrintUtils;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water-ii/
 * <p>
 * 给定一个 m x n 的矩阵，其中的值均为正整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * <p>
 * 说明:
 * m 和 n 都是小于110的整数。每一个单位的高度都大于0 且小于 20000。
 * <p>
 * 示例：
 * <p>
 * 给出如下 3x6 的高度图:
 * [
 * [1,4,3,1,3,2],
 * [3,2,1,3,2,4],
 * [2,3,3,2,3,1]
 * ]
 * <p>
 * 返回 4。
 *
 * @author guya on 2019/6/25
 */
public class Hard_hard_接雨水II {

    int m, n;
    int[][] directs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    boolean[][] visit;
    int[][] heightMap;

    static class Point {
        int x, y, h;

        public Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    ", h=" + h +
                    '}';
        }
    }

    /**
     * 思路：最外圈退水
     * - 最外圈加入优先队列
     * - 每次取最低点，广度优先遍历退水
     * - 最低点的四周加入队列，外圈逐渐内缩
     *
     * @param heightMap
     * @return
     */
    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length <= 2 || heightMap[0].length <= 2) {
            return 0;
        }
        m = heightMap.length;
        n = heightMap[0].length;
        this.visit = new boolean[m][n];
        this.heightMap = heightMap;

        PriorityQueue<Point> pq = new PriorityQueue<>(m * n, (o1, o2) -> o1.h - o2.h);
        for (int i = 0; i < m; i++) {
            addPoint(pq, i, 0);
            addPoint(pq, i, n - 1);
        }
        for (int j = 1; j < n - 1; j++) {
            addPoint(pq, 0, j);
            addPoint(pq, m - 1, j);
        }

        int sum = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            for (int[] d : directs) {
                int tx = p.x + d[0];
                int ty = p.y + d[1];
                if (checkPoint(tx, ty)) {
                    int diff = p.h - heightMap[tx][ty];
                    if (diff > 0) {
                        // 因为p是外圈最低的点，如果diff是正数，水就能蓄住
                        sum += diff;
                        // 这一格的高度就是蓄水的高度
                        heightMap[tx][ty] = p.h;
                    }
                    // 最低的
                    addPoint(pq, tx, ty);
                }

            }
        }

        return sum;
    }

    private boolean checkPoint(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n && !visit[x][y];
    }

    private void addPoint(Queue<Point> q, int x, int y) {
        visit[x][y] = true;
        q.add(new Point(x, y, heightMap[x][y]));
    }

    private void printQ(PriorityQueue<Point> q) {
        PriorityQueue<Point> nq = new PriorityQueue<>(q);
        while (!nq.isEmpty()) {
            Point p = nq.poll();
            System.out.print(p.h + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Hard_hard_接雨水II solution = new Hard_hard_接雨水II();
//        int[][] heightMap = {
//                {1, 4, 3, 1, 3, 2},
//                {3, 2, 1, 3, 2, 4},
//                {2, 3, 3, 2, 3, 1}
//        };
        int[][] heightMap = {
                {1, 1, 1, 2, 6, 4, 4, 1},
                {1, 3, 5, 1, 6, 2, 1, 4},
                {1, 6, 6, 6, 0, 6, 6, 6},
                {1, 6, 1, 1, 1, 1, 1, 6},
                {7, 6, 1, 3, 2, 3, 1, 6},
                {1, 5, 1, 3, 1, 3, 1, 6},
                {6, 2, 1, 3, 3, 3, 1, 6},
                {1, 6, 1, 1, 1, 1, 1, 6},
                {1, 6, 1, 1, 4, 1, 1, 6},
                {1, 6, 6, 6, 3, 6, 6, 6},
                {1, 6, 1, 1, 1, 1, 1, 6},
        };
        PrintUtils.printArr(heightMap);
        int res = solution.trapRainWater(heightMap);
        PrintUtils.printArr(heightMap);
        System.out.println(res);
    }
}
