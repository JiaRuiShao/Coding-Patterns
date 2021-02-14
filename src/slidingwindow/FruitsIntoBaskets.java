// Given an array of characters where each character represents a fruit tree,
// you are given two baskets, and your goal is to put maximum number of fruits in each basket.
// The only restriction is that each basket can have only one type of fruit.
//
// You can start with any tree, but you can’t skip a tree once you have started.
// You will pick one fruit from each tree until you cannot, i.e.,
// you will stop when you have to pick from a third fruit type.
//
// Write a function to return the maximum number of fruits in both the baskets.

package slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class FruitsIntoBaskets {

    /**
     * Sliding Window -- Medium.
     * Similar to the longest substring with k characters question, only the constraint k value and the type of input changed.
     * TC: O(n) -- O(2n) -> O(n) linear
     * SC: O(1) -- O(3) => O(1) constant
     * @param arr
     * @return
     */
    public int findLength(char[] arr) {
        int maxLength = 0, startIdx = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int endIdx = 0; endIdx < arr.length; endIdx++) {
            map.put(arr[endIdx], map.getOrDefault(arr[endIdx], 0) + 1);
            while (map.size() > 2) {
                if (map.get(arr[startIdx]) == 1) {
                    map.remove(arr[startIdx]);
                } else {
                    map.put(arr[startIdx], map.get(arr[startIdx]) - 1);
                }
                startIdx++;
            }
            maxLength = Math.max(maxLength, endIdx - startIdx + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        FruitsIntoBaskets sw = new FruitsIntoBaskets();
        System.out.println("Maximum number of fruits: " +
                sw.findLength(new char[] { 'A', 'B', 'C', 'A', 'C' }));
        System.out.println("Maximum number of fruits: " +
                sw.findLength(new char[] { 'A', 'B', 'C', 'B', 'B', 'C' }));
    }

}
