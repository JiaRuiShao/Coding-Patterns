/*
 * Given a set of numbers that might contain duplicates, find all of its distinct subsets.
 * Input: [1, 3, 3]
 * Output: [], [1], [3], [1,3], [3,3], [1,3,3]
 *
 * Solution:
 * Sort all numbers of the given set. This will ensure that all duplicate numbers are next to each other.
 * Follow the same BFS approach but whenever we are about to process a duplicate
 * (i.e., when the current and the previous numbers are same),
 * instead of adding the current number (which is a duplicate) to all the existing subsets,
 * only add it to the subsets which were created in the previous step.
 *
 * */

package subsets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SubsetWithDuplicates {

    /**
     * Breadth First Search (BFS) -- Easy.
     *
     * Time: best O(N) || worst O(N*2^N)
     * Space: O(N*2^N)
     *
     * @param nums the input array
     * @return the de-duplicated sub-arrays
     */
    public static List<List<Integer>> findSubsets(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> newSet;
        results.add(new ArrayList<>());
        Arrays.sort(nums);

        int start = 0, end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                start = end + 1;
            } else {
                start = 0;
            }
            end = results.size() - 1;
            for (int j = start; j <= end; j++) {
                newSet = new ArrayList<>(results.get(j));
                newSet.add(nums[i]);
                results.add(newSet);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[]{1, 3, 3});
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetWithDuplicates.findSubsets(new int[]{1, 5, 3, 3});
        System.out.println("Here is the list of subsets: " + result);
    }
}
