/*
 * Given a string, find if its letters can be rearranged in such a way that no two same characters come next to each other.
 * Solution:
 * - initialize a hashmap to store the chars and their frequencies
 * - then use a maxHeap to store the chars based on their occurrence (descending order -- from largest to smallest)
 * - competitively polling out the char with the largest frequency and append it to the result str, update the prev char
 * - if the prev char is different than the curr char and its occurrence is larger than 0, poll it into the maxHeap
 * - continue doing this until the maxHeap is empty
 * - check whether the result str has the same length as the input str
 */

package topknumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class RearrangeString {

    /**
     * Time: O(n + dlogd + nlogd) = O(nlogd) where d is the unique char num, and n is the length of the input
     * Space: O(2d) = O(d)
     *
     * @param str the input String
     * @return the rearranged String if there's any; else return an empty String
     */
    public String rearrangeString(String str) {
        StringBuilder rearranged = new StringBuilder();

        // initialize a hashmap to store the chars and their freq
        Map<Character, Integer> freq = new HashMap<>(str.length());
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // initialize a maxHeap to store the unique chars based on their freq (desc)
        Queue<Character> maxHeap = new PriorityQueue<>(freq.size(), (a, b) -> Integer.compare(freq.get(b), freq.get(a)));
        for (char c : freq.keySet()) {
            maxHeap.offer(c);
        }

        // create the new string
        char prev = '\0', temp;
        while (!maxHeap.isEmpty()) {
            temp = maxHeap.poll();
            freq.put(temp, freq.get(temp) - 1);
            rearranged.append(temp);
            if (prev != '\0' && prev != temp && freq.get(prev) > 0) {
                maxHeap.offer(prev);
            }
            prev = temp;
        }

        return rearranged.length() == str.length() ? rearranged.toString() : "";
    }

    public static void main(String[] args) {
        System.out.println("Rearranged string: " + new RearrangeString().rearrangeString("aappp"));
        System.out.println("Rearranged string: " + new RearrangeString().rearrangeString("Programming"));
        System.out.println("Rearranged string: " + new RearrangeString().rearrangeString("aapa"));
    }
}
