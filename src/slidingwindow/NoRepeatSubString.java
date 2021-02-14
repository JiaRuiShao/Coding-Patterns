// Given a string, find the length of the longest substring,
// which has no repeating characters.

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class NoRepeatSubString {

    /**
     * Sliding Window -- Hard.
     * The Most important thoughts for this question:
     *  - the value in the map here stores the index instead of the frequencies
     *  - the startIdx is pointed to the larger one between its previous value and the stored index of the repeating char
     *  (if startIdx is already ahead of the last index of endIdx, we'll keep startIdx -- check example "aabbac")
     * TC: O(n)
     * SC: O(k) -- k elements in the input are all unique (if k/n ~ 1, then SC is O(n) else O(1))
     * depends on the propertions that k takes in n.
     * @param str input
     * @return max subarray length that only contains unique characters
     */
    public int findLength(String str) {
        int startIdx = 0, maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int endIdx = 0; endIdx < str.length(); endIdx++) {
            if (map.containsKey(str.charAt(endIdx))) {
                startIdx = Math.max(startIdx, map.get(str.charAt(endIdx)) + 1);
            }
            map.put(str.charAt(endIdx), endIdx);
            maxLength = Math.max(maxLength, endIdx - startIdx + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        NoRepeatSubString sw = new NoRepeatSubString();
        System.out.println("Length of the longest substring: " + sw.findLength("aabbac")); // 3
        System.out.println("Length of the longest substring: " + sw.findLength("aabadef")); // 5
        System.out.println("Length of the longest substring: " + sw.findLength("abccde")); // 3
    }

}
