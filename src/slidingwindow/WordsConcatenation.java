// Given a string and a list of words, find all the starting indices of substrings
// in the given string that are a concatenation of all the given words exactly once
// without any overlapping of words. It is given that all words are of the same length.

// Similar to the string anagram problem(practice problem#2),
// just change the chars to Strings instead (we're comparing Strings rather than chars)

package slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordsConcatenation {

    /**
     * Sliding Window -- Practice Problem #4.
     * TC: O(#words in words String + 2n)
     * SC: O(#words in words String arr + n)
     * Notice: substring(startIdx, endIdx), startIdx is inclusive and endIdx is exclusive
     *
     * @param str the input String
     * @param words the String arr
     * @return a list of starting indices of any substring that is
     * the concatenation of elements in the given String arr (if there's any)
     */
    public List<Integer> getIndices(String str, String[] words) {

        int winStart = 0, matched = 0, wordLength = words[0].length(), wordNum = 0;
        List<Integer> startIndices = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        String subStr;

        for (String s : words) {
            map.put(s, map.getOrDefault(s, 0) + 1);
            wordNum++;
        }

        for (int winEnd = wordLength - 1; winEnd < str.length(); winEnd++) {

            subStr = str.substring(winEnd + 1 - wordLength, winEnd + 1);

            if (map.containsKey(subStr)) {
                map.put(subStr, map.get(subStr) - 1);
                if (map.get(subStr) == 0) {
                    matched++;
                }
            }

            if (matched == map.size()) {
                startIndices.add(winStart);
            }

            if (winEnd >= wordNum * wordLength - 1) {
                subStr = str.substring(winStart, winStart + wordLength);
                if (map.containsKey(subStr)) {
                    if (map.get(subStr) == 0) {
                        matched--;
                    }
                    map.put(subStr, map.get(subStr) + 1);
                }
                winStart++;
            }
        }
        return startIndices;
    }

    public static void main(String[] args) {
        WordsConcatenation concat = new WordsConcatenation();
        List<Integer> result = concat.getIndices("catfoxcat", new String[] { "cat", "fox" });
        System.out.println(result);
        result = concat.getIndices("catcatfoxfox", new String[] { "fox", "fox" });
        System.out.println(result);
        result = concat.getIndices("catcatfoxfox", new String[] { "cat", "cat" });
        System.out.println(result);
    }
}
