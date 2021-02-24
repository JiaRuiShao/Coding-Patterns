// Comparing Strings containing Backspaces (medium)
//Given two strings containing backspaces (identified by the character ‘#’),
// check if the two strings are equal.

package twopointers;

import java.util.Stack;

public class BackSpaceCompare {

    /**
     * Two Pointers Practice Problem #2 -- Medium.
     *
     * TC: O(m + n) where m and n are the length of str1 and str2
     * SC: O(1)
     *
     * @param str1 input str #1
     * @param str2 input str #2
     * @return true if two strings are teh same after applying backspace; false if not
     */
    public boolean compare(String str1, String str2) {

        // start from the end of the str and move towards the start
        int str1Idx = str1.length() - 1, str2Idx = str2.length() - 1;

        while (str1Idx >= 0 || str2Idx >= 0) {
            str1Idx = findNextValidIndex(str1, str1Idx);
            str2Idx = findNextValidIndex(str2, str2Idx);
            if (str1Idx < 0 && str2Idx < 0) return true;
            if (str1Idx < 0 || str2Idx < 0) return false;
            if (str1.charAt(str1Idx--) != str2.charAt(str2Idx--)) return false;
        }

        return true;
    }

    private int findNextValidIndex(String str, int idx) {

        // record the num of backspace
        int backspaceCount = 0;

        while (idx >= 0) {
            if (str.charAt(idx) == '#') {
                backspaceCount++;
                idx--;
            } else if (backspaceCount > 0) { // str.charAt(idx) != '#" && backspaceCount > 0
                backspaceCount--;
                idx--;
            } else { // str.charAt(idx) != '#" && backspaceCount == 0
                break;
            }
        }
        if (backspaceCount == 0) return idx;
        else return -1;
    }

    /**
     * Using Stack.
     *
     * TC: O(n + m)
     * SC: O(n + m)
     *
     * @param str1 input str #1
     * @param str2 input str #2
     * @return true if two strings are teh same after applying backspace; false if not
     */
    public boolean compare2(String str1, String str2) {
        return buildStack(str1).equals(buildStack(str2));
    }

    private String buildStack(String str) {
        Stack<Character> stack = new Stack<>();
        for (Character c : str.toCharArray()) {
            if (c == '#' && !stack.isEmpty()) stack.pop();
            else stack.push(c);
        }
        return String.valueOf(stack);
    }

    public static void main(String[] args) {
        BackSpaceCompare tp = new BackSpaceCompare();
        System.out.println(tp.compare("xy#z", "xzz#"));
        System.out.println(tp.compare2("xy#z", "xzz#"));
        System.out.println();
        System.out.println(tp.compare("xy#z", "xyz#"));
        System.out.println(tp.compare2("xy#z", "xyz#"));
        System.out.println();
        System.out.println(tp.compare("xp#", "xyz##"));
        System.out.println(tp.compare2("xp#", "xyz##"));
        System.out.println();
        System.out.println(tp.compare("nzp#o#g", "b#nzp#o#g"));
        System.out.println(tp.compare2("nzp#o#g", "b#nzp#o#g"));
    }
}
