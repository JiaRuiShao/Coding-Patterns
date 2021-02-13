// Given a string, find the length of the longest substring in it
// with no more than K distinct characters.

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubArray {

    /**
     * Sliding Window -- Medium.
     * TC: O(n) -- worst TC: O(2n) => O(n)
     * SC: O(k) -- O(k+1) => O(k)
     * @param str our target string
     * @param k max number of unique chars in our substring
     * @return the
     */
    public int findLargestLengthOfNoMoreThanKDistinctChars(String str, int k) {
        // corner cases:
        if (k == 0 || str == null || str.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        // this hashmap stores the chars are keys & their freq as corresponding values
        int freq;
        int startIdx = 0;
        int maxStrLength = 0;
        for (int endIdx = 0; endIdx < str.length(); endIdx++) {
            freq = map.getOrDefault(str.charAt(endIdx), 0);
            // returns the key value if key exists, else return the default value 0
            map.put(str.charAt(endIdx), freq + 1); // replace the previous value if the key exists
            while (map.size() > k) {
                freq = map.get(str.charAt(startIdx));
                if (freq == 1) {
                    map.remove(str.charAt(startIdx++));
                } else {
                    map.put(str.charAt(startIdx++), freq - 1);
                }
            }
            maxStrLength = Math.max(maxStrLength, endIdx - startIdx + 1);
        }

        return maxStrLength;
    }

    public static void main(String[] args) {
        LongestSubArray sw = new LongestSubArray();
        System.out.println("Length of the longest substring: " + sw.findLargestLengthOfNoMoreThanKDistinctChars("araaci", 2));
        System.out.println("Length of the longest substring: " + sw.findLargestLengthOfNoMoreThanKDistinctChars("araaci", 1));
        System.out.println("Length of the longest substring: " + sw.findLargestLengthOfNoMoreThanKDistinctChars("cbbebi", 3));
    }

}
