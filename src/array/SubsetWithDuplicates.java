/*
*
* Given a set of numbers that might contain duplicates, find all of its distinct subsets.
*
* Input: [1, 5, 3, 3]
* Output: [], [1], [5], [3], [1,5], [1,3], [5,3], [1,5,3], [3,3], [1,3,3], [3,3,5], [1,5,3,3]
*
* */

package array;

import java.util.*;

class SubsetWithDuplicates {

    public static List<List<Integer>> findSubsets(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> subsets = new ArrayList<>();



        return subsets;
    }

    public static void main(String[] args) {
        List<List<Integer>> result = SubsetWithDuplicates.findSubsets(new int[] { 1, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);

        result = SubsetWithDuplicates.findSubsets(new int[] { 1, 5, 3, 3 });
        System.out.println("Here is the list of subsets: " + result);
    }
}

