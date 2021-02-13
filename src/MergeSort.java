/**
 * @author JiaRui Shao
 * Description: This class is to sorted an ArrayList<Integer>
 *   - base case
 *   - split the array into left and right parts
 *   - merge the two parts
 */

import java.util.ArrayList;

public class MergeSort {
    ArrayList<Integer> myArray;

    /**
     * Constructor
     * @param myArray the array that is to be sorted
     */
    public MergeSort(ArrayList<Integer> myArray) {
        this.myArray = myArray;
    }

    /**
     * Check whether the ArrayList is null and call the mergeSort method
     * @return the result of mergeSort method
     */
    public ArrayList<Integer> process() {
        //check NUll
        if (myArray == null) return myArray;
        else {
            return mergeSort(myArray, 0, myArray.size() - 1);
        }
    }

    /**
     * Recursive mergeSort
     * @param array the arraylist we would like to sort
     * @param left the left index of the arraylist
     * @param right the right index of the arraylist
     * @return the result of the sorted arraylist
     */
    private ArrayList<Integer> mergeSort(ArrayList<Integer> array, int left, int right) {
        ArrayList<Integer> result = new ArrayList<>();
        // base case
        if (left > right) return result;
        else if (left == right) {
            result.add(array.get(left));
            return result;
        }
        else {
            //recursive mergeSort
            int mid = left + (right - left) / 2;
            ArrayList<Integer> leftResult = mergeSort(array, left, mid);
            ArrayList<Integer> rightResult = mergeSort(array, mid + 1, right);
            merge(leftResult, rightResult, result);
            return result;
        }

    }

    /**
     * Merge the left and right sides using while loop
     * @param left left side of the ArrayList
     * @param right right side of the ArrayList
     * @param result sorted ArrayList of the left-side ArrayList and right-side ArrayList
     */
    private void merge(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> result) {
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i) <= right.get(j)) {
                result.add(left.get(i++));
            }
            else { // right.get(j) < left.get(i)
                result.add(right.get(j++));
            }
        }
        // if we still have some elements at left side, add them to the result
        while (i < left.size()) {
            result.add(left.get(i++));
        }
        // if we still have some elements at right side, add them to the result
        while (j < right.size()) {
            result.add(right.get(j++));
        }
    }

}
