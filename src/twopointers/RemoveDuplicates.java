// Remove Duplicates
// Given an array of sorted numbers, remove all duplicates from it.
// You should not use any extra space; after removing the duplicates in-place
// return the length of the subarray that has no duplicate in it.

package twopointers;

public class RemoveDuplicates {

    /**
     * Two Pointers -- Easy.
     * TC: O(n) where n is the num of elements in the input arr
     * SC: O(1)
     *
     * @param arr the input Integer arr
     * @return the length of the de-duplicate arr
     */
    public int removeDup(int[] arr) {
        // corner cases
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // elements in left side of the left pointer are unique
        // ints that we want to keep
        int left = 1, right = 1;
        while (right < arr.length) {
            if (arr[right] == arr[right - 1]) {
                right++;
            } else {
                arr[left++] = arr[right++];
            }
        }
        return left;
    }

    /**
     * Same using the for loop (actually looks simpler)
     * @param arr the input Integer arr
     * @return the length of the de-duplicate arr
     */
    public int removeDup2(int[] arr) {
        // corner cases
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // elements in left side of the left pointer are unique
        // ints that we want to keep
        int left = 1;
        for (int right = 1; right < arr.length; right++) {
            if (arr[right] != arr[right - 1]) {
                arr[left++] = arr[right];
            }
        }

        return left;
    }

    public static void main(String[] args) {
        RemoveDuplicates tp = new RemoveDuplicates();
        int[] arr = new int[]{2, 3, 3, 3, 6, 9, 9};
        System.out.println(tp.removeDup(arr));
        arr = new int[]{2, 3, 3, 3, 6, 9, 9};
        System.out.println(tp.removeDup2(arr));

        arr = new int[]{2, 2, 2, 11};
        System.out.println(tp.removeDup(arr));
        arr = new int[]{2, 2, 2, 11};
        System.out.println(tp.removeDup2(arr));
    }
}
