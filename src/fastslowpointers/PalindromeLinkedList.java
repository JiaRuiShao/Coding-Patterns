// Palindrome LinkedList (medium)
// Given the head of a Singly LinkedList, write a method to check
// if the LinkedList is a palindrome or not.
//
// Your algorithm should use constant space and the input LinkedList should
// be in the original form once the algorithm is finished.
// The algorithm should have O(N) time complexity
// where ‘N’ is the number of nodes in the LinkedList.

package fastslowpointers;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLinkedList {
    /**
     * Fast Slow Pointers -- Medium.
     * Reverse second-half in-place.
     *
     * We can use the Fast & Slow pointers method to find the middle node of the LinkedList.
     * Once we have the middle of the LinkedList, we will reverse the second half.
     * Then, we will compare the first half with the reversed second half to see
     * if the LinkedList represents a palindrome.
     * Finally, we will reverse the second half of the LinkedList again
     * to revert and bring the LinkedList back to its original form.
     *
     * Steps:
     * - Find the end of the first half(the middle node).
     * - Reverse the second half.
     * - Determine whether or not there is a palindrome.
     * - Restore the list.
     * - Return the result.
     *
     * TC: O(n)
     * SC: O(1) Modify the second half in-place
     *
     * @param head the head of the LinkedList
     * @return true if the LinkedList is a palindrome; false if not
     */
    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) return true;

        ListNode left = head;
        ListNode right = reverseLinkedList(findMiddle(head));
        ListNode temp = right; // store the head of the reversed second half
        boolean isPalindrome = true;

        while (right != null) {
            if (left.value != right.value) {
                isPalindrome = false;
                break;
            }
            left = left.next;
            right = right.next;
        }

        // reverse the second half back to what is was before
        reverseLinkedList(temp);

        return isPalindrome;
    }

    /**
     * A private helper function to find the middle node of the given LinkedList head.
     * When the length of the LinkedList is an oven num, return the 2nd middle point.
     *
     * @param head the given LinkedList head
     * @return the middle node of the LinkedList
     */
    private ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
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
    private ListNode reverseLinkedList(ListNode head) {

        if (head == null || head.next == null) return head;

        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode temp = curr.next; // store the previous next node
            curr.next = prev; // set new next node
            prev = curr; // update the prev
            curr = temp; // update the curr
        }

        return prev; // return the new head
    }

    /**
     * Copy the LinkedList to Array, then use two pointers to check if the array is palindrome.
     *
     * TC: O(2n) = O(n)
     * SC: O(n)
     *
     * @param head the head of the LinkedList
     * @return true if the LinkedList is a palindrome; false if not
     */
    public boolean isPalindromeArray(ListNode head) {
        ListNode node = head;
        // Convert LinkedList into ArrayList
        List<Integer> myArr = new ArrayList<>();
        while (node != null) {
            myArr.add(node.value);
            node = node.next;
        }

        // use two-pointers to check whether the arraylist is a palindrome
        int left = 0, right = myArr.size() - 1;
        while (left < right) {
            // Note that we must use ! .equals instead of !=
            // because we are comparing Integer, not int.
            if (!myArr.get(left).equals(myArr.get(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    public boolean isPalindromeRecursive(ListNode head) {
        current = head;
        return recursivelyCheck(head);
    }

    private ListNode current;

    /**
     * A private helper function to find whether the LinkedList is a palindrome
     * Here the node goes from head -> tail -> head
     * current goes from head -> tail
     *
     * TC: O(n)
     * SC: O(n) -- num of stack frames
     *
     * @param node the head of the LinkedList
     * @return true if the LinkedList is a palindrome; false if not
     */
    private boolean recursivelyCheck(ListNode node) {
        if (node != null) {
            if (!recursivelyCheck(node.next)) return false;
            if (current.value != node.value) return false;
            current = current.next;
        }
        return true;
    }

    public String display(ListNode head) {
        StringBuilder sb = new StringBuilder();
        ListNode node = head;
        while (node != null) {
            if (node == head) {
                sb.append("[");
            }
            if (node.next != null) {
                sb.append(node.value).append(", ");
            } else {
                sb.append(node.value).append("]");
            }
            node = node.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        PalindromeLinkedList palindrome = new PalindromeLinkedList();

        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);

        System.out.println("Is palindrome: " + palindrome.isPalindrome(head));
//        System.out.println(palindrome.display(head));
        System.out.println("Is palindrome: " + palindrome.isPalindromeArray(head));
        System.out.println("Is palindrome: " + palindrome.isPalindromeRecursive(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + palindrome.isPalindrome(head));
//        System.out.println(palindrome.display(head));
        System.out.println("Is palindrome: " + palindrome.isPalindromeArray(head));
        System.out.println("Is palindrome: " + palindrome.isPalindromeRecursive(head));
    }
}
