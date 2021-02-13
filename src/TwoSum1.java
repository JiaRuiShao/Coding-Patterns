/* Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order. */

// Priority: Access fast
// https://leetcode.com/problems/two-sum/solution/

// 1. Arrays

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum1 {

    /**
     * Array Linear Search
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param nums
     * @param target
     * @return target indexes
     */

    public int[] getIndex(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * HashTable -- trading space for speed
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] getIndexTwoSum(int[] nums, int target) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hm.containsKey(target - nums[i])) {
                return new int[]{hm.get(target - nums[i]), i};
            }
            hm.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        TwoSum1 ts = new TwoSum1();
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(ts.getIndex(nums, target)));
        System.out.println(Arrays.toString(ts.getIndexTwoSum(nums, target)));

        nums = new int[] {3,2,4};
        target = 6;
        System.out.println(Arrays.toString(ts.getIndex(nums, target)));
        System.out.println(Arrays.toString(ts.getIndexTwoSum(nums, target)));

        nums = new int[] {3,3};
        target = 6;
        System.out.println(Arrays.toString(ts.getIndex(nums, target)));
        System.out.println(Arrays.toString(ts.getIndexTwoSum(nums, target)));
    }

}