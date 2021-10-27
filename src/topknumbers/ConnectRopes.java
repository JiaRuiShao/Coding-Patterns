/*
 * Given ‘N’ ropes with different lengths, we need to connect these ropes into one big rope with minimum cost.
 * The cost of connecting two ropes is equal to the sum of their lengths.
 * =======================================================================================================
 * Input: [1, 3, 11, 5]
 * Output: 33
 * Explanation: First connect 1+3(=4), then 4+5(=9), and then 9+11(=20). So the total cost is 33 (4+9+20)
 *
 * Input: [3, 4, 5, 6]
 * Output: 36
 * Explanation: First connect 3+4(=7), then 5+6(=11), 7+11(=18). Total cost is 36 (7+11+18)
 *
 * Input: [1, 3, 11, 5, 2]
 * Output: 42
 * Explanation: First connect 1+2(=3), then 3+3(=6), 6+5(=11), 11+11(=22). Total cost is 42 (3+6+11+22)
 */
package topknumbers;

import java.util.PriorityQueue;
import java.util.Queue;

public class ConnectRopes {

    /**
     * Get the min cost to connect ropes.
     * Time: O(nlogn)
     * Space: O(n)
     *
     * @param ropeLengths the input ropes
     * @return the min cost to connect the ropes
     */
    public static int minimumCostToConnectRopes(int[] ropeLengths) {
        if (ropeLengths == null || ropeLengths.length == 0) return -1;
        int cost = 0, length = 0;

        Queue<Integer> minHeap = new PriorityQueue<>(ropeLengths.length);
        for (int len : ropeLengths) {
            minHeap.offer(len);
        }

        while (minHeap.size() > 1) {
            length = minHeap.poll() + minHeap.poll();
            cost += length;
            minHeap.offer(length);
        }

        return cost;
    }

    public static void main(String[] args) {
        int result = ConnectRopes.minimumCostToConnectRopes(new int[]{1, 3, 11, 5});
        System.out.println("Minimum cost to connect ropes: " + result);
        result = ConnectRopes.minimumCostToConnectRopes(new int[]{3, 4, 5, 6});
        System.out.println("Minimum cost to connect ropes: " + result);
        result = ConnectRopes.minimumCostToConnectRopes(new int[]{1, 3, 11, 5, 2});
        System.out.println("Minimum cost to connect ropes: " + result);
    }

}
