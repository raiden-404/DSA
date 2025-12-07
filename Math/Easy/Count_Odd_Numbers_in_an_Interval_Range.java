class Solution {
    /**
     * LeetCode Problem 1523: Count Odd Numbers in an Interval Range
     *
     * Counts the total number of odd integers within the inclusive range [low, high].
     *
     * @param low The lower bound of the inclusive range.
     * @param high The upper bound of the inclusive range.
     * @return The count of odd numbers in [low, high].
     */
    public int countOdds(int low, int high) {
        // Calculate the difference between the bounds. This is the total number of steps.
        int diff = high - low;

        // Case 1: The length of the interval (high - low) is odd.
        // This means the interval contains an equal number of even and odd numbers, plus one extra
        // number which is an odd number (since low and high have different parity, and they
        // contribute 1/2 + 1/2 = 1 to the count).
        // Example: [2, 5] -> (5-2)=3. Odd numbers are 3, 5. Count = 2. Formula: (3/2) + 1 = 1 + 1 = 2.
        // Example: [3, 6] -> (6-3)=3. Odd numbers are 3, 5. Count = 2. Formula: (3/2) + 1 = 1 + 1 = 2.
        if (diff % 2 == 1) {
            // The result is (diff/2) for the pairs of (odd, even) or (even, odd), 
            // plus 1 for the remaining single odd number.
            return (diff / 2) + 1;
        }

        // Case 2: The length of the interval (high - low) is even.
        // This means low and high have the same parity (both odd or both even).
        // The total number of integers in the range is diff + 1 (even + 1 = odd total count).

        // Subcase 2a: Both low and high are even (e.g., [2, 6]).
        // The numbers are E, O, E, O, E. The odd count is (diff/2).
        // Example: [2, 6] -> (6-2)=4. Odd numbers are 3, 5. Count = 2. Formula: 4/2 = 2.
        if (high % 2 == 0) { // If high is even, low must also be even since diff is even.
            // The odd numbers are exactly half of the difference.
            return diff / 2;
        }

        // Subcase 2b: Both low and high are odd (e.g., [3, 7]).
        // The numbers are O, E, O, E, O. The odd count is (diff/2) + 1.
        // Example: [3, 7] -> (7-3)=4. Odd numbers are 3, 5, 7. Count = 3. Formula: (4/2) + 1 = 3.
        // Since diff is even, and high is odd, this branch is reached.
        return (diff / 2) + 1;
    }
}