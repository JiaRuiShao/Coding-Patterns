// Smallest Window containing Substring (hard)
// Given a string and a pattern, find the smallest substring
// in the given string which has all the characters of the given pattern.

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class SmallestWindowContainingSubstring {

    /**
     * Sliding Window -- Practice Problem #3.
     * TC: O(m + n) where m is the character# in pattern
     * SC: O(1 + n) O(1) for map b/c unique char# <= 26 in pattern; O(n) for str.substring() in worst case
     *
     * @param str the input string
     * @param pattern pattern string
     * @return the smallest substring that contains all characters of the given pattern
     */
    public String findString(String str, String pattern) {

        int startIdx = 0, minLength = str.length(), matched = 0, winStart = 0;
        Map<Character, Integer> map = new HashMap<>();

        // store the chars in pattern as keys and their frequencies as value in hashmap
        for (Character c : pattern.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // loop through the input string
        for (int winEnd = 0; winEnd < str.length(); winEnd++) {

            if (map.containsKey(str.charAt(winEnd))) {
                map.put(str.charAt(winEnd), map.get(str.charAt(winEnd)) - 1);
                if (map.get(str.charAt(winEnd)) == 0) {
                    matched++;
                }
            }

            // keep shrinking the window as long as the window contains pattern anagram
            while (matched == map.size() && winStart < winEnd) {
                if (!(map.containsKey(str.charAt(winStart)))) {
                    winStart++;
                } else if (map.get(str.charAt(winStart)) < 0) {
                    map.put(str.charAt(winStart), map.get(str.charAt(winStart)) + 1);
                    winStart++;
                } else {
                    if (minLength > winEnd - winStart + 1) {
                        startIdx = winStart;
                        minLength = winEnd - winStart + 1;
                    }
                    break;
                }
            }
        }

        return matched < map.size() ? "" : str.substring(startIdx, startIdx + minLength);
        // notice that starting index is inclusive and ending index is exclusive in substring() function
    }

    public static void main(String[] args) {
        SmallestWindowContainingSubstring smallestWindow = new SmallestWindowContainingSubstring();
        System.out.println(smallestWindow.findString("aabdec", "abc"));
        System.out.println(smallestWindow.findString("abdbca", "abc"));
        System.out.println(smallestWindow.findString("adcad", "abc"));
    }
}
