/*
 * Given a sorted number array and two integers ‘K’ and ‘X’, find ‘K’ closest numbers to ‘X’ in the array.
 * Return the numbers in the sorted order. ‘X’ is not necessarily present in the array.
 * ====================================================================================
 * Input: [5, 6, 7, 8, 9], K = 3, X = 7
 * Output: [6, 7, 8]
 *
 * Input: [2, 4, 5, 6, 9], K = 3, X = 6
 * Output: [4, 5, 6]
 *
 * Input: [2, 4, 5, 6, 9], K = 3, X = 10
 * Output: [5, 6, 9]
 */

package topknumbers;

import java.util.*;

public class KClosestElements {

    /**
     * Use a max Heap with size k to store the  k numbers that have the smallest absolute distance to the target X.
     * Time: O(NlogK + KlogK)
     * Space: O(K)
     *
     * @param arr the input arr
     * @param K   K
     * @param X   the target num X
     * @return k numbers that have the smallest absolute distance to the target num X
     */
    public List<Integer> findClosestElements(int[] arr, int K, Integer X) {
        List<Integer> result = new ArrayList<>(K);

        Queue<Integer> maxHeap = new PriorityQueue<>(K + 1, (a, b) -> Integer.compare(Math.abs(b - X), Math.abs(a - X)));
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > K) maxHeap.poll();
        }

        result.addAll(maxHeap);
        Collections.sort(result);
        return result;
    }

    /**
     * Binary Search to find the element that is nearest to the target.
     * Time: O(logN)
     * Space: O(1)
     *
     * @param arr    the input arr
     * @param target the target
     * @return the index of the element that is nearest to the target
     */
    private int bs(int[] arr, Integer target) {
        int left = 0, right = arr.length - 1, mid;
        while (left < right - 1) {
            mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        } // terminate when left == right - 1
        if (Math.abs(arr[left] - target) < Math.abs(arr[right] - target)) return left;
        return right;
    }

    /**
     * Binary Search to find the nearest number and then use two pointers to search for the left side and right side.
     * Time: O(logN + K)
     * Space: O(1)
     *
     * @param arr the input arr
     * @param K   K
     * @param X   the target num X
     * @return k numbers that have the smallest absolute distance to the target num X
     */
    public List<Integer> findClosestElementsImproved(int[] arr, int K, Integer X) {
        List<Integer> result = new ArrayList<>(K);
        int index = bs(arr, X);
        int left = index, right = index + 1;
        for (int i = 0; i < K; i++) {
            if (left >= 0 && right < arr.length) {
                int leftDist = Math.abs(arr[left] - X);
                int rightDist = Math.abs(arr[right] - X);
                if (leftDist < rightDist) {
                    result.add(0, arr[left--]);
                } else {
                    result.add(arr[right++]);
                }
            } else if (left >= 0) result.add(0, arr[left--]);
            else if (right < arr.length) result.add(arr[right++]);
        }
        return result;
    }

    public static void main(String[] args) {
        KClosestElements elements = new KClosestElements();

        List<Integer> result = elements.findClosestElements(new int[]{5, 6, 7, 8, 9}, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);
        result = elements.findClosestElementsImproved(new int[]{5, 6, 7, 8, 9}, 3, 7);
        System.out.println("'K' closest numbers to 'X' are: " + result);
        System.out.println();

        result = elements.findClosestElements(new int[]{2, 4, 5, 6, 9}, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);
        result = elements.findClosestElementsImproved(new int[]{2, 4, 5, 6, 9}, 3, 6);
        System.out.println("'K' closest numbers to 'X' are: " + result);
        System.out.println();

        result = elements.findClosestElements(new int[]{2, 4, 5, 6, 9}, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);
        result = elements.findClosestElementsImproved(new int[]{2, 4, 5, 6, 9}, 3, 10);
        System.out.println("'K' closest numbers to 'X' are: " + result);
        System.out.println();
    }
}
