// Sliding Window -- Practice Problem #2
// Given a string and a pattern, find all anagrams of the pattern in the given string.
// Anagram is actually a Permutation of a string.
// For example, “abc” has the following six anagrams:
// abc
// acb
// bac
// bca
// cab
// cba
// Write a function to return a list of starting indices of the anagrams
// of the pattern in the given string.

package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringAnagrams {

    /**
     * Sliding Window -- Practice Problem #2.
     * TC: O(m + n) -> O(n) if m << n, where m is the char# in pattern and n is the char# in input string
     * SC: O(#unique chars in pattern + n) -> O(n) b/c unique m <= n
     *
     * @param str the input string
     * @param pattern the pattern of anagram
     * @return a list of starting indices of anagram of the pattern in the input string (if there's any)
     */
    public List<Integer> findIndices(String str, String pattern) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> myIndices = new ArrayList<>(); // store the starting indices
        int matched = 0, winStart = 0;

        // add characters in the pattern into the map; store unique characters and their frequencies
        for (Character c : pattern.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        // look through the input string to find the matching anagram
        for (int winEnd = 0; winEnd < str.length(); winEnd++) {
            // check whether the leftmost character can be a part of the anagram
            if (map.containsKey(str.charAt(winEnd))) {
                map.put(str.charAt(winEnd), map.get(str.charAt(winEnd)) - 1); // update the freq of the rightmost character
                if (map.get(str.charAt(winEnd)) == 0) {
                    matched++;
                }
            }

            // check whether the characters from winStart to winEnd is an anagram of the given pattern
            if (matched == map.size()) {
                myIndices.add(winStart);
            }

            // check whether the rightmost character is part of the anagram
            if (winEnd >= pattern.length() - 1) {
                if (map.containsKey(str.charAt(winStart))) {
                    if (map.get(str.charAt(winStart)) == 0) {
                        matched--; // update the number of characters matched
                    }
                    map.put(str.charAt(winStart), map.get(str.charAt(winStart)) + 1); // put the char back in the freq map
                }
                winStart++;
            }

        }

        return myIndices;
    }

    public static void main(String[] args) {
        StringAnagrams anagrams = new StringAnagrams();
        System.out.println(anagrams.findIndices("ppqp", "pq"));
        System.out.println(anagrams.findIndices("abbcabc", "abc"));
    }

}
