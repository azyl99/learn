package solved;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * <p>
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author guya on 2019/6/25
 */
public class Hard_medium_合并k个链表 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */

    /*
    // TreeSet -> PriorityQueue
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode dummy = new ListNode(0), current = dummy;
        Arrays.sort(lists, (n1, n2) -> n1.val - n2.val);
        // TreeSet会自动去重，所以值相等的时候，比较器也不能相等；这里其实不严谨，hashCode可能相等，但似乎没有更好的解决办法；但是这段在addAll的时候报错。。。
        TreeSet<ListNode> s = new TreeSet<>((o1, o2)->o1.val-o2.val != 0 ? o1.val-o2.val : o1.hashCode() - o2.hashCode());
        Collections.addAll(s, lists);

        while (!s.isEmpty()) {
            ListNode node = s.first();
            s.remove(node);
            if (node.next != null) {
                s.add(node.next);
            }

            current.next = node;
            current = node;
        }

        current.next = null;

        return dummy.next;
    }
    */


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(new Comparator<ListNode>(){
            @Override
            public int compare(ListNode n1, ListNode n2){
                return n1.val - n2.val;
            }
        });

        // 有可能list里的节点本身是空的
//        Collections.addAll(queue, lists);
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        ListNode dummy = new ListNode(0), current = dummy;
        while (queue.size() != 0) {
            ListNode node = queue.poll();
            if (node.next != null) {
                queue.add(node.next);
            }
            current.next = node;
            current = node;
        }

        current.next = null;

        return dummy.next;
    }

    public static void main(String[] args) {
        Hard_medium_合并k个链表 hardmedium合并k个链表 = new Hard_medium_合并k个链表();
        ListNode[] lists = new ListNode[] {
                makeListNode(new int[] {1,4,5}),
                makeListNode(new int[] {1,3,4}),
                makeListNode(new int[] {2,6}),
        };
        ListNode result = hardmedium合并k个链表.mergeKLists(lists);
        print(result);
    }

    public static ListNode makeListNode(int[] arr) {
        ListNode last = null, node = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            node = new ListNode(arr[i]);
            node.next = last;
            last = node;
        }
        return node;
    }

    public static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
