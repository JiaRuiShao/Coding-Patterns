// Minimum Meeting Rooms (hard)
// Given a list of intervals representing the start and end time of ‘N’ meetings,
// find the minimum number of rooms required to hold all the meetings.

package mergeintervals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FindingMinMeetingRoom {

    /**
     * Find the min meeting room# -- Practice Problem (Hard).
     * Hint: use minHeap to record the ending time
     *
     * TC: O(nlogn + nlogn) sorting takes O(nlogn) time and for loop takes O(nlogn)
     * in that offer(E) and poll() takes O(logn) time in teh worst case
     * SC: O(n) bc there are n elements to store in the minHeap in the worst case
     *
     * @param intervals the given intervals
     * @return the min meeting room#
     */
    public int findMinMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        // minHeap to store the end time of the meeting (lowest end time interval is at the front)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int minRoom = 1;

        for (int[] interval : intervals) {
            if (!minHeap.isEmpty() && interval[0] >= minHeap.peek()[1]) minHeap.poll();
            minHeap.offer(interval);
            minRoom = Math.max(minRoom, minHeap.size());
        }
        return minRoom;
    }

    /**
     * Chronological Ordering -- treat start and end time separately.
     *
     * TC: O(nlogn)
     * SC: O(n)
     *
     * @param intervals the given intervals
     * @return the min meeting room#
     */
    public int findMinRoom(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Integer[] start = new Integer[intervals.length], end = new Integer[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start, (a, b) -> a - b);
        Arrays.sort(end, (a, b) -> a - b);
        int startPointer = 0, endPointer = 0, usedRoom = 0;
        while (startPointer < intervals.length) {
            if (start[startPointer] >= end[endPointer]) {
                endPointer++;
                usedRoom--;
            }
            usedRoom++;
            startPointer++;
        }
        return usedRoom;
    }

    public static void main(String[] args) {
        FindingMinMeetingRoom minRoom = new FindingMinMeetingRoom();
        System.out.printf("The min room needed for %s is %d %n",
                Arrays.deepToString(new int[][]{{0,30},{5,10},{15,20}}),
                minRoom.findMinMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        System.out.printf("The min room needed for %s is %d %n",
                Arrays.deepToString(new int[][]{{7,10},{2,4}}),
                minRoom.findMinMeetingRooms(new int[][]{{7,10},{2,4}}));
    }
}
