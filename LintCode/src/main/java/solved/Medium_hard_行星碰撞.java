package solved;

import lombok.extern.slf4j.Slf4j;
import utils.PrintUtils;

/**
 * 给定一个整数数组 asteroids，表示在同一行的行星。
 *
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 *
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/asteroid-collision
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author guya on 2019/6/25
 */
@Slf4j
class Medium_hard_行星碰撞 {

    public int[] asteroidCollision(int[] asteroids) {
        int[] r1 = new int[asteroids.length + 1];
        int[] r2 = new int[asteroids.length + 1];
        int p1 = 0;
        int p2 = 0;
        for (int num : asteroids) {
            if (num > 0) {
                r1[++p1] = num;
            }
            if (num < 0) {
                while (p1 != 0 && r1[p1] + num < 0) {
                    r1[p1--] = 0;
                }
                if (p1 == 0 || r1[p1] + num < 0) {
                    r2[++p2] = num;
                }
                if (r1[p1] + num == 0) {
                    r1[p1--] = 0;
                }
            }
        }
//        log.info("r1 {}", PrintUtils.toString(r1));
//        log.info("r2 {}", PrintUtils.toString(r2));
        int[] res = new int[p1 + p2];
        int p3 = 0;
        for (int i = 1; i <= p2; i++) {
            res[p3++] = r2[i];
        }
        for (int i = 1; i <= p1; i++) {
            res[p3++] = r1[i];
        }
        return res;
    }

    public static void main(String[] args) {
//        int[] asteroids = {-1, 3, 11, -9, -8, 1, -12, 5, 4, 3, -2, -3, 1};
        int[] asteroids = {15, 10, -5};
        int[] res = new Medium_hard_行星碰撞().asteroidCollision(asteroids);
        PrintUtils.printArr(asteroids);
        PrintUtils.printArr(res);
    }
}
