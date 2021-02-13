/* Given an array of integers numbers that is already sorted
in ascending order, find two numbers such that they add up to
a specific target number.

Return the indices of the two numbers (1-indexed)
as an integer array answer of size 2, where
1 <= answer[0] < answer[1] <= numbers.length.

You may assume that each input would have exactly one solution
and you may not use the same element twice. */

// https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/solution/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum2 {

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
        for (int i = nums.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] + nums[j] == target) {
                    // here j is always smaller than i
                    return new int[]{++j, ++i};
                }
            }
        }
        return new int[2];
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
                // here nums[i] is always larger than target - nums[i]
                // bc the input array is in ascending order, so the index of
                // target - nums[i] is less than the index of nums[i]
                return new int[]{hm.get(target - nums[i]) + 1, ++i};
            }
            hm.put(nums[i], i);
        }
        return new int[2];
    }

    /**
     * HashTable -- trading space for speed
     * Moving from right to left
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] getIndexTwoSum2(int[] nums, int target) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int complement = target - nums[i];
            if (hm.containsKey(complement)) {
                return new int[]{++i, hm.get(complement) + 1};
            }
            hm.put(nums[i], i);
        }
        return new int[2];
    }

    /**
     * Two pointers.
     * TC: O(n)
     * SC: O(1)
     * Compare the sum of these two elements with target.
     * If the sum is equal to target, we found the exactly only solution.
     * If it is less than target, we increase the smaller index by one.
     * If it is greater than target, we decrease the larger index by one.
     * @param nums
     * @param target
     * @return
     */
    public int[] twoPointers(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] + nums[j] == target) {
                return new int[] {++i, ++j};
            } else if (nums[i] + nums[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[2];
    }

    public static void main(String[] args) {
        TwoSum2 ts2 = new TwoSum2();
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(ts2.getIndex(nums, target)));
        System.out.println(Arrays.toString(ts2.getIndexTwoSum(nums, target)));
        System.out.println(Arrays.toString(ts2.getIndexTwoSum2(nums, target)));
        System.out.println(Arrays.toString(ts2.twoPointers(nums, target)));

        nums = new int[] {2,3,4};
        target = 6;
        System.out.println(Arrays.toString(ts2.getIndex(nums, target)));
        System.out.println(Arrays.toString(ts2.getIndexTwoSum(nums, target)));
        System.out.println(Arrays.toString(ts2.getIndexTwoSum2(nums, target)));
        System.out.println(Arrays.toString(ts2.twoPointers(nums, target)));

        nums = new int[] {-1,0};
        target = -1;
        System.out.println(Arrays.toString(ts2.getIndex(nums, target)));
        System.out.println(Arrays.toString(ts2.getIndexTwoSum(nums, target)));
        System.out.println(Arrays.toString(ts2.getIndexTwoSum2(nums, target)));
        System.out.println(Arrays.toString(ts2.twoPointers(nums, target)));
    }

}
