import java.util.Stack;
import java.lang.Integer;
public class StackKeepMinAdvanced {
    private Stack<Integer> store = new Stack<Integer>();
    private int globalMin = Integer.MIN_VALUE;

    public int getMin() {
        if(store.empty()) {
            return Integer.MIN_VALUE;
        }else {
            return globalMin;
        }
    }

    public void push(int val) {
        if(store.empty()) {
            store.push(val);
            globalMin = val;
        }
        if(val >= globalMin) {
            store.push(val);
        }else { //val < globalMin && not the 1st element in teh Stack
            int tem = 2 * val - globalMin;
            store.push(tem);
            globalMin = val;
        }
    }

    public int pop() {
        if(store.empty()) {
            return Integer.MIN_VALUE;
        }
        int popTem = store.pop();
        if(popTem >= globalMin) {// directly pop
            return popTem;
        }else { // popTem < globalMin -- flag
            int tem = globalMin;
            globalMin = 2 * globalMin - popTem; //globalMin set to the previous min value
            return tem;
        }
    }

    public static void main(String[] args) {
        StackKeepMinAdvanced s = new StackKeepMinAdvanced();
        s.push(-4);
        s.push(3);
        s.push(-41);
        s.push(1);
        s.push(3);
        s.push(2);
        s.push(-5);
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.pop());
        System.out.println(s.getMin());
    }
}