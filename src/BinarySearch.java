import static java.lang.Math.abs;

public class BinarySearch {
    // recursive approach to find the target number
    public int binarySearch1(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        return helper(arr, target, 0, arr.length - 1);
//        return find((arr, target, 0, arr.length - 1);
    }

    private int helper(int[] arr, int target, int left, int right) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                return helper(arr, target, left, mid - 1);
            } else { // arr[mid] < target
                return helper(arr, target, mid + 1, right);
            }
        }
        return -1; /// the target is not in the array
    }

    private static int find(int[] data, int key, int lowerBound, int upperBound) {
        // base case –- target not found
        if (lowerBound > upperBound) {
            return -1;
        }
        int mid = lowerBound + (upperBound - lowerBound) / 2;
        // base case –- target found
        if (data[mid] == key) {
            return mid;
        }
        // recursive rule
        if (data[mid] < key) {
            return find(data, key, mid + 1, upperBound);
        } else {
            return find(data, key, lowerBound, mid - 1);
        }
    }

    // iterative approach to find the target number
    public int binarySearch2(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else { // arr[mid] > target
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] data, int key) {
        int lowerBound = 0;
        int upperBound = data.length - 1;
        int mid;

        while (true) {
            if (lowerBound > upperBound) { // target not found
                return -1;
            }
            mid = lowerBound + (upperBound - lowerBound) / 2;

            if (data[mid] == key) {
                return mid; // found the target index
            } else if (data[mid] < key) {
                lowerBound = mid + 1; // go to the right side
            } else {
                upperBound = mid - 1; // go to the left side
            }
        }
    }

    // search sorted 2D array using iterative approach to find the target number
    // solution: convert the 2D array to 1D array
    public boolean binarySearch3(int[][] matrix, int target) {
        // check null
        if (matrix.length == 0 || matrix[0].length == 0) return false;

        int row = matrix.length;
        int col = matrix[0].length;
        int leftIndex = 0;
        int rightIndex = row * col - 1;

        while (leftIndex <= rightIndex) {
            int mid = leftIndex + (rightIndex - leftIndex) / 2;
            int rowIndex = mid / col; // row number of 2D array
            int colIndex = mid % col; // col number of 2D array

            if (matrix[rowIndex][colIndex] == target) {
                return true;
            } else if (matrix[rowIndex][colIndex] < target) {
                leftIndex = mid + 1;
            } else { // matrix[rowIndex][colIndex] > target
                rightIndex = mid - 1;
            }
        }

        return false; // target not found
    }

    // search sorted 2D array using recursive approach to find the target number
    // solution: convert the 2D array to 1D array
    public boolean binarySearch4(int[][] matrix, int target) {
        // check null
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int leftIndex = 0;
        int rightIndex = row * col - 1;
        return binaryHelper(matrix, target, col, leftIndex, rightIndex);
    }

    private boolean binaryHelper(int[][] matrix, int target, int col, int left, int right) {
        int mid = left + (right - left) / 2;
        int rowIndex = mid / col;
        int colIndex = mid % col;
        while (left <= right) {
            if (matrix[rowIndex][colIndex] == target) {
                return true;
            } else if (matrix[rowIndex][colIndex] < target) {
                return binaryHelper(matrix, target, col, mid + 1, right); // why must use return here?
            } else { // matrix[rowIndex][colIndex] > target
                return binaryHelper(matrix, target, col, left, mid - 1); // why must use return here?
            }
        }
        return false;
    }

    // find the element (index) in the array that is closest to the target number
    // using iterative approach
    public int binarySearch5(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1) { // get out when left = right - 1;
            // note that here we cannot use left < right as loop condition => lead to infinite
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid; // cannot use mid + 1
            } else { // arr[mid] > target
                right = mid; // cannot use mid - 1
            }
        }

        if (abs(arr[left] - target) <= abs(arr[right] - target)) {
            return left;
        } else { // abs(arr[left] - target) > abs(arr[right] - target)
            return right;
        }
    }

    // find the index of the first occurrence of an target number
    // using iterative approach
    public int binarySearch6(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1) { // terminate when there're two num left
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                right = mid;
            } else if (arr[mid] < target) {
                left = mid; // left = mid + 1;
            } else { //(arr[mid] > target)
                right = mid; // right = mid - 1;
            }
        }
        // the order matters, compare the target with the element on the left first
        if (arr[left] == target) return left;
        else if (arr[right] == target) return right;
        else return -1;
    }

    // find the index of the last occurrence of an target number
    // using iterative approach
    public int binarySearch7(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1) { // terminate when there're two num left
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                left = mid;
            } else if (arr[mid] < target) {
                left = mid; // left = mid + 1;
            } else { //(arr[mid] > target)
                right = mid; // right = mid - 1;
            }
        }
        // the order matters, compare the target with the element on the left first
        if (arr[right] == target) return right;
        else if (arr[left] == target) return left;
        else return -1;
    }

    // find k elements in the array that is closest to the target value
    // return the left index of k elements here
    public int[] kCloesest(int[] arr, int target, int k) {
        // check null
        if (arr == null || arr.length == 0) return arr; // return the array and end the searching
        // check k
        if (k <= 0 || k > arr.length) return new int[0];

        // binary search to get the largest smaller or equal element
        int left = largestSmallerEqual(arr, target);
        // right = left + 1
        int right = left + 1;
        // These two numbers should be the cloest to target

        // Declare an k-element array to store the result
        int result[] = new int[k];
        for (int i = 0; i < k; i++) {
            // advance the left pointer when:
            // 1) right pointer is already out of bound
            // 2) right pointer is not out of bound, left pointer is not bound, and array[left] is closer to the target
            if (right >= arr.length || (left >= 0 && target - arr[left] <= arr[right] - target)) {
                result[i] = arr[left--];
            } else result[i] = arr[right++];
        }
        return result;
    }
/*
    Time Complexity: O(logn + k) = O(logn)
    Space Complexity: O(1)
*/

    /**
     * find the largest smaller or equal element's index in the array
     *
     * @param arr    input array
     * @param target T
     * @return the index
     */
    private int largestSmallerEqual(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right - 1) { // terminate when there's two num left
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                left = mid;
            } else { //(arr[mid] > target)
                right = mid; // right = mid - 1;
            }
        }
        // post-process: extend from the target space
        // return left or right based on right = left + 1
        if (arr[right] <= target) {
            return right;
        }
        if (arr[left] <= target) {
            return left;
        }
        return -1; // cannot find
    }

    public static void main(String[] args) {
        BinarySearch b = new BinarySearch();
        int[] arr = new int[]{1, 2, 3, 5, 6, 9};

        int solution = b.binarySearch1(arr, 9);
        System.out.println(solution); //5

        solution = b.binarySearch2(arr, 9);
        System.out.println(solution);

        int[][] matrix = new int[][]{
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 100, 1000},
        };

        boolean s = b.binarySearch3(matrix, 1000);
        System.out.println(s);

        s = b.binarySearch4(matrix, 10);
        System.out.println(s);

        solution = b.binarySearch5(arr, 7);
        System.out.println(solution);

        int target = 5;
        arr = new int[]{1, 2, 3, 5, 5, 5};

        solution = b.binarySearch6(arr, target);
        System.out.println(solution); //3

        solution = b.binarySearch7(arr, target);
        System.out.println(solution); //5

    }
}