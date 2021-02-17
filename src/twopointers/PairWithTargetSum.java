// Pair with Target Sum
// Given an array of sorted numbers and a target sum,
// find a pair in the array whose sum is equal to the given target.

package twopointers;

public class PairWithTargetSum {

    /**
     * Two Pointers -- Easy.
     * A function to return the indices of the two numbers (i.e. the pair) such that
     * they add up to the given target.
     * TC: O(n)
     * SC: O(1)
     *
     * @param arr    the input arr
     * @param target target integer
     * @return two indices
     */
    public int[] findIndex(int[] arr, int target) {

        int left = 0, right = arr.length - 1;

        while (left < right) {
            if (arr[left] + arr[right] == target) {
                return new int[]{left, right};
            } else if (arr[left] + arr[right] < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[2]; // didn't found it
    }

    public static void main(String[] args) {
        PairWithTargetSum tp = new PairWithTargetSum();
        int[] result = tp.findIndex(new int[]{1, 2, 3, 4, 6}, 6);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
        result = tp.findIndex(new int[]{2, 5, 9, 11}, 11);
        System.out.println("Pair with target sum: [" + result[0] + ", " + result[1] + "]");
    }
}
