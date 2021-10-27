package binarysearch;

/*
 * Given an array of lowercase letters sorted in ascending order,
 * find the smallest letter in the given array greater than a given ‘key’.
 * Assume the given array is a circular list, which means that the last letter
 * is assumed to be connected with the first letter.
 *
 * This also means that the smallest letter in the given array is greater than the last letter of the array
 * and is also the first letter of the array.
 *
 * Write a function to return the next letter of the given ‘key’.
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'f'
 * Output: 'h'
 * Explanation: The smallest letter greater than 'f' is 'h' in the given array.
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'b'
 * Output: 'c'
 * Explanation: The smallest letter greater than 'b' is 'c'.
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'm'
 * Output: 'a'
 * Explanation: As the array is assumed to be circular, the smallest letter greater than 'm' is 'a'.
 *
 * Input: ['a', 'c', 'f', 'h'], key = 'h'
 * Output: 'a'
 * Explanation: As the array is assumed to be circular, the smallest letter greater than 'h' is 'a'.
 */

public class NextLetter {

    /**
     * Binary Search to find the next letter of the target char in the given circular arrary.
     * Time: O(logn) where n is the char # in the given arr
     * Space: O(1)
     *
     * @param letters the given circular letters
     * @param key     the target char
     * @return the next char in the given letters that is larger than the target char
     */
    public char findNextLetter(char[] letters, char key) {
        int left = 0, right = letters.length - 1, mid, n = letters.length;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (letters[mid] == key) return letters[(mid + 1) % n];
            else if (letters[mid] < key) left = mid + 1;
            else right = mid - 1;
        }
        // key not found, left == right + 1
        return letters[left % n];
    }

    public static void main(String[] args) {
        NextLetter letter = new NextLetter();
        System.out.println(letter.findNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'f'));
        System.out.println(letter.findNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'b'));
        System.out.println(letter.findNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'm'));
        System.out.println(letter.findNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'h'));
    }
}
