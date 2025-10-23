package Strings.Medium;

/**
 * LeetCode Problem: 43. Multiply Strings
 *
 * Problem Description:
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 * Note: You must not use any built-in BigInteger library or convert the inputs to integers directly.
 *
 * Example:
 * Input: num1 = "123", num2 = "45"
 * Output: "5535"
 */
class Solution {
    public String multiply(String num1, String num2) {
        // --- Step 0: Handle Edge Case ---
        // If either number is "0", the product is "0". This avoids unnecessary computation.
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }

        // --- Step 1: Initialize Pointers and Result Array ---
        // Get the last index for each string for easier right-to-left iteration.
        int n = num1.length() - 1;
        int m = num2.length() - 1;

        // The maximum possible length of the product is the sum of the lengths of the two numbers.
        // E.g., 99 * 9 = 891 (2 digits + 1 digit = 3 digits).
        // We create an integer array to store the digits of the result.
        int[] res = new int[num1.length() + num2.length()];

        // --- Step 2: Simulate Multiplication Digit by Digit ---
        // Loop through both numbers from right to left (least significant digit to most significant).
        for (int i = n; i >= 0; i--) {
            for (int j = m; j >= 0; j--) {
                // Convert char to int for calculation. '5' - '0' = 5.
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                /*
                 * --- THIS IMPLEMENTATION BUILDS THE RESULT IN REVERSE ORDER ---
                 * The product of num1[i] and num2[j] affects two positions in the result.
                 * Unconventional Indexing Logic:
                 * - 'Ones' Place (Least significant): res.length - (i + j + 2)
                 * - 'Tens' Place (Carry): res.length - (i + j + 1)
                 * Example: For the last digits (i=n, j=m), the ones place is res[0].
                 * This means res[0] stores the rightmost digit of the final answer.
                 */

                // 'sum' includes the new product and any carry from a previous multiplication
                // that affected this same position.
                int sum = res[i + j + 1] + mul;

                // Set the 'ones' digit for the current position.
                res[i + j + 1] = sum % 10;
                // Add the 'tens' digit (the carry) to the next position to the left.
                res[i + j] += sum / 10;
            }
        }

        // --- Step 3: Convert the Integer Array to a String ---
        StringBuilder result = new StringBuilder();

        // Find the first non-zero digit to skip any leading zeros.
        int firstDigitIndex = 0;
        while (firstDigitIndex < res.length - 1 && res[firstDigitIndex] == 0) {
            firstDigitIndex++;
        }

        // Build the final string from the calculated digits.
        for (int i = firstDigitIndex; i < res.length; i++) {
            result.append(res[i]);
        }
        
        return result.toString();
    }
}