/*
 * Similar Problems:
 *
 * Problem 1: Given ‘M’ sorted arrays, find the median number among all arrays.
 * Solution: This problem is similar to our parent problem with K=Median
 * So if there are ‘N’ total numbers in all the arrays we need to find the K’th minimum number where K=N/2.
 *
 * Problem 2: Given a list of ‘K’ sorted arrays, merge them into one sorted list.
 * Solution: This problem is similar to Merge K Sorted Lists except that the input is a list of arrays compared to LinkedLists.
 */

package kwaymerge;

import java.util.*;

class Node {
    int elementIndex;
    int arrayIndex;

    Node(int arrayIndex, int elementIndex) {
        this.arrayIndex = arrayIndex;
        this.elementIndex = elementIndex;
    }
}

public class KthSmallestInMSortedArrays {

    /**
     * Use a minHeap to store the m integers where m is the lists.size()
     * Time: O((m + k) * logm) where m is the num of Integer arr in the given lists
     * Space: O(m) where m is the num of Integer arr in the given lists
     *
     * @param lists the given list of sorted Integer arr
     * @param k k
     * @return the kth smallest integer in the given list of arr
     */
    public Integer kthSmallest(List<Integer[]> lists, int k) {
        int[] sorted = new int[k];
        if (lists == null || lists.size() < 1 || k < 1) {
            return null;
        }

        Queue<Node> minHeap = new PriorityQueue<>(lists.size(), (a, b) ->
                Integer.compare(lists.get(a.arrayIndex)[a.elementIndex], lists.get(b.arrayIndex)[b.elementIndex]));

        // initialize the minHeap -- O(mlogm)
        for (int i = 0; i < lists.size(); i++) {
            minHeap.offer(new Node(i, 0));
        }

        // find the kth smallest -- O(klogm)
        int count = 0;
        Node temp;
        while (!minHeap.isEmpty()) {
            temp = minHeap.poll();
            if (++count == k) {
                return lists.get(temp.arrayIndex)[temp.elementIndex];
            }
            if (temp.elementIndex < lists.get(temp.arrayIndex).length - 1) {
                minHeap.offer(new Node(temp.arrayIndex, temp.elementIndex + 1));
            }
        }

        return null;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[] { 2, 6, 8 };
        Integer[] l2 = new Integer[] { 3, 6, 7 };
        Integer[] l3 = new Integer[] { 1, 3, 4 };
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = new KthSmallestInMSortedArrays().kthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}
