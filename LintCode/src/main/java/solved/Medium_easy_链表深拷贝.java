package solved;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/copy-list-with-random-pointer/
 *
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 *
 * 要求返回这个链表的深拷贝。
 *
 * @author guya on 2019/6/25
 */
public class Medium_easy_链表深拷贝 {
    /*
    // Definition for a Node.
    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };
    */

    public Node copyRandomList(Node head) {

        Map<Node, Node> map = new HashMap<>();

        for (Node p = head; p != null; p = p.next) {
            Node node = new Node();
            node.val = p.val;
            map.put(p, node);

        }

        for (Node p = head; p != null; p = p.next) {
            Node node = map.get(p);
            if (p.next != null) {
                node.next = map.get(p.next);
            }
            if (p.random != null) {
                node.random = map.get(p.random);
            }
        }

        return map.get(head);
    }

    public static void main(String[] args) {
        Node head = new Node();
        (new Medium_easy_链表深拷贝()).copyRandomList(head);
    }


    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };
}
