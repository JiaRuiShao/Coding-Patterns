// Given a string with lowercase letters only, if you are allowed to
// replace no more than ‘k’ letters with any letter, find the length
// of the longest substring having the same letters after replacement.

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithSameLettersAfterReplacement {

    /**
     * Sliding Window -- Hard.
     * Notice the difference between this question and
     * the longest substring with k distinct characters one:
     * think about this case : substring = "aabbbccb", k = 2;
     * in this question, the replacement letters could be "cc" and
     * the max substring is "bbbbbb" (transformed from "bbbccb");
     * however, in the distinct k characters question,
     * the max substring is "aabbbccb".
     *
     * TC: O(n)
     * SC: O(1)
     * Notice here the SC is not O(n) b/c the max letters we can store is 26, and O(26) -> O(1)
     *
     * @param str the input string
     * @param k   the max num of letters that we can change
     * @return the max length of the transformed substring
     */
    public int findLength(String str, int k) {

        int winStart = 0, maxFreq = 0, maxLength = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int winEnd = 0; winEnd < str.length(); winEnd++) {
            map.put(str.charAt(winEnd), map.getOrDefault(str.charAt(winEnd), 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(str.charAt(winEnd)));
            if (winEnd - winStart + 1 > k + maxFreq) {
                map.put(str.charAt(winStart), map.get(str.charAt(winStart)) - 1);
                winStart++;
            }
            maxLength = Math.max(maxLength, winEnd - winStart + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithSameLettersAfterReplacement sw = new LongestSubstringWithSameLettersAfterReplacement();
        System.out.println(sw.findLength("aabccbb", 2));
        System.out.println(sw.findLength("abbcb", 1));
        System.out.println(sw.findLength("abccde", 1));
    }

}
