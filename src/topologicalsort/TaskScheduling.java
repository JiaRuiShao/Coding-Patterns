/*
 * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’.
 * Each task can have some prerequisite tasks which need to be completed before it can be scheduled.
 * Given the number of tasks and a list of prerequisite pairs, find out if it is possible to schedule all the tasks.
 *
 * Input: Tasks=6, Prerequisites=[2, 5], [0, 5], [0, 4], [1, 4], [3, 2], [1, 3]
 * Output: true
 * Explanation: A possible scheduling of tasks is: [0 1 4 3 2 5]
 * =============================================================================================
 * Similar problem: Find if a given Directed Graph has a cycle in it or not.
 *
 * Solution: If we can’t determine the topological ordering of all the vertices of a directed graph,
 * the graph has a cycle in it. This was also referred to in the above code:
 */

package topologicalsort;

import java.util.*;

public class TaskScheduling {

    /**
     * Time: O(n + m) + o(n) + O(m) = O(n + m) where n is the task num, and m is the prerequisite num
     * Space: O(n + m)
     *
     * @param tasks the num of tasks
     * @param prerequisites the prerequisites
     * @return whether is possible to schedule all tasks that conform to the given prerequisite
     */
    public static boolean isSchedulingPossible(int tasks, int[][] prerequisites) {

        // 0 - corner case
        if (tasks == 0 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // 1 - initialize two hashmaps for storing the prerequisites and each task's freq in requiring other tasks
        Map<Integer, List<Integer>> taskPre = new HashMap<>();
        Map<Integer, Integer> taskFreq = new HashMap<>();
        List<Integer> taskOrder = new LinkedList<>();

        for (int task = 0; task < tasks; task++) {
            taskPre.put(task, new LinkedList<>());
            taskFreq.put(task, 0);
        }

        for (int[] pre : prerequisites) {
            taskPre.get(pre[0]).add(pre[1]);
            taskFreq.put(pre[1], taskFreq.get(pre[1]) + 1);
        }

        // 2 - find the tasks that doesn't have any prerequisite
        Queue<Integer> sourceTasks = new LinkedList<>();
        for (int task : taskFreq.keySet()) {
            if (taskFreq.get(task) == 0) {
                sourceTasks.offer(task);
            }
        }

        // 3 - update the freq map and find new source tasks
        int task;
        while (!sourceTasks.isEmpty()) {
            task = sourceTasks.poll();
            taskOrder.add(task);
            for (Integer t : taskPre.get(task)) {
                taskFreq.put(t, taskFreq.get(t) - 1);
                if (taskFreq.get(t) == 0) {
                    sourceTasks.offer(t);
                }
            }
        }

        return taskOrder.size() == tasks;
    }

    public static void main(String[] args) {

        boolean result = TaskScheduling.isSchedulingPossible(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println("Tasks execution possible: " + result);

        result = TaskScheduling.isSchedulingPossible(3,
                new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println("Tasks execution possible: " + result);

        result = TaskScheduling.isSchedulingPossible(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 },
                new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println("Tasks execution possible: " + result);
    }
}
