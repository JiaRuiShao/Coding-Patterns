// Start of LinkedList Cycle (medium)
// Given the head of a Singly LinkedList,
// write a function to find the starting node of the cycle if there's any
// otherwise return null.

package fastslowpointers;

/*class ListNode{
    int value;
    ListNode next;

    public ListNode(int value) {
        this.value = value;
        next = null;
    }
}*/

import java.util.HashSet;
import java.util.Set;

public class FindCycleStart {

    /**
     * Use hashset to find the start node of the circle.
     *
     * TC: O(n)
     * SC: O(n)
     *
     * @param head the ListNode head
     * @return the starting ListNode of the circle
     */
    public ListNode findStartNode1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) return node;
            set.add(node);
            node = node.next;
        }
        return null;
    }

    /**
     * Floyd's Tortoise and Hare Algorithm.
     *
     * TC: O(n)
     * SC: O(1)
     *
     * @param head the ListNode head
     * @return the starting ListNode of the circle
     */
    public ListNode findStartNode2(ListNode head) {

        ListNode fast = head;
        ListNode slow = head;

        // find the meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null || fast.next == null) return null;

        // find the circle start node
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;

    }

    public static void main(String[] args) {
        FindCycleStart fs = new FindCycleStart();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList cycle start: " + fs.findStartNode1(head).value);
        System.out.println("LinkedList cycle start: " + fs.findStartNode2(head).value);

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList cycle start: " + fs.findStartNode1(head).value);
        System.out.println("LinkedList cycle start: " + fs.findStartNode2(head).value);

        head.next.next.next.next.next.next = head;
        System.out.println("LinkedList cycle start: " + fs.findStartNode1(head).value);
        System.out.println("LinkedList cycle start: " + fs.findStartNode2(head).value);
    }

}
