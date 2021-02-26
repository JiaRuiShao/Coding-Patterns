// Middle of the LinkedList (easy)
// Given the head of a Singly LinkedList, write a method to return the middle node of the LinkedList.
// If the total number of nodes in the LinkedList is even, return the second middle node.

package fastslowpointers;

public class LinkedListMiddle {

    /**
     * Fast & Slow Pointers -- Easy.
     *
     * TC: O(n)
     * SC: O(1)
     *
     * @param head head of the LinkedList
     * @return the middle node
     */
    public ListNode getMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        LinkedListMiddle fs = new LinkedListMiddle();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        System.out.println("Middle Node: " + fs.getMiddle(head).value);

        head.next.next.next.next.next = new ListNode(6);
        System.out.println("Middle Node: " + fs.getMiddle(head).value);

        head.next.next.next.next.next.next = new ListNode(7);
        System.out.println("Middle Node: " + fs.getMiddle(head).value);
    }
}
