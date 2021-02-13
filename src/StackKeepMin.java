import java.util.Stack;
import java.lang.Integer;
public class StackKeepMin {
    private Stack<Integer> store = new Stack<>();
    private Stack<Integer> recorder = new Stack<>();
    private int globalMin = Integer.MAX_VALUE;

    public void push(int val) {
        if (store.empty() || val < globalMin) {
            store.push(val);
            recorder.push(val);
            globalMin = val;
        } else {
            store.push(val);
            recorder.push(globalMin);
        }
    }

    public int pop() {
        recorder.pop();
        return store.pop();
    }

    public int getMin() {
        return recorder.peek();
    }

    public static void main(String[] args) {
        StackKeepMin s = new StackKeepMin();
        s.push(3);
        s.push(5);
        s.push(1);
        s.push(9);
        s.push(10);
        System.out.println(s.getMin());
        s.pop();
        s.pop();
        s.pop();
        s.pop();
        System.out.println(s.getMin());
    }
}