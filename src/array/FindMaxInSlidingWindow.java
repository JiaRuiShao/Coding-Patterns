/*
Given a large array of integers and a window of size w,
find the current maximum value in the window as the window slides through the entire array.
*/

package array;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class FindMaxInSlidingWindow {

    /**
     * Given a large array of integers and a window of size w,
     * find the current maximum value in the window as the window slides through the entire array.
     *
     * Time Complexity: O(n) -- Every element is pushed and popped from the deque only once in a single traversal.
     * Space Complexity: O(windowSize) --> O(1)
     *
     * @param arr the input array
     * @param windowSize the given window size
     * @return the result
     */
    public LinkedList<Integer> findMaxSlidingWindow(int[] arr, int windowSize) {
        LinkedList<Integer> result = new LinkedList<>(); // ArrayDeque for storing values
        Deque<Integer> temp = new LinkedList<Integer>(); // creating a linked list to store the values and index

        if (arr == null || arr.length < windowSize) return result;

        for (int i = 0; i < arr.length; i++) {
            if (i < windowSize) {
                // store the value indexes in descending order
                while (!temp.isEmpty() && arr[temp.peekLast()] < arr[i]) temp.removeLast();
                temp.addLast(i);
            } else { // windowSize <= i < arr.length
                result.add(arr[temp.peekFirst()]);
                // Remove the elements indexes which are not in the current window
                while (!temp.isEmpty() && temp.peekFirst() < i - windowSize + 1) temp.removeFirst();
                // Remove the smaller elements indexes which are not required
                while (!temp.isEmpty() && arr[temp.peekLast()] < arr[i]) temp.removeLast();
                // Add the newly picked element index from the right
                temp.addLast(i);
            }
        }
        // add the max value from the last window
        result.add(arr[temp.peekFirst()]);
        return result;
    }

    public static void main(String[] args) {
        FindMaxInSlidingWindow solution = new FindMaxInSlidingWindow();
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Array = " + Arrays.toString(array));
        System.out.println("Max = " + solution.findMaxSlidingWindow(array, 3));
        System.out.println();

        int[] array2 = {10, 6, 9, -3, 23, -1, 34, 56, 67, -1, -4, -8, -2, 9, 10, 34, 67};
        System.out.println("Array = " + Arrays.toString(array2));
        System.out.println("Max = " + solution.findMaxSlidingWindow(array2, 3));
    }

}
