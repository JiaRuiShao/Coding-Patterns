/*
 * Given a string, sort it based on the decreasing frequency of its characters.
 * =======================================================================================
 * Input: "Programming"
 * Output: "rrggmmPiano"
 * Explanation: 'r', 'g', and 'm' appeared twice, so they need to appear before any other character.
 *
 * Input: "abcbab"
 * Output: "bbbaac"
 * Explanation: 'b' appeared three times, 'a' appeared twice, and 'c' appeared only once.
 */
package topknumbers;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

public class FrequencySort {

    /**
     * Time: O(n + 2nlogn)
     * Space: O(2n)
     *
     * @param str the given String
     * @return the sorted String based on the decreasing frequency of its characters
     */
    public static String sortCharacterByFrequency(String str) {
        StringBuilder sb = new StringBuilder();
        Map<Character, Integer> freq = new TreeMap<>();
        for (int i = 0; i < str.length(); i++) {
            freq.put(str.charAt(i), freq.getOrDefault(str.charAt(i), 0) + 1);
        }

        Queue<Character> maxHeap = new PriorityQueue<>(freq.size(), (a, b) -> Integer.compare(freq.get(b), freq.get(a)));
        maxHeap.addAll(freq.keySet());

        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            while (freq.get(c) > 0) {
                sb.append(c);
                freq.put(c, freq.get(c) - 1);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String result = FrequencySort.sortCharacterByFrequency("Programming");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);

        result = FrequencySort.sortCharacterByFrequency("abcbab");
        System.out.println("Here is the given string after sorting characters by frequency: " + result);
    }

}
