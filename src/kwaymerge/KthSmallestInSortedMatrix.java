/*
 * Given an n x n matrix where each of the rows and columns are sorted in ascending order,
 * return the kth smallest element in the matrix.
 *
 * Input: Matrix = [
 *     [1, 3, 7],
 *     [5, 10, 12],
 *     [6, 12, 15]
 *   ],
 *   K=5
 * Output: 7
 */

package kwaymerge;


import java.util.PriorityQueue;
import java.util.Queue;

public class KthSmallestInSortedMatrix {

    private static class MatrixNode {
        int row;
        int col;

        MatrixNode(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Each row as a sorted list.
     * Time: O(nlogn + klogn)
     * Space: O(n)
     *
     * @param matrix the given matrix
     * @param k      the k
     * @return the kth smallest element in the given matrix
     */
    public static int findKthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1 || k < 1) {
            return -1;
        }

        // build a minHeap to store the
        Queue<MatrixNode> minHeap = new PriorityQueue<>(matrix.length, (a, b) -> (Integer.compare(matrix[a.row][a.col], matrix[b.row][b.col])));
        for (int row = 0; row < matrix.length; row++) {
            minHeap.offer(new MatrixNode(row, 0));
        }

        // poll k times
        int count = 0;
        while (!minHeap.isEmpty()) {
            MatrixNode temp = minHeap.poll();
            if (++count == k) {
                return matrix[temp.row][temp.col];
            }
            if (temp.col < matrix[temp.row].length - 1) {
                minHeap.offer(new MatrixNode(temp.row, temp.col + 1));
            }
        }

        return -1; // k > n
    }

    /**
     * Apply the Binary Search on the “number range”
     * Steps:
     * 1) Start the Binary Search with start = matrix[0][0] and end = matrix[n-1][n-1].
     * 2) Find middle of the start and the end. This middle number is NOT necessarily an element in the matrix.
     * 3) Count all the numbers smaller than or equal to middle in the matrix. As the matrix is sorted, we can do this in O(N).O(N).O(N).
     * 4) While counting, we can keep track of the “smallest number greater than the middle” (let’s call it n1) and at the same time the “biggest number less than or equal to the middle” (let’s call it n2). These two numbers will be used to adjust the “number range” for the Binary Search in the next iteration.
     * 5) If the count is equal to ‘K’, n1 will be our required number as it is the “biggest number less than or equal to the middle”, and is definitely present in the matrix.
     * 6) If the count is less than ‘K’, we can update start = n2 to search in the higher part of the matrix and if the count is greater than ‘K’, we can update end = n1 to search in the lower part of the matrix in the next iteration.
     *
     * @param matrix the given matrix
     * @param k      the k
     * @return the kth smallest element in the given matrix
     */
    public static int findKthSmallestImproved(int[][] matrix, int k) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1 || k < 1 || k >= matrix.length * matrix[0].length) {
            return -1;
        }

        int lessThanOrEqualToMid = 0, start = matrix[0][0], end = matrix[matrix.length - 1][matrix[0].length - 1], mid;
        int[] pair = new int[]{matrix[0][0], matrix[matrix.length - 1][matrix[0].length - 1]}; // stores the largestNumLessThanMid, and smallestNumLargerThanMid
        while (true) {
            mid = start + (end - start) / 2;
            lessThanOrEqualToMid = countLessThanOrEqualToMid(matrix, mid, pair);
            if (lessThanOrEqualToMid < k) { // search higher
                start = pair[1];
            } else if (lessThanOrEqualToMid > k) { // search lower
                end = pair[0];
            } else { // lessThanOrEqualToMid == k
                break;
            }
        }
        return pair[0]; // why not working???
    }

    /**
     * A private helper function to count the num that are less than or equal to the middle.
     * Time: O(row + col)
     *
     * @param matrix the given matrix
     * @param mid    the middle
     * @param pair   stores the largest num that is less than or equals to the mid, and the smallest num that is larger than the mid
     * @return the num of elements that are less than or equal to the mid
     */
    private static int countLessThanOrEqualToMid(int[][] matrix, int mid, int[] pair) {
        int count = 0, row = matrix.length - 1, col = 0;
        while (row >= 0 && col < matrix[0].length) {
            if (matrix[row][col] <= mid) { // update the count & the largest num that is less than or equal to the mid
                count += row + 1;
                pair[0] = Math.max(pair[0], matrix[row][col]);
                col++;
            } else { // update the smallest num that is larger than the mid
                pair[1] = Math.min(pair[1], matrix[row][col]);
                row--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {{2, 6, 8}, {3, 7, 10}, {5, 8, 11}};
        int result = KthSmallestInSortedMatrix.findKthSmallest(matrix, 5);
        System.out.printf("%dth smallest number is: %d%n", 5, result);

        result = KthSmallestInSortedMatrix.findKthSmallestImproved(matrix, 5);
        System.out.printf("%dth smallest number is: %d%n", 5, result);
    }
}
