// Given an array, find the average of all contiguous subarrays
// of size ‘K’ in it.

package slidingwindow;

import java.util.Arrays;

public class AverageSubArray {

    /**
     * Sliding Window -- Easy.
     * Keep the sum of k - 1 elements, everytime move forward,
     * we add the next end index element value and
     * minus the previous start index element value.
     * @param arr the array to be slided
     * @param k slide k elements
     * @return the average values for each k elements in arr
     */
    public double[] findAverageOfKElements(int[] arr, int k) {
        // sliding the startIdx from 0 to arr.length - k
        // or slide the endIdx from k - 1 to arr.length - 1
        double[] avg = new double[arr.length - k + 1];
        double sum = 0; // notice that here sum has to be declared as double
        // or else the averages we calculated are rounded to ints
        int startIdx = 0;
        for (int endIdx = 0; endIdx < arr.length; endIdx++) {
            sum += arr[endIdx];
            if (endIdx >= k - 1) {
                avg[startIdx] = sum / k;
                sum -= arr[startIdx++];
            }
        }
        return avg;
    }

    public static void main(String[] args) {
        AverageSubArray sw = new AverageSubArray();
        System.out.println(Arrays.toString(sw.findAverageOfKElements(new int[] {1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)));
    }
}
