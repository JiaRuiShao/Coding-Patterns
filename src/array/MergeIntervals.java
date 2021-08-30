/*You are given an array (list) of interval pairs as input where each interval has a start and end timestamp.
The input array is sorted by starting timestamps.
You are required to merge overlapping intervals and return a new output array.*/

package array;

import java.util.ArrayList;

class Pair {
    public int first;
    public int second;

    public Pair(int x, int y) {
        this.first = x;
        this.second = y;
    }
}

class MergeIntervals {
    public ArrayList<Pair> mergeIntervals(ArrayList<Pair> v) {
        if (v == null || v.size() == 0) return null;

        ArrayList<Pair> result = new ArrayList<Pair>();
        int start = v.get(0).first, end = v.get(0).second;

        for (int i = 1; i < v.size(); i++) {
            if (v.get(i).first <= end) {
                end = Math.max(end, v.get(i).second);
            } else { // v.get(i).first > end
                result.add(new Pair(start, end));
                start = v.get(i).first;
                end = v.get(i).second;
            }
        }
        result.add(new Pair(start, end));
        return result;
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        ArrayList<Pair> v = new ArrayList<Pair>();

        v.add(new Pair(1, 5));
        v.add(new Pair(3, 7));
        v.add(new Pair(4, 6));
        v.add(new Pair(6, 8));
        v.add(new Pair(10, 12));
        v.add(new Pair(11, 15));

        ArrayList<Pair> result = mi.mergeIntervals(v);

        for (int i = 0; i < result.size(); i++) {
            System.out.print(String.format("[%d, %d] ", result.get(i).first, result.get(i).second));
        }
    }
}