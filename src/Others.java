public class Others {
    public String gameResult(int scoreA, int scoreB) {
        // Write code here, don't forget to check for a tie
        java.lang.String myString = "";
        if (scoreA > scoreB) {
            System.out.println("The winner is A");
            myString = "The winner is A";
        } else if (scoreA < scoreB) {
            System.out.println("The winner is B");
            myString = "The winner is B";
        } else {
            System.out.println("Tie");
            myString = "Tie";
        }
        System.out.println("Good game");
        return myString;
    }

    /**
     * Find the greatest common divisor of two integers
     * 
     * @param a 1st integer
     * @param b 2nd integer
     * @return the gcd if a & b > 0
     */
    private int gcd(int a, int b) {
        if (a > 0 && b > 0) {
            while (a != b) {
                if (a > b)
                    a = a - b;
                else
                    b = b - a;
            }
            return a;
        } else {
            return -1;
        }
    }

    public int binarySearch(int a[], int target) {
        if (a == null || a.length == 0)
            return -1;
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else { // a[mid] >= target
                right = mid - 1;
            }
        }
        return -1; // the target is not in the array
    }

    public int a_pow_b(int a, int b) {
        // base case
        if (b == 0) {
            return 1;
        }
        int half_result = a_pow_b(a, b / 2);

        if (b % 2 == 0) {
            return half_result * half_result;
        } else {
            return half_result * half_result * a;
        }
    }
}
