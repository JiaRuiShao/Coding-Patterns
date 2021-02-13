public class StringDeduplicate {

    /**
     * Perform a basic string compression using the counts of characters.
     * For example, you can compress the string of “aaaaabbbbcccddd” to be “a5b4c3d3”.
     * Another example string is “aabbaabbb” which needs to be compressed as “a2b2a2b3”, not “a4b5”.
     * Your algorithm should also be able to handle the case where compressed string is bigger than the original string.
     *
     * Implementation: for loop
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * In-place replacement: no
     *
     * @param str input string that should have at least two characters
     * @return Compressed string
     */
    private static String stringCompress1(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }

        StringBuilder compressed = new StringBuilder();
        char prev = str.charAt(0);
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            char curr = str.charAt(i);
            if (curr == prev) {
                count++;
            } else {
                compressed.append(prev);
                compressed.append(Integer.toString(count));
                prev = str.charAt(i); // reset prev
                count = 1; // reset count
            }
        }
        // break the loop when i == str.length()
        compressed.append(prev);
        compressed.append(Integer.toString(count));

        // check length and return accordingly
        // return compressed StringBuilder as a string
        /*if (compressed.length() > str.length()) {
            return str;
        }*/
        return compressed.toString();
    }

    /**
     * Perform a basic string compression using two pointers.
     * For example, you can compress the string of “aaaaabbbbcccddd” to be “a5b4c3d3”.
     * Another example string is “aabbaabbb” which needs to be compressed as “a2b2a2b3”, not “a4b5”.
     * Your algorithm should also be able to handle the case where compressed string is bigger than the original string.
     * In that case, you should return the original string instead of trying to compress the string.
     * You can assume that an input string has at least two characters and does not include any numbers.
     *
     * Implementation: two-pointers
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * In-place replacement: no
     *
     * @param str input string that should have at least two characters
     * @return Compressed string
     */
    private String stringCompress2(String str) {
        if (str == null || str.length() < 2) {
            return str;
        }

        int i = 0; // slow pointer
        int j = 1; // fast pointer
        StringBuilder solu = new StringBuilder();
        while(j < str.length()) {
            if (str.charAt(j) != str.charAt(i)) {
                solu.append(str.charAt(i)).append(Integer.toString(j - i));
                i = j;
                j++;
            } else if (str.charAt(j) == str.charAt(i)) {
                j++;
            }
        }
        // j equals to str.length() now
        solu.append(str.charAt(i)).append(Integer.toString(j - i));

        return solu.toString();
    }

    public static void main(String[] args) {
        String[] testing = {"", " ", "  ", "aaabccde", "abbbcdeeeeee"};
        StringDeduplicate stringDedup = new StringDeduplicate();

        Stopwatch timer1 = new Stopwatch();
        for (String test : testing) {
            System.out.printf("output for %s: %s%n", test, stringDedup.stringCompress1(test));
        }
        System.out.print("running time:" + timer1.elapsedTime() + " millisec");

        Stopwatch timer2 = new Stopwatch();
        for (String test : testing) {
            System.out.printf("output for %s: %s%n", test, stringDedup.stringCompress2(test));
        }
        System.out.print("running time:" + timer2.elapsedTime() + " millisec");
    }
}
