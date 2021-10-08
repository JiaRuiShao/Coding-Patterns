/*
 * Given an array of ‘K’ sorted LinkedLists, merge them into one sorted list.
 */

package kwaymerge;

import java.util.PriorityQueue;
import java.util.Queue;

class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
        next = null;
    }
}

class MergeKSortedLists {

    /**
     * Time: O(klogk + nlogk) = O((n + k)logk)
     * Space: O(k)
     *
     * @param lists the given ListNode head arr
     * @return the merged ListNodes head sorted in ascending order
     */
    public ListNode merge(ListNode[] lists) {
        ListNode merged = null, curr = null;

        // corner case
        if (lists == null || lists.length < 1) {
            return null;
        }

        // create a heap with size of lists.length to find the min value of the nodes
        // initialize the default arr with default capacity of lists.length, so it won't be resized
        // pass a lambda comparator in to define the order of ListNode class
        Queue<ListNode> minHeap = new PriorityQueue<>(lists.length, (n1, n2) -> Integer.compare(n1.value, n2.value));

        // we can also create an anonymous inner Comparator class to compare ListNodes by their value
        /*Queue<ListNode> minHeap = new PriorityQueue<>(lists.length, new Comparator<>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return Integer.compare(n1.value, n2.value);
            }
        });*/

        // Time: O(log1 + log2 + ... + logk) = O(log(1*2*...*k)) = O(log(k!)) < O(klogk)
        for (ListNode node : lists) {
            minHeap.offer(node);
        }

        // poll the ListNode out until we compare all nodes in the lists
        ListNode temp;
        while (!minHeap.isEmpty()) { // O(n)
            temp = minHeap.poll(); // O(logk) * O(n)

            // add the temp to the merged ll
            if (merged == null) {
                merged = temp;
                curr = temp;
            } else {
                curr.next = temp;
                curr = curr.next;
            }

            // add the next node into the minHeap (if there's any)
            if (temp.next != null) {
                minHeap.offer(temp.next); // O(logk) * O(n)
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = new MergeKSortedLists().merge(new ListNode[]{l1, l2, l3});
        System.out.print("Here are the elements form the merged list: ");
        while (result != null) {
            System.out.print(result.value + " ");
            result = result.next;
        }
    }
}