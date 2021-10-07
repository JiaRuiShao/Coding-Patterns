/*
 * Given a sequence originalSeq and an array of sequences, write a method to find
 * if originalSeq can be uniquely reconstructed from the array of sequences.
 *
 * Unique reconstruction means that we need to find if originalSeq is the only sequence
 * such that all sequences in the array are subsequences of it.
 *
 * Input: originalSeq: [1, 2, 3, 4], seqs: [[1, 2], [2, 3], [3, 4]]
 * Output: true
 * Explanation: The sequences [1, 2], [2, 3], and [3, 4] can uniquely reconstruct
 * [1, 2, 3, 4], in other words, all the given sequences uniquely define the order of numbers
 * in the 'originalSeq'.
 * ===============================================================================================
 * Input: originalSeq: [1, 2, 3, 4], seqs: [[1, 2], [2, 3], [2, 4]]
 * Output: false
 * Explanation: The sequences [1, 2], [2, 3], and [2, 4] cannot uniquely reconstruct
 * [1, 2, 3, 4]. There are two possible sequences we can construct from the given sequences:
 *  1) [1, 2, 3, 4]
 *  2) [1, 2, 4, 3]
 * ===============================================================================================
 * Input: originalSeq: [3, 1, 4, 2, 5], seqs: [[3, 1, 5], [1, 4, 2, 5]]
 * Output: true
 * Explanation: The sequences [3, 1, 5] and [1, 4, 2, 5] can uniquely reconstruct
 * [3, 1, 4, 2, 5].
 *
 */

package topologicalsort;

import java.util.*;

public class SequenceReconstruction {
    public static boolean canConstruct(int[] originalSeq, int[][] sequences) {
        if (originalSeq == null || originalSeq.length == 0 || sequences == null || sequences.length == 0) {
            return false;
        }

        Map<Integer, List<Integer>> adjacentNum = new HashMap<>();
        Map<Integer, Integer> numFreq = new HashMap<>();

        for (int num : originalSeq) {
            adjacentNum.put(num, new LinkedList<Integer>());
            numFreq.put(num, 0);
        }

        for (int[] seq : sequences) {
            for (int i = 0; i < seq.length - 1; i++) {
                adjacentNum.get(seq[i]).add(seq[i + 1]);
                numFreq.put(seq[i + 1], numFreq.get(seq[i + 1]) + 1);
            }
        }

        Queue<Integer> priorNum = new LinkedList<>();
        for (int num : numFreq.keySet()) {
            if (numFreq.get(num) == 0) {
                priorNum.offer(num);
            }
        }

        while (!priorNum.isEmpty()) {
            if (priorNum.size() > 1) { // return false if there're two possible next sources to choose from
                return false;
            }
            int temp = priorNum.poll();
            for (int num : adjacentNum.get(temp)) {
                numFreq.put(num, numFreq.get(num) - 1);
                if (numFreq.get(num) == 0) {
                    priorNum.offer(num);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        boolean result = SequenceReconstruction.canConstruct(new int[]{1, 2, 3, 4},
                new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 4}});
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[]{1, 2, 3, 4},
                new int[][]{new int[]{1, 2}, new int[]{2, 3}, new int[]{2, 4}});
        System.out.println("Can we uniquely construct the sequence: " + result);

        result = SequenceReconstruction.canConstruct(new int[]{3, 1, 4, 2, 5},
                new int[][]{new int[]{3, 1, 5}, new int[]{1, 4, 2, 5}});
        System.out.println("Can we uniquely construct the sequence: " + result);
    }
}
