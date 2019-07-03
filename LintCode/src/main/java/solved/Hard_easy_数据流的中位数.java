package solved;

import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/find-median-from-data-stream/
 *
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 *
 * 例如，
 *
 * [2,3,4] 的中位数是 3
 *
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 *
 * 设计一个支持以下两种操作的数据结构：
 *
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * 进阶:
 *
 * 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
 * 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
 *
 * @author guya on 2019/6/25
 */
class Hard_easy_数据流的中位数 {

    private PriorityQueue<Integer> left;
    private PriorityQueue<Integer> right;
    private int mid;
    private double middle;
    private int size;

    /** initialize your data structure here. */
    public Hard_easy_数据流的中位数() {
        left = new PriorityQueue<>((o1,o2)->o2-o1);
        right = new PriorityQueue<>((o1,o2)->o1-o2);
        size = 0;
    }

    public void addNum(int num) {
        // 偶数 -> 奇数
        if ((size & 1) == 0) {
            PriorityQueue<Integer> p;
            if (num < middle) {
                p = left;
            } else {
                p = right;
            }
            p.add(num);
            middle = mid = p.poll();
        }
        // 奇数->偶数
        else {
            int val;
            if (num < mid) {
                left.add(num);
                right.add(mid);
                val = left.peek();
            } else {
                right.add(num);
                left.add(mid);
                val = right.peek();
            }
            middle = (val + mid) / 2.0;
        }
        size++;
    }

    public double findMedian() {
        return middle;
    }

    public static void main(String[] args) {
        Hard_easy_数据流的中位数 mf = new Hard_easy_数据流的中位数();
        mf.addNum(-1);
        mf.addNum(-2);
        mf.addNum(-3);
        mf.addNum(-4);
        System.out.println(mf.findMedian());
    }
}

/**
 * Your Hard_easy_数据流的中位数 object will be instantiated and called as such:
 * Hard_easy_数据流的中位数 obj = new Hard_easy_数据流的中位数();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
