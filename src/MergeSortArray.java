import java.util.Arrays;

/**
 * @author JiaRui Shao
 * Description: this class is to use merge sort algorithm to sort an int array
 * and check the validacity of the code
 */
public class MergeSortArray {
    int[] array;
    /**
     * Constructor
     * @param array the input int array that needed to be sorted
     */
    public MergeSortArray(int[] array) {
        this.array = array;
    }

    /**
     * check if the input array is null and process to mergeSort if not
     */
    public int[] mergeSortProcess() {
        // check null
        if (array == null) {
            return array;
        }
        // allocate helper array to help merge steps,
        // so that we guarantee no more than O(n) space is used.
        // The space complexity is O(n) in this case.
        int[] helper = new int[array.length];
        mergeSort(array, helper, 0, array.length - 1);
        return array;
    }

    /**
     * Recursive merge sort
     * @param array
     * @param helper
     * @param left
     * @param right
     */
    private void mergeSort(int[] array, int[] helper, int left, int right) {
        // base case
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        merge(array, helper, left, mid, right);
    }

    /**
     * merge the left side and right side using helper and pointers
     * @param array
     * @param helper intermediate array
     * @param left left index
     * @param mid middle index
     * @param right right index
     */
    private void merge(int[] array, int[] helper, int left, int mid, int right) {
        // copy the array to helper array and
        // we will merge from the helper array
        for (int i = left; i <= right; i++) {
            helper[i]  = array[i];
        }
        int leftIndex = left;
        int rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= right) {
            if (helper[leftIndex] <= helper[rightIndex]) {
                array[left++] = helper[leftIndex++];
            }
            else { // helper[rightIndex] < helper[leftIndex]
                array[left++] = helper[rightIndex++];
            }
        }

        // copy the remaining elements at left side
        while (leftIndex <= mid) {
            array[left++] = helper[leftIndex++];
        }
        // if there's any remaining elements at right side,
        // we don't have to copy them because they are already in their position
    }

    public static void main(String[] args) {
        int[] testArray = null;
        MergeSortArray solution = new MergeSortArray(testArray);
        testArray = solution.mergeSortProcess();
        System.out.println(Arrays.toString(testArray));

        testArray= new int[0];
        System.out.println(Arrays.toString(testArray));
        solution.array = testArray;
        testArray = solution.mergeSortProcess();
        System.out.println(Arrays.toString(testArray));

        testArray= new int[] {4, 3, 1, 2};
        System.out.println(Arrays.toString(testArray));
        solution.array = testArray;
        testArray = solution.mergeSortProcess();
        System.out.println(Arrays.toString(testArray));

        testArray= new int[] {4, 2, 40, 5, 300, 0, 2};
        System.out.println(Arrays.toString(testArray));
        solution.array = testArray;
        testArray = solution.mergeSortProcess();
        System.out.println(Arrays.toString(testArray));
    }
}
