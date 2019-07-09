package fail;

import utils.PrintUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/cat-and-mouse/
 * <p>
 * 两个玩家分别扮演猫（Cat）和老鼠（Mouse）在无向图上进行游戏，他们轮流行动。
 * <p>
 * 该图按下述规则给出：graph[a] 是所有结点 b 的列表，使得 ab 是图的一条边。
 * <p>
 * 老鼠从结点 1 开始并率先出发，猫从结点 2 开始且随后出发，在结点 0 处有一个洞。
 * <p>
 * 在每个玩家的回合中，他们必须沿着与他们所在位置相吻合的图的一条边移动。例如，如果老鼠位于结点 1，那么它只能移动到 graph[1] 中的（任何）结点去。
 * <p>
 * 此外，猫无法移动到洞（结点 0）里。
 * <p>
 * 然后，游戏在出现以下三种情形之一时结束：
 * <p>
 * 如果猫和老鼠占据相同的结点，猫获胜。
 * 如果老鼠躲入洞里，老鼠获胜。
 * 如果某一位置重复出现（即，玩家们的位置和移动顺序都与上一个回合相同），游戏平局。
 * 给定 graph，并假设两个玩家都以最佳状态参与游戏，如果老鼠获胜，则返回 1；如果猫获胜，则返回 2；如果平局，则返回 0。
 * <p>
 * 示例：
 * <p>
 * 输入：[[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
 * 输出：0
 * 解释：
 * 4---3---1
 * |   |
 * 2---5
 *  \ /
 *   0
 *  
 * <p>
 * 提示：
 * <p>
 * 3 <= graph.length <= 200
 * 保证 graph[1] 非空。
 * 保证 graph[2] 包含非零元素
 *
 * @author guya on 2019/6/25
 */
public class Hard_fail_猫和老鼠_图论_博弈论 {

    int N;
    // 防止两个数相加溢出
    int MAX = Integer.MAX_VALUE / 2 - 1;
    int MIN = Integer.MIN_VALUE;


    // S = {start}
    // U = {other}
    // find K have min distance(start, K) in U
    // update distance by K, add to S

    private void dijkstra(int[][] distance, int start) {
        boolean[] visited = new boolean[N];
        visited[start] = true;

        for (int loop = 0; loop < N - 1; loop++) {
            int min = MAX, k = 0;
            for (int i = 0; i < N; i++) {
                if (!visited[i] && distance[start][i] < min) {
                    min = distance[start][i];
                    k = i;
                }
            }

            visited[k] = true;
            for (int i = 0; i < N; i++) {
                if (!visited[i]) {
                    distance[start][i] = Math.min(distance[start][i], min + distance[k][i]);
                }
            }
        }
    }

    public int catMouseGame(int[][] graph) {
        N = graph.length;
        int[][] dd = new int[N][N];
        int[][] dr = new int[N][N];// dd_remove0 去掉零点后的距离图
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    dd[i][j] = 0;
                    dr[i][j] = 0;
                } else {
                    dd[i][j] = MAX;
                    dr[i][j] = MAX;
                }
            }
            for (int j : graph[i]) {
                dd[i][j] = 1;
                if (i != 0 && j != 0) {
                    dr[i][j] = 1;
                }
            }
        }
//        PrintUtils.printArr(distance_remove0);

        // O(N^3)
        for (int i = 0; i < N; i++) {
            // O(N^2)
            dijkstra(dd, i);
            // O(N^2)
            dijkstra(dr, i);
        }

        int m = 1;
        int c = 2;
        for (int n : graph[0]) {
            // 老鼠到终点相邻点的距离比猫近
            if (dr[m][n] < dr[c][n]) {
                return 1;
            }
        }

        int[] place = new int[2];
        place[0] = 1;
        place[1] = 2;
        Set<Integer> set = new HashSet<>();
//        PrintUtils.printArr(dd);
//        PrintUtils.printArr(dr);
//        PrintUtils.printArr(place);


        int res = -1;
        boolean bMouse = true;
        while (res == -1) {
            if (bMouse) {
                res = mouse(dd, dr, graph, set, place);
            } else {
                res = cat(dd, dr, graph, set, place);
            }
            PrintUtils.printArr(place);
            bMouse = !bMouse;
            int v = (place[0] << 8) + place[1];
            set.add(v);
        }

        return res;
    }

    private int test(Set<Integer> set, int[] place) {
        if (place[0] == 0) {
            return 1;
        }

        if (place[0] == place[1]) {
            return 2;
        }

        // 错误点1：运算符优先级
        int v = (place[0] << 8) + place[1];
        if (set.contains(v)) {
            return 0;
        }

        return -1;
    }

    /* 猫
     * 找点：尽量离鼠近（排除零点）；尽量离终点近
     * 必要筛选条件：走到终点的相邻点的距离比鼠不远，即 for each j in Neighbor(0), dr(c,j) <= dr(m, j)
     */

    private int cat(int[][] dd, int[][] dr, int[][] graph, Set<Integer> set, int[] place) {
        int m = place[0];
        int c = place[1];

        int mind = MAX, mind0 = MAX, d, d0;

        int exist = -1;
        for (int i : graph[c]) {
            // cat can't go to place zero
            if (i == 0) {
                continue;
            }

            d = dr[i][m];
            d0 = dd[0][i];

            out:
            if (d < mind || d == mind && d0 < mind0) {
                for (int j : graph[0]) {
                    if (dr[i][j] > dr[m][j]) {
                        break out;
                    }
                }
                place[1] = exist = i;
                if (0 == test(set, place)) {
                    continue;
                }
                mind = d;
                mind0 = d0;
            }
        }
        if (exist == -1) {
            return 1;
        }
        return test(set, place);
    }

    /* 鼠
     * 找点：尽量离终点近；尽量离猫远
     * 必要筛选条件：dd(c, i) > 1
     */

    private int mouse(int[][] dd, int[][] dr, int[][] graph, Set<Integer> set, int[] place) {
        int m = place[0];
        int c = place[1];

        int mind = MAX, mind0 = MAX, d, d0;

        int exist = -1;
        for (int i : graph[m]) {
            d = dr[i][c];
            d0 = dd[0][i];

            if (d0 < mind0 || d0 == mind0 && d < mind) {
                if (d <= 1) {
                    continue;
                }
                place[0] = exist = i;
                if (0 == test(set, place)) {
                    continue;
                }
                mind0 = d0;
                mind = d;
            }
        }
        if (exist == -1) {
            return 2;
        }
        return test(set, place);
    }

    public static void main(String[] args) {
        Hard_fail_猫和老鼠_图论_博弈论 solution = new Hard_fail_猫和老鼠_图论_博弈论();
        int[][] graph;


        /* res = 0
         *
         * 4---3---1
         * |   |
         * 2---5
         *  \ /
         *   0
         */
        graph = new int[][]{{2, 5}, {3}, {0, 4, 5}, {1, 4, 5}, {2, 3}, {0, 2, 3}};
        System.out.println(0 == solution.catMouseGame(graph));

        /* res = 1
         *
         * 2---4---3---1---5
         *         |
         *         0
         */
        graph = new int[][]{{3}, {3, 5}, {4}, {0, 1, 4}, {2, 3}, {1}};
        System.out.println(1 == solution.catMouseGame(graph));

        /* res = 1
         *
         * 4---1
         * |   |
         * 5   3---2
         * |   |
         * 6---0
         */
        graph = new int[][]{{3, 6}, {3, 4}, {3}, {0, 2, 1}, {1, 5}, {4, 6}, {0, 5}};
        System.out.println(1 == solution.catMouseGame(graph));

        /* res = 2
         *
         * 5---1
         *  \ /
         *   4
         *   |
         *   3---2
         *   |
         *   0
         */
        graph = new int[][]{{2, 3}, {4, 5}, {3}, {0, 2, 4}, {1, 3, 5}, {1, 4}};
        System.out.println(2 == solution.catMouseGame(graph));

        /* res = 0
         *
         * 1---2
         * |   | \
         * 4---3 11
         * |   |  |
         * 6---5  |
         * |   |  |
         * 8---7  0
         * |   | /
         * 10--9
         */
        graph = new int[][]{{9, 11}, {2, 4}, {1, 3, 11}, {2, 4, 5}, {1, 3, 6}, {3, 6, 7}, {4, 5, 8}, {5, 8, 9}, {6, 7, 10}, {0, 7, 10}, {8, 9}, {0, 2}};
        System.out.println(0 == solution.catMouseGame(graph));

        /* res = 2
         *
         *     1
         *     |
         *     3
         *     |
         *     4---2
         *   / | \ |
         * 5   6   7
         * |   |   |
         * 8---0---9
         */
        graph = new int[][]{{6, 8, 9}, {3}, {4, 7}, {1, 4}, {2, 3, 5, 6, 7}, {4, 8}, {0, 4}, {2, 4, 9}, {0, 5}, {0, 7}};
        System.out.println(2 == solution.catMouseGame(graph));


        /* res = 1
         *
         * 3---1
         * |   |
         * 4   7
         * | \ |
         * 5   8---9---2
         * |   |
         * 6---0
         */
        graph = new int[][]{{6, 8}, {3, 7}, {9}, {1, 4}, {3, 5, 8}, {4, 6}, {0, 5}, {1, 8}, {0, 4, 7, 9}, {2, 8}};
        System.out.println(1 == solution.catMouseGame(graph));

        /* res = 1 开始时猫可以在每条可能的路上截住老鼠，但不能全部都截住
         *
         *     1
         *     |
         *     3
         *     |
         *     4
         *   /   \
         * 5---0---7
         * |       |
         * 6---2---8
         */
        graph = new int[][]{{5,7},{3},{6,8},{1,4},{3,5,7},{0,4,5},{2,5},{0,4,8},{2,7}};
        System.out.println(1 == solution.catMouseGame(graph));


        // 预期1，却输出了0，老鼠不够聪明：走的时候没有选最优点
        graph = new int[][]{{3, 4, 6, 7, 9, 15, 16, 18}, {4, 5, 8, 19}, {3, 4, 6, 9, 17, 18}, {0, 2, 11, 15}, {0, 1, 10, 6, 2, 12, 14, 16}, {1, 10, 7, 9, 15, 17, 18}, {0, 10, 4, 7, 9, 2, 11, 12, 13, 14, 15, 17, 19}, {0, 10, 5, 6, 9, 16, 17}, {1, 9, 14, 15, 16, 19}, {0, 10, 5, 6, 7, 8, 2, 11, 13, 15, 16, 17, 18}, {4, 5, 6, 7, 9, 18}, {3, 6, 9, 12, 19}, {4, 6, 11, 15, 17, 19}, {6, 9, 15, 17, 18, 19}, {4, 6, 8, 15, 19}, {0, 3, 5, 6, 8, 9, 12, 13, 14, 16, 19}, {0, 4, 7, 8, 9, 15, 17, 18, 19}, {5, 6, 7, 9, 2, 12, 13, 16}, {0, 10, 5, 9, 2, 13, 16}, {1, 6, 8, 11, 12, 13, 14, 15, 16}};
//        PrintUtils.printArr(graph);
        System.out.println(1 == solution.catMouseGame(graph));
    }
}
