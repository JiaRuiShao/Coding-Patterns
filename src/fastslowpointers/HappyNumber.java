// Happy Number (medium)
// Write an algorithm to determine if a number n is happy.
// A happy number is a number defined by the following process:
//    Starting with any positive integer, replace the number by the sum of the squares of its digits.
//    Repeat the process until the number equals 1 (where it will stay),
//    or it loops endlessly in a cycle which does not include 1.
//    Those numbers for which this process ends in 1 are happy.
// Return true if n is a happy number, and false if not.


package fastslowpointers;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {

    /**
     * Use HashSet to store the sum of digits' squares.
     *
     * TC: O(logn + log(logn) + ...) -> O(logn) where logn is the digits # of input num
     * SC: O(logn) ? how many numbers are there in the loop
     *
     * @param num input num
     * @return true if num is a happy number; else if not
     */
    public boolean isHappy(int num) {
        Set<Integer> set = new HashSet<>();
        while (num != 1) {
            if (set.contains(num)) return false;
            set.add(num);
            num = getNext(num);
        }
        return true;
    }

    private int getNext(int num) {
        int sum = 0;
        while (num > 0) {
            sum += (num % 10) * (num % 10);
            num /= 10; // notice java chop off the decimal points for us
        }
        return sum;
    }

    /**
     * Use Floyd's Cycle-Finding Algorithm.
     * Fast & Slow Pointers.
     *
     *
     * If the number NNN is less than or equal to 1000, then we reach the cycle or ‘1’ in at most 1001 steps.
     * For N > 1000, suppose the number has ‘M’ digits and the next number is ‘N1’.
     * From the sequence behavior Wikipedia link(https://en.wikipedia.org/wiki/Happy_number#Sequence_behavior),
     * we know that the sum of the squares of the digits of ‘N’ is at most 9^2M,
     * or 81M (this will happen when all digits of ‘N’ are ‘9’).
     * This means:
     * N1 < 81M = 81 log(N + 1) => N1 = O(logN)
     *
     * TC: O(logN)
     * SC: O(1)
     *
     * @param num input num
     * @return true if num is a happy number; else if not
     */
    public boolean isHappy2(int num) {
        int fast = getNext(num), slow = num;
        while (fast != 1 && fast != slow) {
            fast = getNext(getNext(fast));
            slow = getNext(slow);
        }
        return fast == 1;
    }

    public static void main(String[] args) {
        HappyNumber fs = new HappyNumber();
        System.out.println(fs.isHappy(23));
        System.out.println(fs.isHappy2(23));
        System.out.println(fs.isHappy(12));
        System.out.println(fs.isHappy2(12));
    }
}
