import java.util.Stack;
import java.lang.Integer;

/**
 * Implement a Queue using two Stacks
 */
public class IQueue{
    private Stack<Integer> pushStack = new Stack<Integer>();
    private Stack<Integer> popStack = new Stack<Integer>();
    public void push(int val) {
        pushStack.push(val);
    }

    public int pop() {
        if(popStack.empty()) {
            while(!pushStack.empty()) {
                popStack.push(pushStack.pop());
            }
        }
        return popStack.pop();
    }
    public static void main(String[] args) {
        IQueue iq = new IQueue();
        for (int i = 0; i < 1000; i++) {
            iq.push(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(iq.pop());
        }
        for (int i = 0; i < 1000; i++) {
            iq.push(i);
        }
        for (int i = 0; i < 1900; i++) {
            System.out.println(iq.pop());
        }

    }
}