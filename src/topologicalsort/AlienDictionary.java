/**
 * There is a dictionary containing words from an alien language
 * for which we don’t know the ordering of the alphabets.
 *
 * Write a method to find the correct order of the alphabets in the alien language.
 * It is given that the input is a valid dictionary and there exists an ordering among its alphabets.
 *
 * =============================== Example 1 ====================================
 * Input: Words: ["cab", "aaa", "aab"]
 * Output: cab
 * Explanation: From the given words we can conclude the following ordering among its characters:
 * 1. From "cab" and "aaa", we can conclude that 'c' comes before 'a'.
 * 2. From "aaa" and "aab", we can conclude that 'a' comes before 'b'
 *
 * From the above two points, we can conclude that the correct character order is: "cab"
 * =============================== Example 2 ====================================
 * Input: Words: ["ywx", "wz", "xww", "xz", "zyy", "zwz"]
 * Output: ywxz
 * Explanation: From the given words we can conclude the following ordering among its characters:
 * 1. From "ywx" and "wz", we can conclude that 'y' comes before 'w'.
 * 2. From "wz" and "xww", we can conclude that 'w' comes before 'x'.
 * 3. From "xww" and "xz", we can conclude that 'w' comes before 'z'
 * 4. From "xz" and "zyy", we can conclude that 'x' comes before 'z'
 * 5. From "zyy" and "zwz", we can conclude that 'y' comes before 'w'
 *
 * From the above five points, we can conclude that the correct character order is: "ywxz"
 */

package topologicalsort;

import java.util.*;

public class AlienDictionary {

    /**
     * Topological Sort with extra steps.
     * Time：O(V + N)
     * Space: O(V + N)
     *
     * @param words the given words in alien dictionary
     * @return the order of alphabets from the given input
     */
    public String findOrder(String[] words) {
        StringBuilder alienAlphabetOrder = new StringBuilder();
        // 0 - corner case
        if (words == null || words.length == 0) {
            return alienAlphabetOrder.toString();
        }

        // 1 - set up hashmap to store the letters and the letters that have lower priority in ordering
        // set up another hashmap to store num of times a letter comes after the other letters
        Map<Character, List<Character>> lettersFollowing = new HashMap<>();
        Map<Character, Integer> letterCount = new HashMap<>();

        for (String word : words) {
            for (char c : word.toCharArray()) {
                lettersFollowing.put(c, new LinkedList<Character>());
                letterCount.put(c, 0);
            }
        }

        String w1, w2;
        for (int i = 0; i < words.length - 1; i++) {
            w1 = words[i];
            w2 = words[i + 1];
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                if (w1.charAt(j) != w2.charAt(j)) { // w1.charAt(j) is prior to w2.charAt(j) in ordering
                    lettersFollowing.get(w1.charAt(j)).add(w2.charAt(j));
                    letterCount.put(w2.charAt(j), letterCount.get(w2.charAt(j)) + 1);
                    break;
                }
            }
        }

        // 2 - find all letters that are prior in ordering
        Queue<Character> prior = new LinkedList<>();
        for (Character letter : letterCount.keySet()) {
            if (letterCount.get(letter) == 0) {
                prior.offer(letter);
            }
        }

        // 3 - add prior letters and find new priors among the rest letters
        while (!prior.isEmpty()) {
            char letter = prior.poll();
            alienAlphabetOrder.append(letter);
            for (char l : lettersFollowing.get(letter)) {
                letterCount.put(l, letterCount.get(l) - 1);
                if (letterCount.get(l) == 0) {
                    prior.offer(l);
                }
            }
        }

        // 4 - check whether there's a cycle
        if (alienAlphabetOrder.toString().length() == letterCount.size()) {
            return alienAlphabetOrder.toString();
        }

        return "";
    }

    public static void main(String[] args) {
        String result = new AlienDictionary().findOrder(new String[]{"ba", "bc", "ac", "cab"});
        System.out.println("Character order: " + result);

        result = new AlienDictionary().findOrder(new String[]{"cab", "aaa", "aab"});
        System.out.println("Character order: " + result);

        result = new AlienDictionary().findOrder(new String[]{"ywx", "wz", "xww", "xz", "zyy", "zwz"});
        System.out.println("Character order: " + result);
    }
}
