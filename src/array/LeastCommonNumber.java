/* In the example below, you are given three positive integer arrays which are sorted in ascending order.
You have to find the smallest number that is common in all three arrays.
Return -1 if the smallest common number is not found. */

package array;

public class LeastCommonNumber {
    /**
     * Given three integer arrays sorted in ascending order,
     * return the smallest number that is common in all three arrays.
     * Time: O(arr1.length + arr2.length + arr3.length) => O(n) linear
     * Space: O(1)
     *
     * @param arr1 arr1
     * @param arr2 arr2
     * @param arr3 arr3
     * @return the least common number; or -1 if not found
     */
    public int findLeastCommonNumber(int[] arr1, int[] arr2, int[] arr3) {
        if (arr1 == null || arr2 == null || arr3 == null) return -1;
        int i = 0, j = 0, k = 0, num;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            num = Math.max(Math.max(arr1[i], arr2[j]), arr3[k]);
            if (arr1[i] == arr2[j] && arr1[i] == arr3[k]) return num;
            if (arr1[i] < num) i++;
            if (arr2[j] < num) j++;
            if (arr3[k] < num) k++;
        }
        return -1; // not found
    }

    public static void main(String[] args) {
        LeastCommonNumber leastCommmon = new LeastCommonNumber();
        int[] v1 = new int[]{6, 7, 10, 25, 30, 63, 64};
        int[] v2 = new int[]{1, 4, 5, 6, 7, 8, 50};
        int[] v3 = new int[]{1, 6, 10, 14};
        System.out.println("Least Common Number: " + leastCommmon.findLeastCommonNumber(v1, v2, v3));
    }
}
