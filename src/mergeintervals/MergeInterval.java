// Merge Intervals
// Given a list of intervals, merge all the overlapping intervals to produce
// a list that has only mutually exclusive intervals.

// Intervals: [[1,4], [2,5], [7,9]]
// Output: [[1,5], [7,9]]
// Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into one [1,5].

package mergeintervals;

import java.util.Arrays;
import java.util.LinkedList;

public class MergeInterval {

    /**
     * Merge Interval -- Medium.
     *
     * TC: O(nlogn)
     * SC: O(n)
     * Arrays.sort() use Dual-Pivot QuickSort for primitive type;
     * use Timsort (a hybrid of MergeSort and InsertionSort) for object type
     *
     * @param intervals the given list of int[] arr
     * @return an array of merged intervals
     */
    public int[][] merge(int[][] intervals) {

        // sort the int[][] array based on the first element in each inner array
        Arrays.sort(intervals, (a1, a2) -> Integer.compare(a1[0], a2[0])); // lambda version
        /*Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });*/

        LinkedList<int[]> mergedIntervals = new LinkedList<>();

        for (int[] interval : intervals) {
            if (mergedIntervals.isEmpty() || mergedIntervals.getLast()[1] < interval[0]) mergedIntervals.add(interval);
            mergedIntervals.getLast()[1] = Math.max(mergedIntervals.getLast()[1], interval[1]);
        }

        return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
    }

    public static void main(String[] args) {
        MergeInterval mi = new MergeInterval();
        System.out.println(Arrays.deepToString(mi.merge(new int[][] {{1,9},{2,5},{19,20},{10,11},{12,20},{0,3},{0,1},{0,2}})));
    }

}
