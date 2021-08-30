/*
 We are given an array containing ‘n’ objects.
 Each object, when created, was assigned a unique number from 1 to ‘n’ based on their creation sequence.
 This means that the object with sequence number ‘3’ was created just before the object with sequence number ‘4’.
 Write a function to sort the objects in-place on their creation sequence number in O(n)
 and without any extra space. For simplicity, let’s assume we are passed an integer array containing
 only the sequence numbers, though each number is actually an object.

 Example:
 Input: [3, 1, 5, 4, 2]
 Output: [1, 2, 3, 4, 5]
*/

package array;

class CyclicSort {

    private void swap(int[] nums, int one, int two) {
        int temp = nums[one];
        nums[one] = nums[two];
        nums[two] = temp;
    }

    /**
     * Time: O(n)
     * Space: O(1)
     *
     * @param nums the input arr
     */
    public void sort(int[] nums) {
        int start = 0, num;
        do {
            num = nums[start];
            if (nums[num - 1] != num) { // if the element is not at its supposed index
                swap(nums, start, num - 1);
            } else {
                start++;
            }
        } while (start < nums.length);
    }

    public static void main(String[] args) {

        CyclicSort solution = new CyclicSort();

        int[] arr = new int[]{3, 1, 5, 4, 2};
        solution.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[]{2, 6, 4, 3, 1, 5};
        solution.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();

        arr = new int[]{1, 5, 6, 4, 3, 2};
        solution.sort(arr);
        for (int num : arr)
            System.out.print(num + " ");
        System.out.println();
    }
}

