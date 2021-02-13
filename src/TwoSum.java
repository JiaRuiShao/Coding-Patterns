import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Two Sum III - Data structure design
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/
 * Use List is the fastest.
 * TC: O(nlogn)
 * SC: O(n)
 */
public class TwoSum {

    private List<Integer> nums;

    public TwoSum() {
        nums = new ArrayList<Integer>();
        // b/c here we always add items at tail, which means it's
        // amortized O(1) TC for ArrayList
        // we also need to search/find/get the value in the middle
        // so linkedlist is not as efficient as arraylist
    }

    public void add(int value) {
        nums.add(value);
    }

    private boolean isSorted() {
        for (int i = 1 ; i < nums.size() - 1; i++) {
            if (nums.get(i) - nums.get(i - 1) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean find(int value) {
        // sort the arraylist
        if (!isSorted()) {
            Collections.sort(nums);
        }

        // two pointers
        int left = 0, right = nums.size() - 1;
        while (left < right) {
            if (nums.get(left) + nums.get(right) == value) {
                return true;
            } else if (nums.get(left) + nums.get(right) < value) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }

    private void display() {
        Iterator itr = nums.iterator();
        while (itr.hasNext()) {
            System.out.print(itr.next());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
        twoSum.add(1); // [] --> [1]
        twoSum.display();
        twoSum.add(3); // [1] --> [1,3]
        twoSum.display();
        twoSum.add(5); // [1,3] --> [1,3,5]
        twoSum.display();
        System.out.println(twoSum.find(4)); // 1 + 3 = 4, return true
        System.out.println(twoSum.find(7)); // No two integers sum up to 7, return false
    }
}

/*
// could also use hashmap, but here is not as fast as the anser using list
public class TwoSum {
    private HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public void add(int number) {
        map.put(number, map.containsKey(number) ? map.get(number) + 1 : 1);
    }

    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int i = entry.getKey();
            int j = value - i;
            if ((i == j && entry.getValue() > 1) || (i != j && map.containsKey(j))) {
                return true;
            }
        }
        return false;
    }
}*/
