package solved;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/** https://leetcode-cn.com/problems/shortest-bridge
 *
 * 在给定的二维二进制数组 A 中，存在两座岛。（岛是由四面相连的 1 形成的一个最大组。）
 *
 * 现在，我们可以将 0 变为 1，以使两座岛连接起来，变成一座岛。
 *
 * 返回必须翻转的 0 的最小数目。（可以保证答案至少是 1。）
 *
 * 示例 1：
 * 输入：[[0,1],[1,0]]
 * 输出：1
 *
 * 示例 2：
 * 输入：[[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 *
 * 示例 3：
 * 输入：[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 或 A[i][j] == 1
 *
 * @author guya on 2019/6/25
 */
public class Medium_medium_最短的桥_广度优先 {

    private PriorityQueue<Integer> queue = new PriorityQueue<>();
    private int N;
    private int[][] ds = {{1,0},{0,1},{-1,0},{0,-1}};
    private Set<Integer> block1 = new HashSet<>();
    private Set<Integer> block2 = new HashSet<>();

    public int shortestBridge(int[][] A) {
        N = A.length;

        Set<Integer> block = block1;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!contains(block1, x, y) && !contains(block2, x, y) && A[x][y] == 1) {
                    add(block, x, y);
                    while (!queue.isEmpty()) {
                        int r = queue.poll();
                        int xx = r >> 8;
                        int yy = r & 127;
                        for (int[] d : ds) {
                            int tx = xx+d[0];
                            int ty = yy+d[1];
                            if (tx < 0 || tx >= N || ty < 0 || ty >= N) {
                                continue;
                            }
                            if (!contains(block1, tx, ty) && !contains(block2, tx, ty) && A[tx][ty] == 1) {
                                add(block, tx, ty);
                            }
                        }
                    }
                    block = block2;
                }
            }
        }

        // 这里太暴力了，更好的是，找到一个岛屿之后，广度优先进行周围感染
        int min = Integer.MAX_VALUE;
        for (Integer i1 : block1) {
            for (Integer i2 : block2) {
                int distance = Math.abs((i1>>8)-(i2>>8)) + Math.abs((i1&127)-(i2&127));
                min = distance < min ? distance : min;
            }
        }

        return min - 1;
    }

    // 放到一个函数，方便加print调试
    void add(Set<Integer> block, int x, int y) {
        int r = (x<<8) + y;
        queue.add(r);
        block.add(r);
//        PrintUtils.printArr(new int[]{x,y});
    }
    boolean contains(Set<Integer> block, int x, int y) {
        return block.contains((x<<8) + y);
    }

    public static void main(String[] args) {
        Medium_medium_最短的桥_广度优先 solution = new Medium_medium_最短的桥_广度优先();
        int [][] a;
        a = new int[][]{{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
        System.out.println(solution.shortestBridge(a));
        a = new int[][]{{0,1,0},{0,0,0},{0,0,1}};
        System.out.println(solution.shortestBridge(a));
    }
}








