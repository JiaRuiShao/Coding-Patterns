/*
 * You are given a list of tasks that need to be run, in any order, on a server.
 * Each task will take one CPU interval to execute but once a task has finished,
 * it has a cooling period during which it can’t be run again.
 * If the cooling period for all tasks is ‘K’ intervals,
 * find the minimum number of CPU intervals that the server needs to finish all tasks.
 * If at any time the server can’t execute any task then it must stay idle.
 *
 * Example 1:
 * Input: [a, a, a, b, c, c], K=2
 * Output: 7
 * Explanation: a -> c -> b -> a -> c -> idle -> a
 *
 * Example 2:
 * Input: [a, b, a], K=3
 * Output: 5
 * Explanation: a -> b -> idle -> idle -> a
 *
 * ============================================================================================================
 * Solution:
 * This problem follows the Top ‘K’ Elements pattern and is quite similar to
 * Rearrange String K Distance Apart. We need to rearrange tasks such that same tasks are ‘K’ distance apart.
 *
 * Following a similar approach, we will use a Max Heap to execute the highest frequency task first.
 * After executing a task we decrease its frequency and put it in a waiting list.
 * In each iteration, we will try to execute as many as k+1 tasks.
 * For the next iteration, we will put all the waiting tasks back in the Max Heap.
 * If, for any iteration, we are not able to execute k+1 tasks,
 * the CPU has to remain idle for the remaining time in the next iteration.
 */

package topknumbers;

import java.util.*;

public class TaskScheduler {

    /**
     * Notice here the distance between the same task is k+1.
     * Time: O(n + dlogd + nlogd + d) = O(nlogd) where d is the unique number of tasks in the given tasks arr
     * Space: O(3d) = O(d)
     *
     * @param tasks the given tasks as a char arr
     * @param k     k cooling period
     * @return the time for the CPU to run all the given tasks
     */
    public int scheduleTasks(char[] tasks, int k) {
        int time = 0;
        // initialize a Hashmap to store the tasks and their occurrence
        Map<Character, Integer> freq = new HashMap<>(tasks.length);
        for (char task : tasks) {
            freq.put(task, freq.getOrDefault(task, 0) + 1);
        }
        // initialize a maxHeap to store the tasks in the descending order of their occurrence
        Queue<Character> maxHeap = new PriorityQueue<>(freq.size(), (a, b) -> Integer.compare(freq.get(b), freq.get(a)));
        for (char task : freq.keySet()) {
            maxHeap.offer(task);
        }
        // create a wait list queue for the tasks
        System.out.println("count|char|freq");
        Queue<Character> wl = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            int count = 1;
            // poll distance(k + 1) times
            for (; count <= k + 1 && !maxHeap.isEmpty(); count++) {
                char c = maxHeap.poll();
                time++;
                freq.put(c, freq.get(c) - 1);
                if (freq.get(c) > 0) wl.offer(c);
                System.out.println(count + " " + (char) c + " " + freq.get(c));
            }
            // if there's remaining tasks needed to be done && maxHeap.size() < k + 1, add time by k + 1 - count
            if (!wl.isEmpty()) {
                time += k + 1 - count + 1;
                System.out.println(k + 1 - (count - 1));
            }
            // add all remaining tasks back into the maxHeap
            maxHeap.addAll(wl);
            wl.clear();
        }
        return time;
    }

    public static void main(String[] args) {
        char[] tasks = new char[]{'a', 'a', 'a', 'b', 'c', 'c'};
        System.out.println("Minimum intervals needed to execute all tasks: " + new TaskScheduler().scheduleTasks(tasks, 2));

        tasks = new char[]{'a', 'b', 'a'};
        System.out.println("Minimum intervals needed to execute all tasks: " + new TaskScheduler().scheduleTasks(tasks, 3));
    }
}
