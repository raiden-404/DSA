class Solution {
    public int minOperations(int[] nums, int k) {
        // Variable to store the sum modulo k while traversing
        int res = 0;

        // Loop through each element in the array
        for (int n : nums) {
            // Add current number to result and keep only modulo k
            // This avoids integer overflow & only remainder matters for divisibility
            res = (res + n) % k;
        }

        // res now contains: (sum of array) % k
        // If res == 0 → sum is already divisible by k → 0 operations needed
        // Otherwise → we need exactly 'res' operations (subtracting 1 each time)
        return res;
    }
}
