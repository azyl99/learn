package solved;

import java.util.ArrayList;

/**
 * https://leetcode-cn.com/problems/next-greater-element-iii/
 *
 * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，并且其值大于n。如果不存在这样的32位整数，则返回-1。
 *
 * @author guya on 2019/6/25
 */
class Medium_cheat_最小重排大于整数 {
    /**
     * 思路 11 3 765421 —> 11 4 123567
     * @param n
     * @return
     */
    public int nextGreaterElement2(int n) {
        ArrayList<Integer> arr = new ArrayList<>();
        int max = 0;
        int x = 0;
        boolean find = false;
        while (n != 0) {
            x = n % 10;
            arr.add(x);
            n /= 10;

            if (max > x) {
                find = true;
                break;
            }
            max = x;
        }

        if (!find) {
            return -1;
        }
        arr.sort((a, b) -> a - b);

        int up = 0;
        for (int i = 0; i < arr.size(); i++) {
            if ((up = arr.get(i)) > x) {
                // 不加类型转换，默认删的是index
                arr.remove((Integer) up);
                arr.add(0, up);
                break;
            }
        }

        // 靠！找的是32位整数，更大的话会溢出
        long res = n;
        for (Integer i : arr) {
            res = res * 10 + i;
        }

        // >> 32 是错的，这样会是负数
        if ((res >> 31) != 0) {
            return -1;
        }

        return (int)res;
    }

    /**
     * 思路 11 3 765 4 21 —> 11 4 12 3 567
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        int tail = n % 10;
        n = n / 10;
        int x = tail;
        int max = tail;

        int medium = 0;
        int mlen = 0;
        boolean find = false;
        while (n != 0) {
            x = n % 10;
            n /= 10;

            if (max > x) {
                find = true;
                break;
            }

            max = x;
            medium = medium * 10 + x;
            mlen++;
        }

        if (!find) {
            return -1;
        }

        // 靠！找的是32位整数，更大的话会溢出
        long res = ( n * 100 + tail * 10 + x );
        res = res * (int)Math.pow(10, mlen) + medium;

        // >> 32 是错的，这样会是负数
        if ((res >> 31) != 0) {
            return -1;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        int r = new Medium_cheat_最小重排大于整数().nextGreaterElement(230241);
//        int r = new Medium_cheat_最小重排大于整数().nextGreaterElement(2147483647);
        System.out.println(r);
    }
}
