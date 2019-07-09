package solved;

/**
 * @author guya on 2019/6/25
 */
public class Hard_hard_猫和老鼠_图论_博弈论 {

    private int[][][] dp;
    private int N;

    public int catMouseGame(int[][] graph) {
        N = graph.length;

        // dp[t][x][y]: time t, mouse x, cat y
        dp = new int[2*N][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return solve(graph, 0, 1, 2);
    }

    /*
      这个递归的难点在于如何倒着思考，相信结果是正确的
     */

    private int solve(int[][] graph, int t, int x, int y) {
//        PrintUtils.printArr(new int[]{t,x,y});

        if (t >= 2 * N) {
            return dp[t][x][y] = 0;
        }
        if (x == 0) {
            return dp[t][x][y] = 1;
        }
        if (x == y) {
            return dp[t][x][y] = 2;
        }
        if (dp[t][x][y] != -1) {
            return dp[t][x][y];
        }

        boolean flag = false;
        if ((t & 1) == 0) {
            // mouse
            for (int i : graph[x]) {
                int res = solve(graph, t+1, i, y);
                if (res == 1) {
                    return dp[t][x][y] = 1;
                }
                if (res != 2) {
                    flag = true;
                }
            }
            if (flag) {
                return dp[t][x][y] = 0;
            } else {
                return dp[t][x][y] = 2;
            }
        } else {
            // cat
            for (int i : graph[y]) {
                // cat can't go to position 0
                if (i == 0) {
                    continue;
                }

                int res = solve(graph, t+1, x, i);
                if (res == 2) {
                    return dp[t][x][y] = 2;
                }
                if (res != 1) {
                    flag = true;
                }
            }
            if (flag) {
                return dp[t][x][y] = 0;
            } else {
                return dp[t][x][y] = 1;
            }
        }
    }


    public static void main(String[] args) {
        Hard_hard_猫和老鼠_图论_博弈论 solution = new Hard_hard_猫和老鼠_图论_博弈论();
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


//         预期1，却输出了0，老鼠不够聪明：走的时候没有选最优点
        graph = new int[][]{{3, 4, 6, 7, 9, 15, 16, 18}, {4, 5, 8, 19}, {3, 4, 6, 9, 17, 18}, {0, 2, 11, 15}, {0, 1, 10, 6, 2, 12, 14, 16}, {1, 10, 7, 9, 15, 17, 18}, {0, 10, 4, 7, 9, 2, 11, 12, 13, 14, 15, 17, 19}, {0, 10, 5, 6, 9, 16, 17}, {1, 9, 14, 15, 16, 19}, {0, 10, 5, 6, 7, 8, 2, 11, 13, 15, 16, 17, 18}, {4, 5, 6, 7, 9, 18}, {3, 6, 9, 12, 19}, {4, 6, 11, 15, 17, 19}, {6, 9, 15, 17, 18, 19}, {4, 6, 8, 15, 19}, {0, 3, 5, 6, 8, 9, 12, 13, 14, 16, 19}, {0, 4, 7, 8, 9, 15, 17, 18, 19}, {5, 6, 7, 9, 2, 12, 13, 16}, {0, 10, 5, 9, 2, 13, 16}, {1, 6, 8, 11, 12, 13, 14, 15, 16}};
//        PrintUtils.printArr(graph);
        System.out.println(1 == solution.catMouseGame(graph));
    }
}
