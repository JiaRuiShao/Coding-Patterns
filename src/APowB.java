// assume that a & b are both integers
// neglect the corner case when a = 0, b < 0

public class APowB {
    public int aPowB(int a, int b) {
        if (b == 0) {
            return 1;
        } else {
            int half = b / 2;
            int half_value = aPowB(a, half);
            if (b % 2 == 0) {
                return half_value * half_value;
            } else {
                return half_value * half_value * a;
            }
        }

    }

    public static void main(String[] args) {
        APowB c = new APowB();
        System.out.println(c.aPowB(2, 5));
    }
}