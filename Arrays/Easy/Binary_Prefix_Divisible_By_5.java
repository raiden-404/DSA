/**
 * LeetCode 1018: Binary Prefix Divisible By 5
 * * Time Complexity: O(N) - We iterate through the array exactly once.
 * Space Complexity: O(1) - We only use a single integer 'mod' for state tracking.
 */
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        // We only need to track the remainder, not the full number.
        int mod = 0; 
        List<Boolean> res = new ArrayList<>();

        for(int n : nums) {
            // Logic: (Previous_Remainder * 2 + Current_Bit) % 5
            // We apply % 5 immediately to keep the number small (0-4)
            // and prevent Integer Overflow.
            mod = ((mod * 2) + n) % 5;
            
            // If remainder is 0, the number represented by the prefix is divisible by 5.
            res.add(mod == 0);
        }
        return res;
    }
}