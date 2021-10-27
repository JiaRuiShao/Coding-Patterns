/*
 * Given a string and a number ‘K’, find if the string can be rearranged such that
 * the same characters are at least ‘K’ distance apart from each other.
 *
 * Example 1
 * Input: "mmpp", K=2
 * Output: "mpmp" or "pmpm"
 * Explanation: All same characters are 2 distance apart.
 *
 * Example 2
 * Input: "Programming", K=3
 * Output: "rgmPrgmiano" or "gmringmrPoa" or "gmrPagimnor" and a few more
 * Explanation: All same characters are 3 distance apart.
 *
 * Example 3
 * Input: "aab", K=2
 * Output: "aba"
 * Explanation: All same characters are 2 distance apart.
 *
 * Example 4
 * Input: "aappa", K=3
 * Output: ""
 * Explanation: We cannot find an arrangement of the string where any two 'a' are 3 distance apart.
 *
 * Solution: similar to RearrangeString (with k as 2). In this question, we need a k size queue to store the prev chars
 */
package topknumbers;

import java.util.*;

public class RearrangeStringKDistanceApart {

    /**
     * Time: O(n + dlogd + nlogd) = O(nlogn) where d is the unique char num, and n is the length of the input
     * Space: O(2d + k) = O(d + k)
     *
     * @param str the input String
     * @param k k distance
     * @return the rearranged String if there's any; else return an empty String
     */
    public String reorganizeString(String str, int k) {
        StringBuilder reArranged = new StringBuilder();

        // initialize a HashMap to store the chars in the str and their occurrences
        Map<Character, Integer> freq = new HashMap<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // initialize a maxHeap to store the chars based on their occurrence in descending order
        Queue<Character> maxHeap = new PriorityQueue<>(freq.size(), (a, b) -> Integer.compare(freq.get(b), freq.get(a)));
        for (char c : freq.keySet()) {
            maxHeap.offer(c);
        }

        // create a queue to store the prev k chars
        Queue<Character> prev = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            reArranged.append(c);
            freq.put(c, freq.get(c) - 1);
            prev.offer(c);
            if (prev.size() == k) {
                char temp = prev.poll();
                if (freq.get(temp) > 0) maxHeap.offer(temp);
            }
        }

        return reArranged.length() == str.length() ? reArranged.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("Reorganized string: " +
                new RearrangeStringKDistanceApart().reorganizeString("mmpp", 2));
        System.out.println("Reorganized string: " +
                new RearrangeStringKDistanceApart().reorganizeString("Programming", 3));
        System.out.println("Reorganized string: " +
                new RearrangeStringKDistanceApart().reorganizeString("aab", 2));
        System.out.println("Reorganized string: " +
                new RearrangeStringKDistanceApart().reorganizeString("aappa", 3));
    }

}
