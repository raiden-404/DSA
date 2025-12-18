/*
 * Problem: 728. Self Dividing Numbers
 *
 * A self-dividing number is a number that is divisible by every digit it contains.
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * A self-dividing number is not allowed to contain the digit zero.
 *
 * Given two integers left and right, return a list of all the self-dividing numbers in the range [left, right].
 */

class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        // List to store the resulting self-dividing numbers
        List<Integer> res = new ArrayList<>();

        // Iterate through every number in the given range [left, right]
        for (int i = left; i <= right; i++) {
            // Check if the current number 'i' is a self-dividing number
            if (isValid(i)) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * Helper function to determine if a number is self-dividing.
     * * @param num The number to check
     * @return true if self-dividing, false otherwise
     */
    public boolean isValid(int num) {
        int temp = num;
        
        // Process each digit of the number
        while (temp > 0) {
            // Extract the last digit
            int digit = temp % 10;
            
            // Move to the next digit by removing the last one
            temp /= 10;

            // Condition 1: A self-dividing number cannot contain the digit 0
            // Condition 2: The number must be divisible by the digit
            if (digit == 0 || num % digit != 0) {
                return false;
            }
        }
        
        // If loop completes without returning false, all digits satisfied the condition
        return true;
    }
}