// Cycle in a Circular Array (hard)

// We are given an array containing positive and negative numbers.
// Suppose the array contains a number ‘M’ at a particular index.
// Now, if ‘M’ is positive we will move forward ‘M’ indices and
// if ‘M’ is negative move backwards ‘M’ indices.

// You should assume that the array is circular which means two things:
//   1) If, while moving forward, we reach the end of the array,
//   we will jump to the first element to continue the movement.
//   2) If, while moving backward, we reach the beginning of the array,
//   we will jump to the last element to continue the movement.

// Write a method to determine if the array has a cycle.
// The cycle should have more than one element and should follow one direction
// which means the cycle should not contain both forward and backward movements
// (arr[seq[i]] is either all pos or all neg).

package fastslowpointers;

import java.util.HashSet;
import java.util.Set;

public class CircularArrayLoop {

    /**
     * Finding the loop in a given arr using Hashset.
     * TC: O(n^2)
     * SC: O(n)
     *
     * @param arr the given arr
     * @return true if there's a valid loop; false if not
     */
    public boolean findLoop(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            if (loopExist(arr, i)) return true;
        }

        return false;
    }

    /**
     * A private helper function to find the loop using hashset.
     * TC: O(n)
     * SC: O(n)
     *
     * @param arr the given int[] arr
     * @param idx current index
     * @return whether there's a valid loop
     */
    private boolean loopExist(int[] arr, int idx) {
        Set<Integer> set = new HashSet<>(); // store the indices
        int nextIdx, numEntry = arr[idx];
        while (true) {
            nextIdx = (idx + arr[idx]) % arr.length >= 0 ? (idx + arr[idx]) % arr.length : arr.length + (idx + arr[idx]) % arr.length;
            if (arr[idx] * arr[nextIdx] < 0) return false;
            if (set.contains(idx)) {
                return set.size() > 1 && arr[idx] == numEntry;
            }
            set.add(idx);
            idx = nextIdx;
        }
    }

    /**
     * Fast Slow Pointers -- Practice Problem #3.
     * TC: O(n^2)
     * SC: O(1)
     *
     * @param arr the given arr
     * @return true if there's a valid loop; false if not
     */
    public boolean isCircularArray(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            if (loopExist2(arr, i)) return true;
        }

        return false;
    }

    /**
     * A private helper function to find whether there's a valid loop exist using fast slow pointers.
     *
     * @param arr the given arr
     * @param idx given index
     * @return true if there's a valid loop; false if not
     */
    private boolean loopExist2(int[] arr, int idx) {
        int nextIdx = idx, nextNextIdx = idx;
        while (true) {
            nextIdx = nextIdx(arr, nextIdx);
            if (nextIdx == -1 || nextIdx(arr, nextNextIdx) == -1) return false;
            nextNextIdx = nextIdx(arr, nextIdx(arr, nextNextIdx));
            if (nextNextIdx == -1) return false;
            if (nextIdx == nextNextIdx) return true;
        }
    }

    /**
     * A private helper function to find the next index.
     *
     * @param arr the given arr
     * @param idx current index
     * @return the next index if it's valid; else return -1
     */
    private int nextIdx(int[] arr, int idx) {
        int nextIdx = (idx + arr[idx]) % arr.length >= 0 ? (idx + arr[idx]) % arr.length : arr.length + (idx + arr[idx]) % arr.length;
        if (nextIdx == idx || arr[idx] * arr[nextIdx] < 0) return -1;
        return nextIdx;
    }


    public static void main(String[] args) {
        CircularArrayLoop circular = new CircularArrayLoop();
        System.out.println(circular.findLoop(new int[]{1, 2, -1, 2, 2})); // true
        System.out.println(circular.findLoop(new int[]{2, 2, -1, 2})); // true
        System.out.println(circular.findLoop(new int[]{2, 1, -1, -2})); // false
        System.out.println(circular.findLoop(new int[]{2, -1, 1, 2, 2})); // true
        System.out.println(circular.findLoop(new int[]{-1, 2})); // false b/c length == 1
        System.out.println(circular.findLoop(new int[]{-2, 1, -1, -2, -2})); // false b/c not all nums in the cycle are in the same direction
        System.out.println(circular.findLoop(new int[]{-1, -2, -3, -4, -5})); // false
    }
}

