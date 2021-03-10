// Maximum CPU Load (hard)
// We are given a list of Jobs. Each job has a Start time, an End time, and a CPU load when it is running.
// Our goal is to find the maximum CPU load at any time if all the jobs are running on the same machine.

package mergeintervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxCPULoad {

    /**
     * Merge Intervals -- Practice Problem.
     *
     * TC: O(nlogn)
     * SC: O(n)
     *
     * @param jobs given jobs
     * @return the max CPU load
     */
    public int findMaxCPU(int[][] jobs) {
        if (jobs == null || jobs.length == 0) return 0;

        int maxCPU = 0, currentCPU = 0;
        Arrays.sort(jobs, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        for (int[] job : jobs) {
            while (!minHeap.isEmpty() && job[0] >= minHeap.peek()[1]) {
                currentCPU -= minHeap.poll()[2];
            }
            minHeap.offer(job);
            currentCPU += job[2];
            maxCPU = Math.max(maxCPU, currentCPU);
        }
        return maxCPU;
    }

    public static void main(String[] args) {
        MaxCPULoad cpu = new MaxCPULoad();
        System.out.printf("The max CPU needed for %s is %d %n",
                Arrays.deepToString(new int[][]{{1, 4, 3}, {2, 5, 4}, {7, 9, 6}}),
                cpu.findMaxCPU(new int[][]{{1, 4, 3}, {2, 5, 4}, {7, 9, 6}}));
        System.out.printf("The max CPU needed for %s is %d %n",
                Arrays.deepToString(new int[][]{{6, 7, 10}, {2, 4, 11}, {8, 12, 15}}),
                cpu.findMaxCPU(new int[][]{{6, 7, 10}, {2, 4, 11}, {8, 12, 15}}));
        System.out.printf("The max CPU needed for %s is %d %n",
                Arrays.deepToString(new int[][]{{1, 4, 2}, {2, 4, 1}, {3, 6, 5}}),
                cpu.findMaxCPU(new int[][]{{1, 4, 2}, {2, 4, 1}, {3, 6, 5}}));
    }
}
