package a_Id冲突;

import lombok.extern.slf4j.Slf4j;

/**
 * @author guya on 2019/2/27
 */
@Slf4j
public class ShowConflict {

    public static final int M = 18;
    public static final int N = 8;

    public static int[] result = new int[N + 1];
    public static int[] conflicts = new int[N + 1];
    public static int[] numberCounts = new int[M + 1];
    public static int[] numberArr = new int[N + 1];

    public static void enter(int pos, int num) {
        // 以前已有该num
        if (numberCounts[num] != 0) {
            conflicts[pos] = conflicts[pos - 1] + 1;
        } else {
            conflicts[pos] = conflicts[pos - 1];
        }

        numberCounts[num]++;
        // 实际的数组内容
        numberArr[pos] = num;
        log.debug("entered [{},{}] {}", pos, num, currentInfo());
    }

    public static void save(int pos, int num) {
        log.debug("to save [{},{}] conflict {}", pos, num, conflicts[pos]);
        // 冲突conflicts[pos]次的计数器加一
        result[conflicts[pos]]++;
        leave(pos, num);
    }

    public static void leave(int pos, int num) {
        numberCounts[num]--;
        conflicts[pos] = 0;
        numberArr[pos] = 0;
        log.debug("leaved [{},{}] {}", pos, num, currentInfo());
    }

    public static String currentInfo() {
        if(log.isDebugEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("numbers [");
            for (int i = 1; i < numberArr.length; i++) {
                sb.append(numberArr[i]).append(" ");
            }
            sb.append("], counts [");
            for (int i = 1; i < numberCounts.length; i++) {
                sb.append(numberCounts[i]).append(" ");
            }
            sb.append("], conflicts [");
            for (int i = 1; i < conflicts.length; i++) {
                sb.append(conflicts[i]).append(" ");
            }
            sb.append("]");
            return sb.toString();
        }
        return "";
    }

    /**
     * @param pos
     * @param num
     */
    public static void f(int pos, int num) {
        enter(pos, num);
        if (pos == N) {
            // 叶子节点
            save(pos, num);
        } else {
            // 非叶子节点
            g(pos);
            leave(pos, num);
        }
    }

    /**
     * 遍历节点的儿子们
     *
     * @param pos
     */
    public static void g(int pos) {
        for (int num = 1; num <= M; num++) {
            f(pos + 1, num);
        }
    }

    public static void main(String[] args) {
        g(0);

        int s = 0;
        for (int i = 0; i < N; i++) {
            s += result[i] * i;
            System.out.println(i + " " + result[i] + " ");
        }
        System.out.println();
        System.out.println(s / Math.pow(M, N));
    }
}
