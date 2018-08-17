// 这题是暴力调试出来的，时间复杂度log(n)
public class a_0到n的自然数中数字k的数量 {
    /*
     * https://www.lintcode.com/problem/digit-counts/description
     * @param : An integer
     * @param : An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        /**
         * 十位数的3个数：n/100*10  +  min(max(n%100-30+1, 0), 10)
         * 十位数的k个数：n/100*10  +  min(max(n%100-k*10+1, 0), 10)
         */
        int temp, res = 0;
        for (int i = 1, j = 10; ; i = j, j *=10) {
            temp = n/j*i// 734: 700 以下十位数3的数量
                    + Math.min( Math.max( n%j- k*i + 1, 0 ), i );// 734: 700 以上十位数3的数量
            if (k == 0 && i != 1) {// 如果数0的个数，需要排除两位数以上多算的首位为0的情况
                temp = Math.max(temp - i, 0);
            }
            if (temp == 0) {
                break;
            }
            res += temp;
        }
        return res;
    }

    public static void main(String[] args) {
        a_0到n的自然数中数字k的数量 a = new a_0到n的自然数中数字k的数量();
//        a.digitCounts(3, 13);
//        a.digitCounts(1, 12);
        System.out.println(a.digitCounts(0, 17));
        System.out.println(a.digitCounts(0, 117));
//        System.out.println(a.digitCounts(3, 333));
    }
};