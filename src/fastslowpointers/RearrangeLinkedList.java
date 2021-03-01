// Rearrange a LinkedList (medium)
// Given the head of a Singly LinkedList, write a method to modify the LinkedList such that
// the nodes from the second half of the LinkedList are inserted alternately to the nodes
// from the first half in reverse order. So if the LinkedList has nodes
// 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> null, your method should return
// 1 -> 6 -> 2 -> 5 -> 3 -> 4 -> null.

package fastslowpointers;

public class RearrangeLinkedList {

    /**
     * Fast Slow Pointers -- Practice Problem #2.
     * TC: O(n)
     * SC: O(1)
     *
     * @param head the head of the given LinkedList
     */
    public void rearrange(ListNode head) {
        ListNode middle = findMiddle(head); // 1st middle node
        ListNode right = reverse(middle.next); // reverse the 2nd half & return the 2nd half head
        ListNode left = head; // 1st half head
        ListNode temp1, temp2; // temp nodes
        middle.next = null;

        while (left != null && right != null) {
            temp1 = left.next;
            temp2 = right.next;

            left.next = right;
            if (temp1 != null) {
                right.next = temp1;
            } else {
                break;
            }

            left = temp1;
            right = temp2;
        }
    }

    /**
     * A private helper function to find the middle node of the given LinkedList head.
     * When the length of the LinkedList is an oven num, return the 1nd middle point.
     *
     * @param head the given LinkedList head
     * @return the middle node of the LinkedList
     */
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * A private helper function to reverse a LinkedList.
     *
     * @param head the head of the given LinkedList
     * @return the new head of the reversed LinkedList
     */
    private ListNode reverse(ListNode head) {
        ListNode temp;
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    public static void main(String[] args) {
        RearrangeLinkedList fs = new RearrangeLinkedList();
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        fs.rearrange(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }

}
