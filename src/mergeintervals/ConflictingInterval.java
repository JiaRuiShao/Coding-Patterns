// Conflicting Appointments (medium)
// Given an array of intervals representing ‘N’ appointments,
// find out if a person can attend all the appointments.

package mergeintervals;

import java.util.Arrays;

public class ConflictingInterval {

    /**
     * Conflicting Appointments.
     *
     * TC: O(nlogn)
     * SC: O(n)
     * b/c Tim sort uses O(n) space and O(nlogn) time to sort
     *
     * @param intervals the given intervals(appointments)
     * @return true is the intervals is conflicting; false if not
     */
    public boolean isConflict(int[][] intervals) {
        if (intervals == null || intervals.length < 2) return false;
        /*Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] interval1, int[] interval2) {
                return Integer.compare(interval1[0], interval2[0]);
            }
        });*/
        Arrays.sort(intervals, (interval1, interval2) -> Integer.compare(interval1[0], interval2[0]));

        for (int index = 1; index < intervals.length; index++) {
            if (intervals[index - 1][1] > intervals[index][0]) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        ConflictingInterval conflictInterval = new ConflictingInterval();
        System.out.print(Arrays.deepToString(new int[][]{{1, 4}, {2, 5}, {7, 9}}));
        System.out.println(": "+ conflictInterval.isConflict(new int[][]{{1, 4}, {2, 5}, {7, 9}}));
        System.out.print(Arrays.deepToString(new int[][]{{6, 7}, {2, 4}, {8, 12}}));
        System.out.println(": "+ conflictInterval.isConflict(new int[][]{{6, 7}, {2, 4}, {8, 12}}));
        System.out.print(Arrays.deepToString(new int[][]{{4, 5}, {2, 3}, {3, 6}}));
        System.out.println(": "+ conflictInterval.isConflict(new int[][]{{4, 5}, {2, 3}, {3, 6}}));
        System.out.print(Arrays.deepToString(new int[][]{{4, 5}}));
        System.out.println(": "+ conflictInterval.isConflict(new int[][]{{4, 5}}));
    }
}
