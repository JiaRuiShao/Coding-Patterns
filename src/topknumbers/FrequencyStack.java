/*
 * Design a class that simulates a Stack data structure, implementing the following two operations:
 * - push(int num): Pushes the number ‘num’ on the stack.
 * - pop(): Returns the most frequent number in the stack.If there is a tie, return the number which was pushed later.
 *
 * Example: After following push operations: push(1), push(2), push(3), push(2), push(1), push(2), push(5)
 * 1. pop() should return 2, as it is the most frequent number
 * 2. Next pop() should return 1
 * 3. Next pop() should return 2
 *
 * Solution:
 * We will keep three things with every number that we push to the heap:
 * 1. number // value of the number
 * 2. frequency // current frequency of the number when it was pushed to the heap
 * 3. sequenceNumber // a sequence number, to know what number came first
 */

package topknumbers;

import java.util.*;

class Element {
    int number;
    int freq;
    int seqNum;
    public Element(int number, int freq, int seqNum) {
        this.number = number;
        this.freq = freq;
        this.seqNum = seqNum;
    }
}

class ElementComparator implements Comparator<Element> {
    public int compare(Element e1, Element e2) {
        if (e1.freq == e2.freq) return Integer.compare(e2.seqNum, e1.seqNum);
        return Integer.compare(e2.freq, e1.freq);
    }
}

public class FrequencyStack {
    int seqNum = 0;
    Map<Integer, Integer> freq = new HashMap<>();
    Queue<Element> maxHeap = new PriorityQueue<>(new ElementComparator());

    public void push(int num) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
        maxHeap.offer(new Element(num, freq.get(num), seqNum++));
    }

    public int pop() {
        if (maxHeap.isEmpty()) return -1;
        int num = maxHeap.poll().number;
        freq.put(num, freq.get(num) - 1);
        if (freq.get(num) == 0) freq.remove(num);
        return num;
    }

    public static void main(String[] args) {
        FrequencyStack frequencyStack = new FrequencyStack();
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(3);
        frequencyStack.push(2);
        frequencyStack.push(1);
        frequencyStack.push(2);
        frequencyStack.push(5);
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
        System.out.println(frequencyStack.pop());
    }
}
