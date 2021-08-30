/*
 * Given a set with distinct elements, find all of its distinct subsets.
 *
 * Input: [1, 3]
 * Output: [], [1], [3], [1,3]
 *
 * */

package subsets;

import java.util.ArrayList;
import java.util.List;

public class Subsets {


    /**
     * Breadth First Search(BFS) -- Easy.
     *
     * Time: O(N * 2^N)
     * Space: O(N * 2^N)
     *
     * @param nums the input integer array
     * @return all subsets of input array
     */
    public static List<List<Integer>> findSubsets(int[] nums) {

        List<List<Integer>> results = new ArrayList<>();
        List<Integer> newSet;
        results.add(new ArrayList<>()); // start by adding the empty subset

        // corner case
        if (nums == null || nums.length == 0) {
            return results;
        }

        for (int num : nums) { // O(n)
            // take all existing subsets and insert the current number to create new subsets
            int n = results.size(); // size keep doubling: 2^0, 2^1, 2^2,...2^n
            for (int i = 0; i < n; i++) { // ** here CANNOT use result.size --> endless
                newSet = new ArrayList<>(results.get(i));
                newSet.add(num);
                results.add(newSet);
            }
        }

        return results;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = Subsets.findSubsets(new int[]{1, 3});
        System.out.println("Here is the list of subsets: " + result);

        result = Subsets.findSubsets(new int[]{1, 5, 3});
        System.out.println("Here is the list of subsets: " + result);
    }

}
