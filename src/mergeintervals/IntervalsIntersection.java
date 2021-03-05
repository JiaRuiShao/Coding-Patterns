// Intervals Intersection (medium)
// Given two lists of intervals, find the intersection of these two lists.
// Each list consists of disjoint intervals sorted on their start time.

package mergeintervals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntervalsIntersection {

    /**
     * Intervals Intersection -- Medium.
     *
     * TC: O(n)
     * SC: O(n)
     *
     * @param arr1 1st given interval
     * @param arr2 2nd given interval
     * @return the intersection of two given intervals
     */
    public int[][] intersect(int[][] arr1, int[][] arr2) {
        // check corner case (notice that you MUST specify the 1st dimension)
        if (arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0) return new int[][]{};

        List<int[]> result = new LinkedList<>();
        int i = 0, j = 0; // index for arr1 & arr2
        int start, end;

        while (i < arr1.length && j < arr2.length) {
            // if there's intersection
            start = Math.max(arr1[i][0], arr2[j][0]);
            end = Math.min(arr1[i][1], arr2[j][1]);
            if (start <= end) result.add(new int[]{start, end});

            if (arr1[i][1] < arr2[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        IntervalsIntersection intersect = new IntervalsIntersection();
        System.out.println(Arrays.deepToString(intersect.intersect(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}})));
        System.out.println(Arrays.deepToString(intersect.intersect(new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}},
                new int[][]{})));
    }
}
