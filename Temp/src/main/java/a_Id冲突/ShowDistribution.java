package a_Id冲突;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author guya on 2019/2/27
 */
@Slf4j
public class ShowDistribution {

    static class Group {
        int f1;
        int f2;
        int f3;

        public Group(int f1, int f2) {
            this.f1 = f1;
            this.f2 = f2;
            this.f3 = 0;
        }

        public Group(int f1, int f2, int f3) {
            this.f1 = f1;
            this.f2 = f2;
            this.f3 = f3;
        }

        @Override
        public boolean equals(Object obj) {
            Group o = (Group) obj;
            return f1 == o.f1 && f2 == o.f2 && f3 == o.f3;
        }

        @Override
        public int hashCode() {
            return Objects.hash(f1, f2, f3);
        }
    }

    /**
     * 不同(m,n,k)场景下可能的枚举量的缓存
     */
    public static Map<Group, Long> fCache = new HashMap<>();
    /**
     * 组合数的缓存
     */
    public static Map<Group, Long> cCache = new HashMap<>();

    public static Long combination(int m, int n) {
        if (m < n || n < 0) {
            return 0L;
        } else if (n == 0) {
            return 1L;
        }
        Group group = new Group(m, n);
        Long result = cCache.get(group);
        if (result == null) {
            result = combination(m - 1, n) + combination(m - 1, n - 1);
            cCache.put(group, result);
        }
        log.debug("combination return [{}, {}; {}]", m, n, result);
        return result;
    }

    public static Long f(int m, int n, int k) {
        log.debug("f enter [{}, {}, {}]", m, n, k);
        Long result;
        if (k == 0) {
            if (n > m) {
                result = 0L;
            } else {
                result = combination(m, n);
            }
        } else if (n == 1) {
            result = 0L;
        } else {
            Group group = new Group(m, n, k);
            result = fCache.get(group);
            if (result == null) {
                result = f(m, n - 1, k - 1) * (n - k) + f(m, n - 1, k) * (m + 1 + k - n);
                fCache.put(group, result);
            }
        }
        log.debug("f return [{}, {}, {}; {}]", m, n, k, result);
        return result;
    }

    public static void showDistribution(int m, int n) {
        for (int i = 0; i < n; i++) {
            f(m, n, i);
        }
        System.out.println("conflict table:");
        for (int i = 0; i < n; i++) {
            System.out.printf("%2d: %d\n", i, f(m, n, i));
        }
    }

    public static void main(String[] args) {
        showDistribution(3,3);
        System.out.println();
    }
}
