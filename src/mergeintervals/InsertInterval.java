// Insert Interval (medium)
// Given a set of non-overlapping intervals, insert a new interval into the intervals
// (merge if necessary). You may assume that the intervals were initially sorted
// according to their start times.

package mergeintervals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class InsertInterval {

    /**
     * Insert Intervals -- Medium.
     *
     * TC: O(n)
     * SC: O(n)
     *
     * @param intervals the given nested int[][] array
     * @param newInterval added interval
     * @return the intervals after merged
     */
    public int[][] insertInterval(int[][] intervals, int[] newInterval) {

        List<int[]> solution = new LinkedList<>();

        if (intervals == null) {
            solution.add(newInterval);
            return solution.toArray(new int[solution.size()][]);
        }

        int index = 0;

        // when interval[1] < newInterval[0] || interval[0] > newInterval[1] there is no overlapping
        // add all the intervals whose end is < the start of the newInterval
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            solution.add(intervals[index++]);
        }
        // merge all the intervals whose end is >= the start of the newInterval and start is <= the start of newInterval
        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[index][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[index][1], newInterval[1]);
            index++;
        }
        // add the newInterval
        solution.add(newInterval);
        // add the rest intervals whose start is > start of newInterval
        while (index < intervals.length) {
            solution.add(intervals[index++]);
        }

        return solution.toArray(new int[solution.size()][]);
    }

    public static void main(String[] args) {
        InsertInterval insert = new InsertInterval();
        System.out.println(Arrays.deepToString(insert.insertInterval(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8})));
        System.out.println(Arrays.deepToString(insert.insertInterval(new int[][]{{3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{1, 2})));
        System.out.println(Arrays.deepToString(insert.insertInterval(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}}, new int[]{12, 16})));
        System.out.println(Arrays.deepToString(insert.insertInterval(new int[][]{{1, 2}}, new int[]{12, 16})));
        System.out.println(Arrays.deepToString(insert.insertInterval(new int[][]{{12, 16}}, new int[]{1, 2})));
        System.out.println(Arrays.deepToString(insert.insertInterval(new int[][]{}, new int[]{1, 2})));
    }
}
