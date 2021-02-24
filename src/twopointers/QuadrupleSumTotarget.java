// Quadruple Sum to Target (medium)
// Given an array of unsorted numbers and a target number,
// find all unique quadruplets in it,
// whose sum is equal to the target number.

package twopointers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class QuadrupleSumTotarget {

    /**
     * Two Pointers Practice Problem #1 -- Medium.
     *
     * TC: O(nlogn + n^3) = O(n^3)
     * SC: O(n + 4^n) = O(4^n)
     *
     * @param arr    the input array
     * @param target the int target
     * @return a list of quadruplets whose sum equals to the target
     */
    public List<List<Integer>> findQuadruplets(int[] arr, int target) {
        List<List<Integer>> quadruplets = new LinkedList<>();
        int left, right;

        Arrays.sort(arr); // quicksort for primary data type
        for (int i = 0; i < arr.length - 3; i++) { // the first index
            // skip the duplicates
            if (i > 1 && arr[i] == arr[i - 1]) continue;
            for (int j = i + 1; j < arr.length - 2; j++) { // the second index
                // skip the duplicates
                if (j > 2 && arr[j] == arr[j - 1]) continue;
                left = j + 1; // the third index
                right = arr.length - 1; // the forth index
                while (left < right) {
                    if (arr[i] + arr[j] + arr[left] + arr[right] == target) {
                        quadruplets.add(Arrays.asList(arr[i], arr[j], arr[left++], arr[right--]));
                        // skip the duplicates
                        while (left < right && arr[left] == arr[left - 1]) left++;
                        while (left < right && arr[right] == arr[right + 1]) right--;
                    } else if (arr[i] + arr[j] + arr[left] + arr[right] < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }

    public static void main(String[] args) {
        QuadrupleSumTotarget tp = new QuadrupleSumTotarget();
        System.out.println(tp.findQuadruplets(new int[]{4, 1, 2, -1, 1, -3}, 1));
        System.out.println(tp.findQuadruplets(new int[]{2, 0, -1, 1, -2, 2}, 2));
    }

}
