// Given a string and a pattern, find out if the string contains any permutation of the pattern.
// Permutation is defined as the re-arranging of the characters of the string.
// For example, “abc” has the following six permutations:
// abc
// acb
// bac
// bca
// cab
// cba
// If a string has ‘n’ distinct characters, it will have n!n! permutations.

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class CheckPermutation {
    /**
     * Sliding Window -- Practice Problem #1.
     * TC: O(pattern + n * (n - pattern)) -> O(n^2) if patterns << n
     * SC: O(2 * pattern) -> O(1) if pattern << n
     *
     * @param str the input string
     * @param pattern pattern string
     * @return true if the input string contains the pattern; false if not
     */
    public boolean findPermutation(String str, String pattern) {
        if (str == null || str.length() < pattern.length()) {
            return false;
        }

        Map<Character, Integer> pat = new HashMap<>();
        for (Character c : pattern.toCharArray()) {
            pat.put(c, pat.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int right = 0; right < str.length(); right++) {
            map.put(str.charAt(right), map.getOrDefault(str.charAt(right), 0) + 1);
            if (right - left + 1 >= pattern.length()) {
                if (pat.equals(map)) { // TC: O(n) * (n-pattern)
                    return true;
                } else {
                    if (map.get(str.charAt(left)) == 1) {
                        map.remove(str.charAt(left));
                    } else {
                        map.put(str.charAt(left), map.get(str.charAt(left)) - 1);
                    }
                    left++;
                }
            }
        }
        return false;
    }

    /**
     * Sliding window -- improved TC & SC
     * TC: O(n + pattern) -> O(n) if pattern << n
     * SC: O(pattern) -> O(1) if pattern << n
     *
     * @param str the input string
     * @param pattern pattern string
     * @return true if the input string contains the pattern; false if not
     */
    public boolean findPermutation2(String str, String pattern) {
        int windowStart = 0, matched = 0;
        Map<Character, Integer> charFrequencyMap = new HashMap<>();
        for (char chr : pattern.toCharArray())
            charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

        // our goal is to match all the characters from the 'charFrequencyMap' with the current window
        // try to extend the range [windowStart, windowEnd]
        for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char rightChar = str.charAt(windowEnd);
            if (charFrequencyMap.containsKey(rightChar)) {
                // decrement the frequency of the matched character
                charFrequencyMap.put(rightChar, charFrequencyMap.get(rightChar) - 1);
                if (charFrequencyMap.get(rightChar) == 0) // character is completely matched
                    matched++;
            }

            if (matched == charFrequencyMap.size())
                return true;

            if (windowEnd >= pattern.length() - 1) { // shrink the window by one character
                char leftChar = str.charAt(windowStart++);
                if (charFrequencyMap.containsKey(leftChar)) {
                    if (charFrequencyMap.get(leftChar) == 0)
                        matched--; // before putting the character back, decrement the matched count
                    // put the character back for matching
                    charFrequencyMap.put(leftChar, charFrequencyMap.get(leftChar) + 1);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        CheckPermutation permutation = new CheckPermutation();
        System.out.println("Permutation exist: " + permutation.findPermutation("oidbcaf", "abc"));
        System.out.println("Permutation exist: " + permutation.findPermutation("odicf", "dc"));
        System.out.println("Permutation exist: " + permutation.findPermutation("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Permutation exist: " + permutation.findPermutation("aaacb", "abc"));

        System.out.println("Permutation exist: " + permutation.findPermutation2("oidbcaf", "abc"));
        System.out.println("Permutation exist: " + permutation.findPermutation2("odicf", "dc"));
        System.out.println("Permutation exist: " + permutation.findPermutation2("bcdxabcdy", "bcdyabcdx"));
        System.out.println("Permutation exist: " + permutation.findPermutation2("aaacb", "abc"));
    }
}
