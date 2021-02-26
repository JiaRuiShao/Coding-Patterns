// LinkedList Cycle (easy)
// Given the head of a Singly LinkedList, write a function to determine
// if the LinkedList has a cycle in it or not.

package fastslowpointers;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a Singly-Linked list
 */
class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class LinkedListCycle {

    /**
     * Use Hashset to record the nodes' reference
     *
     * TC: O(n)
     * SC: O(n)
     *
     * @param head the head node of the LinkedList
     * @return true if the LinkedList contains a circle in it; false if not
     */
    public boolean hasCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return true;
            } else {
                set.add(node);
            }
            node = node.next;
        }
        return false;
    }

    /**
     * Fast Slow Pointers -- Easy.
     *
     * TC: O(n)
     * SC: O(1)
     *
     * @param head the head node of the LinkedList
     * @return true if the LinkedList contains a circle in it; false if not
     */
    public boolean hashCycle2(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListCycle fs = new LinkedListCycle();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        System.out.println("LinkedList has cycle: " + fs.hasCycle1(head) + " " + fs.hashCycle2(head));

        head.next.next.next.next.next.next = head.next.next;
        System.out.println("LinkedList has cycle: " + fs.hasCycle1(head) + " " + fs.hashCycle2(head));

        head.next.next.next.next.next.next = head.next.next.next;
        System.out.println("LinkedList has cycle: " + fs.hasCycle1(head) + " " + fs.hashCycle2(head));
    }
}
