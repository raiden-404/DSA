// 961. N-Repeated Element in Size 2N Array
import java.util.HashSet;
class Solution {
    /**
     * Finds the element that appears N times in an array of size 2N.
     * * Logic: The problem states that exactly one element is repeated N times, 
     * and all other elements appear exactly once. Therefore, we do not need to 
     * count the frequency of every number. The moment we encounter a number 
     * that we have seen before, it must be the answer.
     */
    public int repeatedNTimes(int[] nums) {
        // Initialize a HashSet to store unique elements encountered so far.
        // We use a HashSet because it provides O(1) average time complexity 
        // for both .add() and .contains() operations.
        HashSet<Integer> set = new HashSet<>();
        
        // Iterate through each number 'n' in the input array.
        for (int n : nums) {
            
            // Check if the current number 'n' is already in the set.
            // If .contains(n) returns true, it means we have seen this number before.
            // Since unique numbers only appear once, the first time we see a 
            // duplicate, it is guaranteed to be the repeated element (the answer).
            if (set.contains(n)) {
                return n; // Return the result immediately.
            }
            
            // If the number was not in the set, add it now so we can 
            // recognize it if it appears again later in the array.
            set.add(n);
        }
        
        // This part of the code is theoretically unreachable because the problem 
        // guarantees that a solution always exists within the array.
        // However, Java requires a return statement here to compile successfully.
        return -1;
    }
}